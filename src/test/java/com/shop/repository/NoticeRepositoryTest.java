package com.shop.repository;

import com.shop.constant.ItemSellStatus;
import com.shop.entity.Item;
import com.shop.entity.Notice;
import org.aspectj.weaver.ast.Not;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class NoticeRepositoryTest {

    @Autowired
    NoticeRepository noticeRepository;



    @Test
    @DisplayName("문의사항 저장 테스트")
    public void createNoticeTest(){
        Notice notice = new Notice();
        notice.setNoticeNm("테스트상품" + 1);
        notice.setNoticeDetail("테스트 상품 상세 설명"+1);
        notice.setRegTime(LocalDateTime.now());
        notice.setUpdateTime(LocalDateTime.now());
        notice.setWriteName("희산");
        Notice saveNotice=noticeRepository.save(notice);
        System.out.println(saveNotice.toString());
    }

    public void createNoticeList() {
        for (int i=1; i<=10; i++) {
            Notice notice = new Notice();
            notice.setNoticeNm("테스트상품" + i);
            notice.setNoticeDetail("테스트 상품 상세 설명"+i);
            notice.setRegTime(LocalDateTime.now());
            notice.setUpdateTime(LocalDateTime.now());
            notice.setWriteName("희산");
            Notice saveNotice=noticeRepository.save(notice);
        }
    }

    @Test
    @DisplayName("상품명 조회 테스트")
    public void findByNoticeNmTest() {
        this.createNoticeList();
        List<Notice> noticeList=noticeRepository.findByNoticeNm("테스트상품1");
        for (Notice notice : noticeList){
            System.out.println(notice.toString());
        }
    }

    @Test
    @DisplayName("상품명, 상품상세설명 OR 테스트")
    public void  findByNoticeNmOrNoticeDetailTest() {
        this.createNoticeList();
        List<Notice> noticeList=noticeRepository.findByNoticeNmOrNoticeDetail("테스트 상품","테스트 상품 상세 설명5");
        for (Notice notice : noticeList){
            System.out.println(notice.toString());
        }
    }

    @Test
    @DisplayName("@Query를 이용한 상품 조회 테스트")
    public void findByNoticeDetailTest(){
        this.createNoticeList();
        List<Notice> noticeList=noticeRepository.findByNoticeDetail("테스트 상품 상세 설명");
        for(Notice notice : noticeList){
            System.out.println(notice.toString());
        }
    }


}
