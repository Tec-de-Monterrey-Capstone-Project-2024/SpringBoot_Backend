package com.springboot.connectmate.controllers;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.connectmate.services.TestService;
import org.springframework.http.ResponseEntity;

@Hidden
@RestController
public class TestController {
    @Autowired
    private TestService testService;

    @GetMapping("/createMultipleRecords")
    public ResponseEntity<String> createMultipleRecords() {
        testService.createMultipleRecords();
        return ResponseEntity.ok("50 records created successfully");
    }
}
