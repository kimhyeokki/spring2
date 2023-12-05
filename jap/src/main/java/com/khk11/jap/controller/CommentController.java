package com.khk11.jap.controller;

import com.khk11.jap.dto.BoardDto;
import com.khk11.jap.dto.CommentDto;
import com.khk11.jap.dto.CustomUserDetails;
import com.khk11.jap.entity.Board02;
import com.khk11.jap.entity.Comment02;
import com.khk11.jap.service.BoardService;
import com.khk11.jap.service.CommentService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final BoardService boardService;

    @PostMapping("/insert/{id}")
    public String insert(@PathVariable("id") int id, @RequestParam String content,
                         @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        Board02 board = boardService.getBoard(id);
   /*     Board02 board02 = Board02.builder()
                .id(board.getId())
                .subject(board.getSubject())
                 .createDate(board.getCreateDate())
                 .commentList(board.getCommentList())
                 .content(board.getContent())
                .build();*/
        commentService.insertComment(board,content,customUserDetails.getLoggedMember());
        return "redirect:/view/{id}";
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Map<String,String> delete(@PathVariable("id") int id){
        commentService.deleteComment(id);
        Map<String,String> resultMap = new HashMap<>();
        resultMap.put("isDelete","ok");
        return resultMap;
    }

    @PostMapping("/ajaxinsert/{id}")
    @ResponseBody
    public Map<String, Object> ajaxInsert(@PathVariable("id") int id,
                                          @RequestParam String content,
                                          @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        Board02 board = boardService.getBoard(id);
       /* Board02 board02 = Board02.builder()
                .id(board.getId())
                .subject(board.getSubject())
                .createDate(board.getCreateDate())
                .commentList(board.getCommentList())
                .content(board.getContent())
                .build();*/
        Comment02 comment02 = commentService.insertAjaxComment(board,content,customUserDetails.getLoggedMember());
        //save된 엔티티를 리턴받아서 데이터를 전달하기 위해서 Dto로 변환해서 사용하잪
        CommentDto responseComment = CommentDto.fromEntity(comment02); //Dto로 변환
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("isInsert","ok");
        resultMap.put("responseComment",responseComment);
        /*resultMap.put("content",responseComment.getContent());
        resultMap.put("id",responseComment.getId());
        resultMap.put("date",responseComment.getCreateDate());*/
        return resultMap;
    }

}
