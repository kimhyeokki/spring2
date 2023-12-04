package com.khk11.todo.dao;

import com.khk11.todo.dto.CalendarDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CalendarDao {
    int insertCalendar(CalendarDto CalendarDto);
    List<CalendarDto> getAllCalendar();
}