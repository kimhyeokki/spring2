package com.khk11.jap.dto;

import com.khk11.jap.entity.Board02;
import com.khk11.jap.entity.Comment02;
import com.khk11.jap.entity.Member02;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentDto {
    private Integer id;

    private String content;

    private LocalDateTime createDate;

    private String strCreateDate;

    private Member02 member02;

    private Board02 board02;

    public static CommentDto fromEntity(Comment02 comment02){
        return CommentDto.builder()
                .id(comment02.getId())
                .content(comment02.getContent())
                .createDate(comment02.getCreateDate())
                .member02(comment02.getWriter())
                .strCreateDate(comment02.getCreateDate().format(DateTimeFormatter.ofPattern("YYYY-MM-DD HH:mm:ss")))
                .build();
    }
}