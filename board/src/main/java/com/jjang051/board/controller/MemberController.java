package com.jjang051.board.controller;

import com.jjang051.board.dto.JoinDto;
import com.jjang051.board.dto.LoginDto;
import com.jjang051.board.dto.UpdateDto;
import com.jjang051.board.service.MailService;
import com.jjang051.board.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;
    private final MailService mailService;
    @GetMapping("/login")
    public String login( @RequestParam(value = "error", required = false) String error,
                         @RequestParam(value = "exception", required = false) String exception,Model model) {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        model.addAttribute("loginDto",new LoginDto());
        return "/member/login";
    }
    @PostMapping("/loginProcess")
    public String loginProcess(@Valid @ModelAttribute LoginDto loginDto,
                               BindingResult bindingResult,
                               Model model) {
        model.addAttribute("loginDto",loginDto);
        return "/member/login";
    }

    @GetMapping("/join")
    public String join(Model model){
        model.addAttribute("joinDto" ,new JoinDto());
        return "/member/join";
    }

    @PostMapping("/join")
    public String joinProcess(@Valid @ModelAttribute JoinDto joinDto,
                              BindingResult bindingResult,
                              Model model){
        if (bindingResult.hasErrors()){
            return "/member/join";
        }
        model.addAttribute("joinDto" ,joinDto);
        memberService.insertMember(joinDto);
        return "redirect:/member/login";
    }
    @GetMapping("/myPage")
    public String myPage(Model model){
        model.addAttribute("joinDto", new JoinDto());
        return "/member/myPage";
    }
    @GetMapping("/delete")
    public String delete(Model model){
        return "/member/delete";
    }
    @PostMapping("/delete")
    public String deleteProcess(@ModelAttribute LoginDto loginDto, Model model){
        log.info("{}===",loginDto.getId());
        int result = memberService.deleteMember(loginDto);
        if(result>0){
            SecurityContextHolder.getContext().setAuthentication(null);
                return "redirect:/";
        }
        model.addAttribute("error",true);
        model.addAttribute("exception","아이디 패스워드가 틀립니다.");
        return "/member/delete";
    }
    @DeleteMapping("/delete")
    public Map<String,String> deleteAjaxProcess(@ModelAttribute LoginDto loginDto, Model model){
        log.info("{}===",loginDto.getId());
        Map<String,String> map = new HashMap<>();
        int result = memberService.deleteMember(loginDto);
        if(result>0){
            SecurityContextHolder.getContext().setAuthentication(null);
            map.put("isState","ok");
            return map;
        }
        model.addAttribute("error",true);
        model.addAttribute("exception","아이디 패스워드가 틀립니다.");
        map.put("isState","fail");
        return map;
    }

    @GetMapping("/modify")
    public String modify(){
        return "member/modify";
    }
    @PostMapping("/modify")
    public String modifyProcess(@ModelAttribute JoinDto joinDto, Model model){
        log.info("joinDto==={}",joinDto.toString());
        int result = memberService.updateMember(joinDto);
        if(result>0){
            SecurityContextHolder.getContext().setAuthentication(null);
            return "redirect:/";
        }
        model.addAttribute("error",true);
        model.addAttribute("exception","아이디 패스워드가 틀립니다.");

        return "member/modify";
    }
    @PutMapping("/modify")
    @ResponseBody
    public Map<String,String> modifyAjaxProcess(@ModelAttribute JoinDto joinDto){
        log.info("joinDto==={}",joinDto);
        Map<String,String> map = new HashMap<>();
        int result = memberService.updateMember(joinDto);
        if(result>0){
            SecurityContextHolder.getContext().setAuthentication(null);

            map.put("isState","ok");
            return map;
        }
        map.put("isState","fail");
        return map;
    }

    @GetMapping("/findPassword")
    public String findPassword(){
        //이메일을 입력해서 비밀번호 입력받기

        return "/member/findPassword";
    }

    @PostMapping("/findPassword")
    public String findPassword(UpdateDto updateDto){
        log.info("mail==={}",updateDto);
        mailService.sendMailAndChangePassword(updateDto);
        return "redirect:/member/login";
    }
}
