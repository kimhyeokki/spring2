package com.khk11.jap.controller;

import com.khk11.jap.dto.MemberDto;
import com.khk11.jap.entity.Member02;
import com.khk11.jap.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor

@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/join")
    public String join(){
        return "/member/join";
    }
    @PostMapping("/join")
    public String joinProcess(MemberDto memberDto){
        memberService.join(memberDto);
        return "redirect:/member/list";
    }

    @GetMapping("/list")
    public String list(Model model){
        List<MemberDto> memberList = memberService.getAllMember();
        model.addAttribute("memberList",memberList);
        return "/member/list";
    }

    @GetMapping("/mypage")
    public String mypage(@RequestParam String id , Model model){
        MemberDto memberInfo = memberService.getMemberInfo(id);
        model.addAttribute("memberInfo",memberInfo);
        return "/member/mypage";
    }
    @GetMapping("/modify")
    public String modify(@RequestParam String id,Model model) {
        MemberDto memberInfo = memberService.getMemberInfo(id);
        model.addAttribute("memberInfo",memberInfo);
        return "/member/modify";
    }


    @PostMapping("/modify")
    public String modifyProcess(@ModelAttribute MemberDto memberDto, Model model) {
        MemberDto memberInfo = memberService.modifyMember(memberDto);
        //model.addAttribute("memberInfo",memberInfo);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam String id) {
        memberService.deleteMember(id);
        return "redirect:/member/list";
    }
}
