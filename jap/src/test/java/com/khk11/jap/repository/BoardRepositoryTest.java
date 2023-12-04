package com.khk11.jap.repository;

;
import com.khk11.jap.entity.Board02;
import com.khk11.jap.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;
    @Test
    public void insertBoard02() {
        for(int i=1;i<=120;i++) {
            Board02 dbInsertBoard = Board02.builder()
                    .subject("제목을 들어갑니다."+i)
                    .content("내용이 들어갑니다."+i)
                    .createDate(LocalDateTime.now())
                    .build();
            boardRepository.save(dbInsertBoard);
        }
    }
}