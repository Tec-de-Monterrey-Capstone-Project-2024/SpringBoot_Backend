package com.springboot.connectmate.controllers;
@RestController
@RequestMapping("/api/alerts")
@Tag(
        name = "Alert REST API",
        description = "CRUD REST API for Alerts"
)
public class AlertController {

    // Create Alert
    @Operation(
            summary = "Create Alert",
            description = "Creates a New Alert"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Alert created successfully"
    )
    @PostMapping
    public ResponseEntity<String> createAlert(@RequestBody AlertDTO alert) {
        return new ResponseEntity<>("Alert created successfully", HttpStatus.OK);
    }

    // Get All Alerts Rest API
    @Operation(
            summary = "Get All Alerts",
            description = "Gets All Alerts"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Alerts fetched successfully"
    )


    // Get Alert by ID API
    @Operation(
            summary = "Get Alert by ID",
            description = "Gets a specific alert by its ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Alert fetched successfully"
    )
    @GetMapping("/{alertId}")
    public AlertDTO getAlertById(@PathVariable Long alertId) {
        AlertDTO alert = new AlertDTO();

        alert.setId(0L);
        alert.setMetricID(alertId);
        alert.setName("Low Service Level");
        alert.setDescription("Service level is low");
        alert.setType("Service Level");
        alert.setStatus("Open");
        alert.setSeverity(AlertDTO.severity.LOW);
        alert.setMinThreshold(90L);
        alert.setMaxThreshold(100L);
        alert.setSupervisor("John Doe");
        alert.setAgent("Jane Doe");
        alert.setCreatedAt(LocalDateTime.parse("2007-12-03T10:15:30"));
        alert.setUpdatedAt(LocalDateTime.parse("2007-12-03T10:15:31"));

        return alert;
    }


// Get All Alerts Rest API
@Operation(
        summary = "Get All Alerts",
        description = "Gets All Alerts"
)
@ApiResponse(
        responseCode = "200",
        description = "Alerts fetched successfully"
)
@GetMapping
public List<AlertDTO> getAllAlerts() {
    return List.of(new AlertDTO());
}

// Second method with a different name
@GetMapping("/highSeverity")
public List<AlertDTO> getAllHighSeverityAlerts() {
    // Implementation here to fetch all alerts
    // For now, we're just returning a list of alerts with a severity of HIGH
    return List.of(
            new AlertDTO(1L, 1L, "High CPU Usage", "CPU usage is above 90%", "CPU", "Open", AlertDTO.severity.HIGH, 80L, 90L, "John Doe", "Jane Doe", LocalDateTime.parse("2021-12-31T23:59:59"), LocalDateTime.parse("2021-12-31T23:59:59")),
            new AlertDTO(2L, 2L, "High Memory Usage", "Memory usage is above 90%", "Memory", "Open", AlertDTO.severity.HIGH, 80L, 90L, "John Doe", "Jane Doe", LocalDateTime.parse("2021-12-31T23:59:59"), LocalDateTime.parse("2021-12-31T23:59:59"))
    );
}
}