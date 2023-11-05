package com.shop.repository;

import com.shop.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice,Long>,
        QuerydslPredicateExecutor<Notice>, NoticeRepositoryCustom {
    List<Notice> findByNoticeNm(String noticeNm);
    List<Notice> findByNoticeNmOrNoticeDetail(String noticeNm, String noticeDetail);

    @Query("select i from Notice i where i.noticeDetail like " +
            "%:noticeDetail%")
    List<Notice> findByNoticeDetail(@Param("noticeDetail") String noticeDetail);

}
