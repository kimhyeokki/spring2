package com.khk11.jap.service;

import com.khk11.jap.dto.BoardDto;
import com.khk11.jap.entity.Board02;
import com.khk11.jap.exception.DataNotFoundException;
import com.khk11.jap.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    //db에 왔다 갔다 하는
    private final BoardRepository boardRepository;

  /*  public Board02 insertBoard(Board02 board02){
       Board02 board = boardRepository.save(board02);
        return board;
    }*/
    /*public BoardDto insertBoard(BoardDto boardDto){
        Board02 dbBoard02 = Board02.builder()
                .id(boardDto.getId())
                .subject(boardDto.getSubject())
                .content(boardDto.getContent())
                .build();
        Board02 responseBoard = boardRepository.save(dbBoard02);
        BoardDto responseBoardDto = BoardDto.fromEntity(responseBoard);
        return responseBoardDto;
    }*/
  public BoardDto insertBoard(Board02 board02){
      Board02 board = boardRepository.save(board02);
      return BoardDto.fromEntity(board);
  }

    /*public List<BoardDto> getAllBoard() {
        List<Board02> board02List = boardRepository.findAll();
        List<BoardDto> boardList = new ArrayList<>();
        for (int i =0;i<board02List.size();i++)
            boardList.add(BoardDto.fromEntity(board02List.get(i)));
        return  boardList;
    }*/
    public List<Board02> getAllBoard() {
        List<Board02> boardList = boardRepository.findAll();
        return boardList;
    }
    public Page<Board02> getAllPageBoard(int page) {
       Pageable pageable = PageRequest.of(page,10, Sort.by(Sort.Direction.ASC,"createDate"));
        Page<Board02> boardList = boardRepository.findAll(pageable);
        return boardList;
    }

    public Board02 getBoard(int id) {
        Optional<Board02> board = boardRepository.findById(id); //Optional을 반환하고 그래서 isPresent를 통해 있는지 확인후
                                                                // .get()을 통해 가져올 수 있다.
        if(board.isPresent()){
          return board.get();  //entity로 반환함
        }
          throw new DataNotFoundException("찾는 거 없습니다.");
          //  return null;
    }
    public Page<Board02> getSearchBoard(String category,String keyword, int page) {
        Pageable pageable = PageRequest.of(page,10);
        if (category.equals("subject")){
        Page<Board02> boardList = boardRepository.findBySubject(keyword,pageable); //Optional을 반환하고 그래서 isPresent를 통해 있는지 확인
            return boardList;
        } else if(category.equals("content")){
            Page<Board02> boardList = boardRepository.findByContent(keyword,pageable); //Optional을 반환하고 그래서 isPresent를 통해 있는지 확
            return boardList;
        }else if (category.equals("writer")){
            Page<Board02> boardList = boardRepository.findByWriter(keyword,pageable); //Optional을 반환하고 그래서 isPresent를 통해 있는지 확
            return boardList;
        }else {
            Page<Board02> boardList = boardRepository.findByAllCategory(keyword,pageable); //Optional을 반환하고 그래서 isPresent를 통해 있는지 확
            return boardList;
        }
    }
}
