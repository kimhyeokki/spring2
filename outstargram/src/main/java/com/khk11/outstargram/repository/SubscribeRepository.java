package com.khk11.outstargram.repository;


import com.khk11.outstargram.entity.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface SubscribeRepository extends JpaRepository<Subscribe, Integer> {

    @Modifying
    @Query(
            value="INSERT INTO SUBSCRIBE " +
                    "(id,fromMemberId,toMemberId, createdate) VALUES (SUBSCRIBE_SEQ.nextval,:fromMemberId,:toMemebrId, sysdate)"
            ,nativeQuery = true)
    void subscribe(@Param("fromMemberId") int fromMemberId, @Param("toMemebrId") int toMemebrId);


    @Modifying
    @Query(
            value="DELETE FROM SUBSCRIBE WHERE fromMemberId = :loggedMemberId AND toMemberId = :urlId",nativeQuery = true)
    void unSubscribe(@Param("loggedMemberId") int loggedMemberId, @Param("urlId") int urlId);


    @Query(value="SELECT COUNT(*) FROM SUBSCRIBE WHERE fromMemberId = :memberId",nativeQuery = true)
    int subscribeCount(@Param("memberId") int memberId);


    @Query(value="SELECT COUNT(*) FROM SUBSCRIBE WHERE fromMemberId = :loggedMemberId and toMemberId = :urlId",nativeQuery = true)
    int subscribeState(@Param("loggedMemberId") int loggedMemberId,@Param("urlId") int urlId );


    // 여기다가
}
