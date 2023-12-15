package com.khk11.outstargram.controller;

import com.khk11.outstargram.dto.CustomUserDetails;
import com.khk11.outstargram.dto.MemberProfileDto;
import com.khk11.outstargram.dto.UpdateMemberDto;
import com.khk11.outstargram.entity.Member;
import com.khk11.outstargram.service.MemberService;
import com.khk11.outstargram.service.SubscribeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final SubscribeService subscribeService;

    @Transactional
    @GetMapping("/mypage/{id}")
    public String mypage(@PathVariable int id, Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        log.info(customUserDetails.getLoggedMember().getUserId());
        log.info(customUserDetails.getLoggedMember().getName());
        //model.addAttribute("memberInfo",customUserDetails.getLoggedMember()); //로그인한 정보만
        int count =subscribeService.subscribeCount(id);
        MemberProfileDto memberInfo = memberService.getProfile(id,customUserDetails.getLoggedMember().getId());
        model.addAttribute("memberInfo",memberInfo);
        model.addAttribute("count",count);

        return  "/member/mypage";
    }

    @GetMapping("/modify/{id}")
    public String modify(@PathVariable int id, Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        log.info("id===={}",id);
        log.info(customUserDetails.getLoggedMember().getUserId());
        log.info(customUserDetails.getLoggedMember().getName());
        model.addAttribute("memberInfo",customUserDetails.getLoggedMember());
        return  "/member/modify";
    }

    @PostMapping("/modify/{id}")
    public String modifyProcess(@PathVariable int id, Model model, UpdateMemberDto updateMemberDto,@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        Member returnMember =memberService.updateMember(id,updateMemberDto);
        customUserDetails.setLoggedMember(returnMember);
        return "redirect:/member/mypage/"+id;
    }
}