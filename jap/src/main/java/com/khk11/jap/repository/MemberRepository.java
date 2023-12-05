package com.khk11.jap.repository;

import com.khk11.jap.entity.Member02;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member02, String> {
    List<Member02> findByNickName(String nickName);

    List<Member02> findByNickNameOrUserId(String nickName,String userId);

    List<Member02> findByAgeGreaterThan(int age);

    List<Member02> findByAgeGreaterThanOrderByAgeDesc(int age);

    Optional<Member02> findByUserId (String userId);
}
