package com.khk11.jap.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@SequenceGenerator(  //시퀀스 새로 생성
        name = "comment_seq_generator",
        sequenceName = "new_comment_seq",
        initialValue = 1,
        allocationSize = 1
)
public class Comment02 {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "comment_seq_generator")
    private Integer id;

    @Column(length = 2000)
    private String content;

    private LocalDateTime createDate;

    @ManyToOne
    @JoinColumn(name = "boardId")
    private Board02 board02;
}