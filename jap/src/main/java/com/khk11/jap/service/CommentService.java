package com.khk11.jap.service;

import com.khk11.jap.dto.CommentDto;
import com.khk11.jap.entity.Board02;
import com.khk11.jap.entity.Comment02;
import com.khk11.jap.entity.Member02;
import com.khk11.jap.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public void insertComment(Board02 board02, String content, Member02 member02) {
        Comment02 comment =Comment02.builder()
                .content(content)
                .createDate(LocalDateTime.now())
                .board02(board02)
                .writer(member02)
                .build();
        commentRepository.save(comment);
    }

    public Comment02 insertAjaxComment(Board02 board02, String content, Member02 member02) {
        Comment02 comment =Comment02.builder()
                .content(content)
                .createDate(LocalDateTime.now())
                .board02(board02)
                .writer(member02)
                .build();
        return commentRepository.save(comment);
    }
    public void deleteComment(int id){
        commentRepository.deleteById(id);
    }
}
