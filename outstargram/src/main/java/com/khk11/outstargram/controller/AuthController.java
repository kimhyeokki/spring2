package com.khk11.outstargram.controller;

import com.khk11.outstargram.dto.JoinDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @GetMapping("/login")
    public String login(){
        return "/auth/login";
    }

    @GetMapping("/join")
    public String join(Model model, JoinDto joinDto){
        model.addAttribute("joinDto",joinDto);
        return "/auth/join";
    }

    @PostMapping("/join")
    public String joinProcess(){

        return "redirect:/";
    }
}
