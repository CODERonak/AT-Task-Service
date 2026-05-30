package com.example.taskservice.service;

import com.example.taskservice.dto.TaskRequest;
import com.example.taskservice.dto.TaskResponse;
import com.example.taskservice.model.Task;
import com.example.taskservice.repository.TaskRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskResponse createTask(TaskRequest taskRequest, String email) {
        Task task = new Task();
        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        task.setOwnerEmail(email);
        task.setStatus("PENDING");
        taskRepository.save(task);

        return new TaskResponse(task.getTitle(), task.getDescription(), task.getStatus());
    }

    public TaskResponse getTaskById(Long id, String email) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        if (!task.getOwnerEmail().equals(email)) {
            throw new RuntimeException("You are not authorized to view this task");
        }
        return new TaskResponse(task.getTitle(), task.getDescription(), task.getStatus());
    }

    public List<TaskResponse> getAllTasks(String email) {
        List<Task> tasks = taskRepository.findByOwnerEmail(email);
        return tasks.stream()
                .map(task -> new TaskResponse(task.getTitle(), task.getDescription(), task.getStatus()))
                .collect(Collectors.toList());
    }

    public TaskResponse updateTask(Long id, TaskRequest taskRequest, String email) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        if (!task.getOwnerEmail().equals(email)) {
            throw new RuntimeException("You are not authorized to update this task");
        }
        
        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());

        taskRepository.save(task);

        return new TaskResponse(task.getTitle(), task.getDescription(), task.getStatus());
    }

    public void deleteTask(Long id, String email) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        if (!task.getOwnerEmail().equals(email)) {
            throw new RuntimeException("You are not authorized to delete this task");
        }
        taskRepository.deleteById(id);
    }

}
