package com.khk11.jap.repository;

import com.khk11.jap.entity.Board02;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Query("select b from Board02 b where b.subject like %:keyword%")
    //*은 못쓰기 때문에 이름을 부여해서 사용해야 한다.
    //매개변수를 쓰려면 변수라는 이름으로 : 을 붙여야 한다.
    Page<Board02> findBySubject(@Param("keyword") String keyword,Pageable pageable);
    @Query("select b from Board02 b where b.content like %:keyword%")
    Page<Board02> findByContent(@Param("keyword") String keyword,Pageable pageable);
    @Query("select b from Board02 b where b.writer.nickName like %:keyword%")
    Page<Board02> findByWriter(@Param("keyword") String keyword,Pageable pageable);

    @Query(value = "select * from Board02 b where b.subject like %:keyword%" , nativeQuery = true)
        //nativeQuery true를 쓰면 *를 쓸수 있다.
        //default 값은 false로 되어있음
    Page<Board02> findBySubjectNative(@Param("keyword") String keyword,Pageable pageable);

    /*@Modifying
    //select을 제외한 나머지 구문(DML)은 반드시 @Modifying을 붙여야 한다,
    @Query(value = "insert into Board02",nativeQuery = true)
    Page<Board02> insertBoard(@Param("keyword") String keyword,Pageable pageable);*/
}

