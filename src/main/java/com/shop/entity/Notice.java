package com.shop.entity;

import com.shop.dto.ItemFormDto;
import com.shop.dto.NoticeFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="notice")
@Getter
@Setter
@ToString
public class Notice extends BaseEntity {

    @Id
    @Column(name="notice_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;  //문의사항코드

    @Column(nullable = false, length = 50)
    private String noticeNm; //제목
    @Lob
    @Column(nullable = false)
    private String noticeDetail; //상세내용

    @Column(nullable = false, length = 50)
    private String writeName; //작성자

    public void updateNotice(NoticeFormDto noticeFormDto){
        this.noticeNm = noticeFormDto.getNoticeNm();
        this.writeName = noticeFormDto.getWriteName();
        this.noticeDetail = noticeFormDto.getNoticeDetail();
    }
}
