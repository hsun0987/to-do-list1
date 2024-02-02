package com.kitri.todolist.todo;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/api/todos")
public class TodoApiController {
    static HashMap<Integer, Todo> todos = new HashMap<>() ;
    static int id = 10;
    static {
        todos.put(1, new Todo(1, "코테 문제 5개 풀기", "false"));
        todos.put(2, new Todo(2, "스프링DI 개념 복습", "false"));
        todos.put(3, new Todo(3, "아시안컵 보기", "false"));
        todos.put(4, new Todo(4, "영어단어 100개 외우기", "true"));
        todos.put(5, new Todo(5, "옥길이랑 밥약속", "false"));
    }
    @GetMapping("")
    public ArrayList<Todo> todos() {
        return new ArrayList<>(todos.values());
    }
    @PostMapping("")
    public boolean add(@RequestBody Todo todo) {
         todo.setId(id);
        return todos.put(id++, todo) != null ? true : false;
    }
    @PutMapping("/{id}")
    public String finish(@RequestBody Todo todo){
        todos.get(todo.id).setDone(todo.done);
        return "done";
    }
    @DeleteMapping("/{id}")
    public String remove(@RequestBody Todo todo){
        todos.remove(todo.id);
        return "remove";
    }
}
