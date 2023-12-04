package com.khk11.jap.dto;

import com.khk11.jap.entity.Board02;
import com.khk11.jap.entity.Comment02;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.springframework.data.repository.cdi.Eager;

import java.time.LocalDateTime;
import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BoardDto {

    private Integer id;

    private String subject;

    private String content;

    private LocalDateTime createDate;

    @OneToMany(mappedBy = "board02", cascade = CascadeType.REMOVE)
    private List<Comment02> commentList;

    public static BoardDto fromEntity(Board02 board02){
        return BoardDto.builder()
                .id(board02.getId())
                .subject(board02.getSubject())
                .content(board02.getContent())
                .createDate(board02.getCreateDate())
                .commentList(board02.getCommentList())
                .build();
    }
}
