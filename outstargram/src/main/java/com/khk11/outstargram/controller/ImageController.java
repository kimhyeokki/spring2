package com.khk11.outstargram.controller;

import com.khk11.outstargram.dto.CustomUserDetails;
import com.khk11.outstargram.dto.ImageUploadDto;
import com.khk11.outstargram.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @GetMapping("/story")
    public String stroy(){
        return "/image/story";
    }

    @GetMapping("/upload")
    public String stroyUpload(){
        return "/image/upload";
    }

    @PostMapping("/upload")
    public String uploadProcess(ImageUploadDto imageUploadDto,
                            @AuthenticationPrincipal CustomUserDetails customUserDetails){
        imageService.upload(imageUploadDto,customUserDetails);
        return "redirect:/member/mypage/"+customUserDetails.getLoggedMember().getId();
    }
}
