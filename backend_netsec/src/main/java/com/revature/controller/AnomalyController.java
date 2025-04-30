package com.revature.controller;

import com.revature.models.Anomaly;
import com.revature.services.AnomalyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/anomalies")
public class AnomalyController {

    private final AnomalyService anomalyService;

    public AnomalyController(AnomalyService anomalyService) {
        this.anomalyService = anomalyService;
    }

    @GetMapping
    public List<Anomaly> getAllAnomalies(@RequestParam(required = false) String type,
                                         @RequestParam(required = false) Integer sessionId) {
        if (type != null) {
            return anomalyService.findByType(type);
        }
        if (sessionId != null) {
            return anomalyService.findBySessionId(sessionId);
        }
        return anomalyService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Anomaly> getAnomalyById(@PathVariable Integer id) {
        return anomalyService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Anomaly createAnomaly(@RequestBody Anomaly anomaly) {
        return anomalyService.create(anomaly);
    }

    @PutMapping("/{id}")
    public Anomaly updateAnomaly(@PathVariable Integer id, @RequestBody Anomaly anomaly) {
        return anomalyService.update(id, anomaly);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnomaly(@PathVariable Integer id) {
        anomalyService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
