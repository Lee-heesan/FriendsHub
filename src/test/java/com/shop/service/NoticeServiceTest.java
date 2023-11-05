package com.shop.service;

import com.shop.constant.ItemSellStatus;
import com.shop.dto.ItemFormDto;
import com.shop.dto.NoticeFormDto;
import com.shop.entity.Item;
import com.shop.entity.ItemImg;
import com.shop.entity.Notice;
import com.shop.repository.ItemRepository;
import com.shop.repository.NoticeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@TestPropertySource(locations="classpath:application-test.properties")
public class NoticeServiceTest {

    @Autowired
    NoticeService noticeService;

    @Autowired
    NoticeRepository noticeRepository;


    @Test
    @DisplayName("상품 등록 테스트")
    @WithMockUser(username = "user", roles = "USER")
    void saveNotice() throws Exception {
        NoticeFormDto noticeFormDto = new NoticeFormDto();
        noticeFormDto.setNoticeNm("테스트상품");
        noticeFormDto.setNoticeDetail("테스트 상품 입니다.");
        noticeFormDto.setWriteName("이름");

        System.out.println("테스트입니다,"+ noticeService.saveNotice(noticeFormDto));

        Long id = noticeService.saveNotice(noticeFormDto);

        Notice notice = noticeRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        assertEquals(noticeFormDto.getNoticeNm(), notice.getNoticeNm());
        assertEquals(noticeFormDto.getNoticeDetail(), notice.getNoticeDetail());
        assertEquals(noticeFormDto.getWriteName(), notice.getWriteName());

    }

}
