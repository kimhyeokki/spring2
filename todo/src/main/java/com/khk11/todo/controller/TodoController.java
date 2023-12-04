package com.khk11.todo.controller;

import com.khk11.todo.Service.TodoService;
import com.khk11.todo.dto.TodoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;
    @GetMapping({"/","/index"})
    public String index() {
        return "/todo/index";
    }
    @PostMapping("/insert")
    @ResponseBody
    public List<TodoDto> insertTodo(@ModelAttribute TodoDto todoDto) {
        List<TodoDto> todoList = todoService.insertTodo(todoDto);
        return todoList;
    }

    @PostMapping("/list")
    @ResponseBody
    public List<TodoDto> getPickedDateTodo(@ModelAttribute TodoDto todoDto) {
        List<TodoDto> todolist = todoService.getPickedDateTodo(todoDto);
        return todolist;
    }


    @DeleteMapping("/delete")
    @ResponseBody
    public List<TodoDto> deleteTodo(@ModelAttribute TodoDto todoDto) {
        log.info("todoDto==={}",todoDto.toString());
        List<TodoDto> todoList = todoService.deleteTodo(todoDto);
        //Map<String, Integer> resultMap = new HashMap<>();
        //resultMap.put("isDelete",result);
        return todoList;
    }

    @PutMapping("/update")
    @ResponseBody
    public List<TodoDto> updateTodo(@ModelAttribute TodoDto todoDto) {
        log.info("todoDto update==={}",todoDto.toString());
        List<TodoDto> todoList = todoService.updateTodo(todoDto);
        return todoList;
    }

    @GetMapping("/all")
    @ResponseBody
    public List<TodoDto> all(@ModelAttribute TodoDto todoDto) {
        List<TodoDto> dateCountList = todoService.getDateCount();
        return dateCountList;
    }


}
