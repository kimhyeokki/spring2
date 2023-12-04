package com.jjang051.board.controller;

import com.jjang051.board.dto.JoinDto;
import com.jjang051.board.dto.MailDto;
import com.jjang051.board.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/mail")
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;
    @GetMapping("/mail")
    public String mail(){
        return "/mail/mail";
    }
   @PostMapping("/send")
    public String sendMail(@ModelAttribute MailDto mailDto){
        mailService.sendMail(mailDto);
        return "redirect:/";
    }
    @PostMapping("/confirm")
    @ResponseBody
    public Map<String,String> sendAuthMail(String mail){
        String randomNum = mailService.sendMailAuthEmail(mail);
        Map<String, String> map = new HashMap<>();
        map.put("confirmNum",randomNum);
        return map;
    }


}
