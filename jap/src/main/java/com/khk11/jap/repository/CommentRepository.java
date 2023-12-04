package com.khk11.jap.repository;

import com.khk11.jap.entity.Comment02;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment02, Integer> {
}
