package com.springboot.connectmate.controllers;

@RestController
@RequestMapping("/queue")
public class QueueController {
    @GetMapping
    public String getQueue() {
        return "GET request for /queue";
    }

    @GetMapping("/{id}")
    public String getQueue(@PathVarible("id") int id) {
        return "GET request for /queue/id";
    }
    @GetMapping("/{id}/agent")
    public String getQueue() {
        return "GET request for /queue/id/agent";
    }

}
