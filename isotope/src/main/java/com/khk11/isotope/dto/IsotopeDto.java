package com.khk11.isotope.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class IsotopeDto {
    private int id;
    @NotBlank(message = "제목은 필수!! 비어 있으면 안됨")
    @Size(min=10,max = 100,message = "최소 5글자 이상 써야함")
    private String title;
    @NotBlank(message = "내용 필수!!")
    @Size(min = 5, message ="5글자 이상 필수!!")
    private String description;

    @PositiveOrZero
    //@Max(value = 5,message = "5이하인 정수를 써주세요")
    @Range(min=0,max = 5,message = "5이하인 정수를 써주세요")
    private Double point;

    private String category;

    private String original;

    private String renamed;
    private String regdate;
    private MultipartFile file;
    //멀티파트 유효성 검사하기!!!
}
