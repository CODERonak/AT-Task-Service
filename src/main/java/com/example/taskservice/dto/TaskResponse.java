package com.example.taskservice.dto;

public record TaskResponse(
        String title,
        String description,
        String status) {
}
