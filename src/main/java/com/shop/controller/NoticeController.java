package com.shop.controller;


import com.shop.dto.*;
import com.shop.entity.Item;
import com.shop.entity.Notice;
import com.shop.entity.QNotice;
import com.shop.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;


    @GetMapping("/notice/new")
    public String NoticeForm(Model model) {
        model.addAttribute("noticeFormDto",new NoticeDto());
        return "notice/noticeForm";
    }


    @PostMapping(value = "/notice/new")
    public String noticeNew(@Valid NoticeFormDto noticeFormDto, BindingResult bindingResult,
                            Model model){


        if(bindingResult.hasErrors()){
            model.addAttribute("errorMessage", "a다시 시도 해주세요");
            return "notice/noticeForm";
        }


        try {
            noticeService.saveNotice(noticeFormDto);
        } catch (Exception e){
            model.addAttribute("errorMessage", "등록 중 에러가 발생하였습니다.");
            return "notice/noticeForm";
        }

        return "redirect:/";
    }


    @GetMapping(value = "/notice/{noticeId}")
    public String noticeDtl(@PathVariable("noticeId") Long noticeId, Model model){

        try {
            NoticeFormDto noticeFormDto = noticeService.getNoticeDtl(noticeId);
            model.addAttribute("noticeFormDto", noticeFormDto);
        } catch(EntityNotFoundException e){
            model.addAttribute("errorMessage", "존재하지 않는 상품 입니다.");
            model.addAttribute("noticeFormDto", new NoticeFormDto());
            return "notice/noticeForm";
        }

        return "notice/noticeForm";
    }

    @PostMapping(value = "/notice/{noticeId}")
    public String noticeUpdate(@Valid NoticeFormDto noticeFormDto, BindingResult bindingResult
                             , Model model){
        if(bindingResult.hasErrors()){
            return "notice/noticeForm";
        }


        try {
            noticeService.updateItem(noticeFormDto);
        } catch (Exception e){
            model.addAttribute("errorMessage", "상품 수정 중 에러가 발생하였습니다.");
            return "notice/noticeForm";
        }

        return "redirect:/";
    }

    @GetMapping(value = {"/notices", "/notices/{page}"})
    public String noticeManage(NoticeSearchDto noticeSearchDto, @PathVariable("page") Optional<Integer> page, Model model){

        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 10);
        Page<Notice> notices = noticeService.getNoticePage(noticeSearchDto,pageable);


        model.addAttribute("notices", notices);
        model.addAttribute("noticeSearchDto", noticeSearchDto);
        model.addAttribute("maxPage", 5);

        return "notice/noticeMng";
    }

    @GetMapping("/notice/noticeDtl/{noticeId}")
    public String showNoticeDtl(Model model, @PathVariable("noticeId") Long noticeId) {

        NoticeFormDto noticeFormDto = noticeService.getNoticeDtl(noticeId);
        model.addAttribute("notice",noticeFormDto);
        return "notice/noticeDtl";
    }

}
