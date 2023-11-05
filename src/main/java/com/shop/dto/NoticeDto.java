package com.shop.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NoticeDto {
    private Long id;

    private String noticeNm;

    private String noticeDetail;

    private LocalDateTime regTime;

    private LocalDateTime updateTime;

    private String writeName;

}
