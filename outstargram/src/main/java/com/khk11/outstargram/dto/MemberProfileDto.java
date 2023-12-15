package com.khk11.outstargram.dto;

import com.khk11.outstargram.entity.Member;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberProfileDto {

    private boolean pageOwner;
    private Member member;
    private int subscribeCount;
    private boolean subscribeState;
}
