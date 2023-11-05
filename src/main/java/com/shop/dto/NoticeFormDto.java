package com.shop.dto;

import com.shop.entity.Notice;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
public class NoticeFormDto {

    private Long id;

    @NotBlank(message = "제목은 필수 입력 값입니다.")
    private String noticeNm;
    @NotBlank(message = "문의사항 내용은 필수 입력 값입니다.")
    private String noticeDetail;

    private LocalDateTime regTime;

    private LocalDateTime updateTime;
    @NotBlank(message = "작성자 이름은 필수 입력 값입니다.")
    private String writeName;

    private static ModelMapper modelMapper = new ModelMapper();

    public Notice createNotice(){
        return modelMapper.map(this, Notice.class);
    }

    public static NoticeFormDto of(Notice notice){
        return modelMapper.map(notice,NoticeFormDto.class);
    }
}
