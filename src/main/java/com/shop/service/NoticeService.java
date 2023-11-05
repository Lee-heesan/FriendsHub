package com.shop.service;

import com.shop.dto.*;
import com.shop.entity.Item;
import com.shop.entity.Notice;
import com.shop.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public Long saveNotice(NoticeFormDto noticeFormDto) {

        //공지사항 등록
        Notice notice = noticeFormDto.createNotice();
        noticeRepository.save(notice);

        return notice.getId();
    }

    @Transactional(readOnly = true)
    public NoticeFormDto getNoticeDtl(Long id){

        Notice notice = noticeRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        NoticeFormDto noticeFormDto = NoticeFormDto.of(notice);
        return noticeFormDto;
    }

    public Long updateItem(NoticeFormDto noticeFormDto){
        //상품 수정
        Notice notice = noticeRepository.findById(noticeFormDto.getId())
                .orElseThrow(EntityNotFoundException::new);
        notice.updateNotice(noticeFormDto);


        return noticeFormDto.getId();
    }


    @Transactional(readOnly = true)
    public Page<Notice> getNoticePage(NoticeSearchDto noticeSearchDto, Pageable pageable){
        return noticeRepository.getNoticePage(noticeSearchDto, pageable);
    }

    @Transactional(readOnly = true)
    public Page<NoticeDtlDto> NoticeDtlPage(NoticeSearchDto noticeSearchDto, Pageable pageable){
        return noticeRepository.getNoticeDtlPage(noticeSearchDto, pageable);
    }
}
