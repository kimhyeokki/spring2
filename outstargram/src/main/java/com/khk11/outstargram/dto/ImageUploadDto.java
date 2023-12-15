package com.khk11.outstargram.dto;

import com.khk11.outstargram.entity.Image;
import com.khk11.outstargram.entity.Member;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ImageUploadDto {
    private MultipartFile file;
    private String caption;

    public Image toEntity(String imgUrl, Member member){
        //Dto를 엔티티로 만듬
        return Image.builder()
                .caption(caption)
                .imgUrl(imgUrl)
                .member(member)
                .build();

    }
    //save Entity


}
