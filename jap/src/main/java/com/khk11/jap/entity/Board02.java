package com.khk11.jap.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Entity //붙여줘야 맵핑이 된다.
//@Table(name = "myBoard")   // 테이블의 이름을 지정해서 생성 가능함.
public class Board02 {
    //primary key 가 설정되어야 한다.
    // int는 사용 못함
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)  //시퀀스 생성
    @Column(name = "boardId") // 컬럼명도 이름을 바꿀 수 있다.
    private Integer id;

 //   @Column(name = "mySubject")
    private String subject;
//,columnDefinition = "varchar2"
    @Column(length = 2000 ) // 타입도 설정가능
    private String content;
    private LocalDateTime createDate;

    @OneToMany(mappedBy = "board02", cascade = CascadeType.REMOVE)
    private List<Comment02> commentList;
}
