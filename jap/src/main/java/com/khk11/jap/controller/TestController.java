package com.khk11.jap.controller;

import com.khk11.jap.dto.BoardDto;
import com.khk11.jap.entity.Board02;
import com.khk11.jap.repository.BoardRepository;
import com.khk11.jap.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class TestController {
    @Value("${pagination.size}")
    private int pageNationSize;
    private final BoardService boardService;
/*    @GetMapping("/")
    public String index(){
        Board02 board02 = new Board02();
        board02.setSubject("jpa 사용중");
        board02.setContent("jpa 사용중입니다. 잘되나요??");
       // boardRepository.save(board02); // db에 저장 insert

        return "index";
    }*/
    @GetMapping("/")
    public String index(){

    return "/index";
    }
    @GetMapping("/insert")
    public String insert(){
        return "/insert";
    }
    @PostMapping("/insert")
        public String insertProcess(@ModelAttribute BoardDto boardDto){
        Board02 dbInsertBoard = Board02.builder()
                .subject(boardDto.getSubject())
                .content(boardDto.getContent())
                .createDate(LocalDateTime.now())
                .build();
        boardService.insertBoard(dbInsertBoard);
         return "redirect:/list";
    }

    @GetMapping("/list")
    public String list(Model model){
        List<Board02> boardList = boardService.getAllBoard();
        model.addAttribute("boardList",boardList);
        return "/list";
    }

    @GetMapping("/list02")
    public String pageList(Model model,@RequestParam(value = "page",required = true,defaultValue = "0") int page){
        Page<Board02> pageNation = boardService.getAllPageBoard(page);

        log.info("total==={}",pageNation.getTotalPages());
        List<Board02> boardList = pageNation.getContent(); //getContent()를 통해 리스트로 나타남
        int start = pageNationSize*(int)Math.floor((double) pageNation.getNumber() / pageNationSize) ;
        int end = start+pageNationSize;
        model.addAttribute("start",start);
        model.addAttribute("end",end);
        model.addAttribute("boardList",boardList);
        model.addAttribute("pageNation",pageNation);
        return "/list";
    }





    @GetMapping("/view/{id}")
    private String view(@PathVariable int id, Model model){
        BoardDto  board = boardService.getBoard(id);
        log.info("id==={}",id);
        log.info("commentList==={}",board.getCommentList().size());
        model.addAttribute("board",board);
        return "/view";
    }
}
