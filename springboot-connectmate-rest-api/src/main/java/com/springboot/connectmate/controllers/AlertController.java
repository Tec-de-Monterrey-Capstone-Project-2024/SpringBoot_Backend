package com.springboot.connectmate.controllers;

@RestController
@RequestMapping ("/alerts")
public class AlertController {
    @GetMapping
    //public String getAlerts()
    }
    @GetMapping("/{id}")
    //public ResponseEntity<List<AlertDTO>> getAlert(){
        //return ResponseEntity.ok(alerts)
    }
    @PostMapping
    //public ResponseEntity<AlertAddDTO> addAlert(@RequestBody AlertAddDTo newalerts) {
        //return ResponseEntity.ok (newalerts)
    }

}
