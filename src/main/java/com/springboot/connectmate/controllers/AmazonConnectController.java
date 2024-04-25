package com.springboot.connectmate.controllers;

import com.springboot.connectmate.services.AmazonConnectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/amazon-connect")
public class AmazonConnectController {

    private final AmazonConnectService amazonConnectService;

    @Autowired
    public AmazonConnectController(AmazonConnectService amazonConnectService) {
        this.amazonConnectService = amazonConnectService;
    }

    @GetMapping("/instances")
    public ResponseEntity<List<String>> listConnectInstances() {
        return ResponseEntity.ok(amazonConnectService.listConnectInstances());
    }


}
