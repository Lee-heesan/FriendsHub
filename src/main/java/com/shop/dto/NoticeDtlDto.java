package com.shop.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NoticeDtlDto {

    private Long id;

    private String writeName;

    private String noticeNm;

    private String noticeDetail;


    @QueryProjection
    public NoticeDtlDto(Long id, String noticeNm, String noticeDetail, String writeName){
        this.id = id;
        this.noticeNm = noticeNm;
        this.noticeDetail = noticeDetail;
        this.writeName = writeName;
    }

}