package com.khk11.outstargram.api;

import com.khk11.outstargram.dto.CustomUserDetails;
import com.khk11.outstargram.entity.Image;
import com.khk11.outstargram.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ImageAplController {
    private final ImageService imageService;
    @GetMapping("image")
    private Map<String,Object> loadStroy(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                         @PageableDefault(size = 3) Pageable pageable){
    Map<String,Object> resultMap = new HashMap<>();
    Page<Image> imageList = imageService.loadStory(customUserDetails.getLoggedMember().getId(),pageable);
    resultMap.put("imageList",imageList);
    return resultMap;

    }
}
