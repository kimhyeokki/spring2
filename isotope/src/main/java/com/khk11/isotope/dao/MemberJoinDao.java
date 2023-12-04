package com.khk11.isotope.dao;


import com.khk11.isotope.dto.MemberJoin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberJoinDao {
    int insertMember(MemberJoin memberJoin);

    MemberJoin loginMember(String username);
}
