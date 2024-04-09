package com.springboot.connectmate.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
@Tag(
        name = "Task REST API",
        description = "CRUD REST API for Tasks"
)
public class TaskController {
}
