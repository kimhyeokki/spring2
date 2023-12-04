package com.khk11.jap.repository;

import com.khk11.jap.entity.Board02;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //컨테이너에 등록
public interface BoardRepository extends JpaRepository<Board02,Integer> { //클래스명, primary key의 타입
    // select는 == find
    // insert는 == save
    // update는 == save
    // delete는 == delete
    // findByEmail 도 사용 가능 컬럼명을 붙여서
    //jpa query를 통해 복잡한 쿼리문을 개발자가 직접쓸 수 있다.

    //Query("직접 입력가능")

    @Override
    Page<Board02> findAll(Pageable pageable);
}

