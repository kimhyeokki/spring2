package com.khk11.todo.Service;

import com.khk11.todo.dao.TodoDao;
import com.khk11.todo.dto.TodoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoDao todoDao;


    @Transactional
    public List<TodoDto> insertTodo(TodoDto todoDto) {
        int result  = todoDao.insertTodo(todoDto);
        List<TodoDto> todoList = getPickedDateTodo(todoDto);
        return todoList;
    }

    // 해당하는 날짜의
    @Transactional
    public List<TodoDto> getPickedDateTodo(TodoDto todoDto) {
        List<TodoDto> todoList  = todoDao.getPickedDateTodo(todoDto);
        return todoList;
    }
    @Transactional
    public List<TodoDto> deleteTodo(TodoDto todoDto) {
        int result  = todoDao.deleteTodo(todoDto);
        List<TodoDto> todoList  = todoDao.getPickedDateTodo(todoDto);
        return todoList;
        //return result;
    }

    @Transactional
    public List<TodoDto> updateTodo(TodoDto todoDto) {
        int result  = todoDao.updateTodo(todoDto);
        List<TodoDto> todoList  = todoDao.getPickedDateTodo(todoDto);
        return todoList;
    }

    public List<TodoDto> getDateCount() {
        // count,pickedDate, no
        List<TodoDto> todoList  = todoDao.getDateCount();
        return todoList;
    }

}
