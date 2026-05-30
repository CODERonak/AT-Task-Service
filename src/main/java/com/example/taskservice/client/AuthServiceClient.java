package com.example.taskservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "auth-client", url = "${AUTH_SERVICE}")
public interface AuthServiceClient {

    @PostMapping("/auth/validate")
    boolean validateToken(@RequestParam("token") String token);

    @GetMapping("/auth/username")
    String getUsernameFromToken(@RequestParam("token") String token);
}
