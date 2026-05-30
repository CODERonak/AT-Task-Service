package com.example.taskservice.model;

import java.time.Instant;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100) 
    private String title;
    
    @Lob
    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String status;

    @Column(name = "owner_Email", nullable = false) 
    private String ownerEmail;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = Instant.now();
    }

}
