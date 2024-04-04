package com.springboot.connectmate.controllers;

@RestController
@RequestMapping ("/alerts")
public class AlertController {
    @GetMapping("/")
    public ResponseEntity<List<AlertDTO>> getAlerts(){
        return ResponseEntity.ok(alerts)
    }
    @PostMapping("/")
    public ResponseEntity<AlertAddDTO> addAlert(@RequestBody AlertAddDTo newalerts) {
        return ResponseEntity.ok (newalerts)
    }

    @PutMapping("/")
    public ResponseEntity<AlertModifyDTO> modifyAlert



}
