package com.shop.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.constant.ItemSellStatus;
import com.shop.dto.*;
import com.shop.entity.*;
import com.shop.service.NoticeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

public class NoticeRepositoryCustomImpl implements NoticeRepositoryCustom{

    private JPAQueryFactory queryFactory;

    public NoticeRepositoryCustomImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }


    private BooleanExpression regDtsAfter(String searchDateType){

        LocalDateTime dateTime = LocalDateTime.now();

        if(StringUtils.equals("all", searchDateType) || searchDateType == null){
            return null;
        } else if(StringUtils.equals("1d", searchDateType)){
            dateTime = dateTime.minusDays(1);
        } else if(StringUtils.equals("1w", searchDateType)){
            dateTime = dateTime.minusWeeks(1);
        } else if(StringUtils.equals("1m", searchDateType)){
            dateTime = dateTime.minusMonths(1);
        } else if(StringUtils.equals("6m", searchDateType)){
            dateTime = dateTime.minusMonths(6);
        }

        return QNotice.notice.regTime.after(dateTime);
    }

    private BooleanExpression searchByLike(String searchBy, String searchQuery){

        if(StringUtils.equals("noticeNm", searchBy)){
            return QNotice.notice.noticeNm.like("%" + searchQuery + "%");
        } else if(StringUtils.equals("createdBy", searchBy)){
            return QNotice.notice.createdBy.like("%" + searchQuery + "%");
        }

        return null;
    }

    @Override
    public Page<Notice> getNoticePage(NoticeSearchDto noticeSearchDto, Pageable pageable) {

        QueryResults<Notice> results = queryFactory
                .selectFrom(QNotice.notice)
                .where(regDtsAfter(noticeSearchDto.getSearchDateType()),
                        searchByLike(noticeSearchDto.getSearchBy(),
                                noticeSearchDto.getSearchQuery()))
                .orderBy(QNotice.notice.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

            List<Notice> content = results.getResults();
            Long total =results.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

    private BooleanExpression noticeNmLike(String searchQuery){
       return StringUtils.isEmpty(searchQuery) ? null : QNotice.notice.noticeNm.like("%" + searchQuery + "%");
   }

    @Override
    public Page<NoticeDtlDto> getNoticeDtlPage(NoticeSearchDto noticeSearchDto, Pageable pageable) {
        QNotice notice = QNotice.notice;

        List<NoticeDtlDto> content = queryFactory
                .select(
                        new QNoticeDtlDto(
                                notice.id,
                                notice.noticeNm,
                                notice.noticeDetail,
                                notice.writeName)
                )
                .from(notice)
                .where(noticeNmLike(noticeSearchDto.getSearchQuery()))
                .orderBy(notice.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .select(Wildcard.count)
                .from(notice)
                .where(noticeNmLike(noticeSearchDto.getSearchQuery()))
                .fetchOne()
                ;

        return new PageImpl<>(content, pageable, total);
    }


}