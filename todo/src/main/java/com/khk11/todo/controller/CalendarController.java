package com.khk11.todo.controller;

import com.khk11.todo.Service.CalendarService;
import com.khk11.todo.dto.CalendarDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/calendar")
public class CalendarController {
    private final CalendarService calendarService;

    @GetMapping("/")
    public String calendar(Model model) {
        List<CalendarDto> calendarDtoList = calendarService.getAllCalendar();
        model.addAttribute("calendarDtoList",calendarDtoList);
        return "/todo/calendar";
    }
    @GetMapping("/form")
    public String calendarForm() {
        return "/todo/calendarForm";
    }

    @PostMapping("/todo")

    public String calendarTodo(@ModelAttribute CalendarDto calendarDto) {
        log.info("calendarDto.toString()==={}",calendarDto.toString());
        CalendarDto dbInserCalendarDto = CalendarDto.builder()
                .id(calendarDto.getId())
                .start(calendarDto.getStart()+" "+calendarDto.getStartTime())
                .end(calendarDto.getEnd()+" "+calendarDto.getEndTime())
                .allDay(calendarDto.isAllDay())
                .title(calendarDto.getTitle())
                .url(calendarDto.getUrl())
                .backgroundColor(calendarDto.getBackgroundColor())
                .borderColor(calendarDto.getBackgroundColor())
                .build();
        calendarService.insertCalendar(dbInserCalendarDto);
        return "redirect:/calendar/";
    }

    @PostMapping("/modalTodo")
    @ResponseBody
    public Map<String, Object> modalTodo(@ModelAttribute CalendarDto calendarDto) {
        log.info("calendarDto.toString()==={}",calendarDto.toString());
        CalendarDto dbInserCalendarDto = CalendarDto.builder()
                .id(calendarDto.getId())
                .start(calendarDto.getStart()+" "+calendarDto.getStartTime())
                .end(calendarDto.getEnd()+" "+calendarDto.getEndTime())
                .allDay(calendarDto.isAllDay())
                .title(calendarDto.getTitle())
                .url(calendarDto.getUrl())
                .backgroundColor(calendarDto.getBackgroundColor())
                .borderColor(calendarDto.getBackgroundColor())
                .build();
        List<CalendarDto> calendarDtoList = calendarService.insertAjaxCalendar(dbInserCalendarDto);
        Map<String, Object> map = new HashMap<>();
        map.put("isInsert","ok");
        map.put("calendarDtoList",calendarDtoList);
        return map;
    }
}