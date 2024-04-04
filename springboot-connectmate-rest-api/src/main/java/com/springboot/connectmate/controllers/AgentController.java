package com.springboot.connectmate.controllers;

@RestController
@RequestMapping("/agents")
public class AgentController {
    @GetMapping
    public String getAgents() {
        return "GET request for /agents";
    }
    @GetMapping("/{id}")
    public String getAgent(@PathVarible("id") int id) {
    // public String getAgent(@RequestBody("id") int id) {
        return "GET request for /agent/id";
    }
    @GetMapping("/{id}/metrics")
    public String getAgent() {
        return "GET request for /agent/id/metrics";
    }
    @GetMapping("/{id}/recommendations")
    public String getAgent() {
        return "GET request for /agent/id/recommendations";
    }
    // @PostMapping
    // public String postAgent() {
        // return "POST request for /agent/id/recommendations";
    // }
}
