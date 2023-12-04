package com.khk11.todo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // json변환할때 null을 배제하겠다.
public class TodoDto {
    private Integer no;
    private String todo;
    private String pickedDate;
    private String isDone;
    private  int count;
}