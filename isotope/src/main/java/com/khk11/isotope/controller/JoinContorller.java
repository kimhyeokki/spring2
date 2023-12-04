package com.khk11.isotope.controller;

import com.khk11.isotope.dto.MemberJoin;

import com.khk11.isotope.service.MemberJoinService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/member")
@RequiredArgsConstructor
public class JoinContorller {
    private final MemberJoinService memberJoinService;



    @GetMapping("/join")
    public String join(Model model){
        MemberJoin memberJoin = new MemberJoin();
        model.addAttribute("memberJoin",memberJoin);
        return "/signin";
    }

    @PostMapping("/join")
    public String joinProcess(@Valid @ModelAttribute MemberJoin memberJoin,
                              BindingResult bindingResult, Model model){
        if(memberJoin.getAge()>100){
            bindingResult.reject("ageError","너는 살아있냐?");
        }
        if(memberJoin.getName().contains("개새")){
            bindingResult.reject("nameError","비속어 안됨");
        }
        if(memberJoin.getProfile().isEmpty()){
            model.addAttribute("memberJoin",memberJoin);
            bindingResult.addError(new FieldError("profileError","profile","이미지를 입력해주세요"));
            return "/signin";
        }
        if(bindingResult.hasErrors()){
            model.addAttribute("memberJoin",memberJoin);
            return "/signin";
        }
         memberJoinService.insertMember(memberJoin);
        return "redirect:/isotope/main";
    }
    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("memberJoin",new MemberJoin());
        return "/login";
    }
    @PostMapping("/loginProcess")
    public String loginProcess(@ModelAttribute MemberJoin memberJoin){
        int insert = memberJoinService.insertMember(memberJoin);
        return "redirect:/isotope/main";
    }
}
