package com.khk11.todo.Service;

import com.khk11.todo.dao.CalendarDao;
import com.khk11.todo.dto.CalendarDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CalendarService {
    private final CalendarDao calendarDao;

    @Transactional
    public int insertCalendar(CalendarDto calendarDto) {
        log.info("calendarDto==={}",calendarDto.toString());
        int result  = calendarDao.insertCalendar(calendarDto);
        return result;
    }

    @Transactional
    public List<CalendarDto> getAllCalendar() {
        List<CalendarDto> calendarDtoList  = calendarDao.getAllCalendar();
        return calendarDtoList;
    }
    @Transactional
    public List<CalendarDto> insertAjaxCalendar(CalendarDto calendarDto){
        int result  = calendarDao.insertCalendar(calendarDto);
        if(result>0){
            List<CalendarDto> calendarDtoList  = calendarDao.getAllCalendar();
            return calendarDtoList;
        }
        return null;
    }
}
