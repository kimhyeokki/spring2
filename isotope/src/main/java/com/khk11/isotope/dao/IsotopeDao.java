package com.khk11.isotope.dao;

import com.khk11.isotope.dto.IsotopeDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IsotopeDao {

    int insertIsotope(IsotopeDto isotopeDto);

    List<IsotopeDto> getAllList();
}
