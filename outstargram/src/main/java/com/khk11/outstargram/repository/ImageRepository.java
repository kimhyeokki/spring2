package com.khk11.outstargram.repository;

import com.khk11.outstargram.entity.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image,Integer> {

    @Query(value = "SELECT * FROM  IMAGE WHERE MEMBER_ID IN " +
            "(SELECT ToMemberId FROM SUBSCRIBE WHERE fromMemberId = :customUserDetailsId)",
            nativeQuery = true)
    Page<Image> loadStroy(@Param("customUserDetailsId") int customUserDetailsId, Pageable pageable);
    // 변수명이 같으면 @Param 생략가능하다.
}