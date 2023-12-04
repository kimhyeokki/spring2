package com.khk11.todo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CalendarDto {
    private int no;
    private String id;
    private String title;
    private String start;
    private String end;
    private String startTime;
    private String endTime;
    private boolean allDay;
    private String url;
    private String backgroundColor;
    private String textColor;
    private String borderColor;
}