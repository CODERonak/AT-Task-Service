package com.example.taskservice.controller;

import com.example.taskservice.dto.TaskRequest;
import com.example.taskservice.dto.TaskResponse;
import com.example.taskservice.service.TaskService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskRequest taskRequest,
            @RequestAttribute("email") String email) {
        return ResponseEntity.ok(taskService.createTask(taskRequest, email));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable Long id, @RequestAttribute("email") String email) {
        return ResponseEntity.ok(taskService.getTaskById(id, email));
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks(@RequestAttribute("email") String email) {
        return ResponseEntity.ok(taskService.getAllTasks(email));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable Long id, @RequestBody TaskRequest taskRequest,
            @RequestAttribute("email") String email) {
        return ResponseEntity.ok(taskService.updateTask(id, taskRequest, email));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id, @RequestAttribute("email") String email) {
        taskService.deleteTask(id, email);
        return ResponseEntity.noContent().build();
    }
}
