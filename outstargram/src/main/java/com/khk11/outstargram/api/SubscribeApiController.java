package com.khk11.outstargram.api;

import com.khk11.outstargram.dto.CustomUserDetails;
import com.khk11.outstargram.service.SubscribeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
public class SubscribeApiController {
    private final SubscribeService subscribeService;
    @PostMapping("/api/subscribe/{toMemberId}")
    public Map<String,Object> subscribe(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                        @PathVariable int toMemberId) {
//        log.info("customUserDetails.getLoggedMember().getId()==={}",customUserDetails.getLoggedMember().getId());
//        log.info("toMemberId==={}",toMemberId);

        subscribeService.subscribe(customUserDetails.getLoggedMember().getId(),toMemberId);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("isSubscribe","ok");
        return resultMap;
    }

    @DeleteMapping("/api/subscribe/{toMemberId}")
    public Map<String,Object> subscribeDelete(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                              @PathVariable int toMemberId) {
//        log.info("customUserDetails.getLoggedMember().getId()==={}",customUserDetails.getLoggedMember().getId());
//        log.info("toMemberId==={}",toMemberId);

        subscribeService.unSubscribe(customUserDetails.getLoggedMember().getId(),toMemberId);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("isUnsubscribe","ok");
        return resultMap;
    }
}