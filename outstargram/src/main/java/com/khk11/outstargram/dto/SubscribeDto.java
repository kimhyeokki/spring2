package com.khk11.outstargram.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SubscribeDto {
    private Integer id;
    private String profileImageUrl;
    private String name;
    private Character subscribeState;
    private Character equalState;
}
