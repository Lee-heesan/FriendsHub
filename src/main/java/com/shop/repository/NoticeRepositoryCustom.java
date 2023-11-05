package com.shop.repository;

import com.shop.dto.ItemSearchDto;
import com.shop.dto.MainItemDto;
import com.shop.dto.NoticeDtlDto;
import com.shop.dto.NoticeSearchDto;
import com.shop.entity.Item;
import com.shop.entity.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoticeRepositoryCustom {

    Page<Notice> getNoticePage(NoticeSearchDto noticeSearchDto, Pageable pageable);

    Page<NoticeDtlDto> getNoticeDtlPage(NoticeSearchDto noticeSearchDto, Pageable pageable);

}