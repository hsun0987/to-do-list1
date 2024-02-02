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
        todos.put(1, new Todo(1, "프로그래머스 3개 풀기", "false"));
        todos.put(2, new Todo(2, "스프링DI 개념 복습", "false"));
        todos.put(3, new Todo(3, "방 청소하기", "false"));
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
    public String remove(@PathVariable int id){
        todos.remove(id);
        return "remove";
    }
}
