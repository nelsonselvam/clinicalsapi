package com.throttle.patientclinicals.clinicalsapi.controllers;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.throttle.patientclinicals.clinicalsapi.models.ClinicalData;
import com.throttle.patientclinicals.clinicalsapi.repos.ClinicalDataRepository;
import com.throttle.patientclinicals.clinicalsapi.repos.PatientRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/clinicaldata")
public class ClinicalDataRestController {

    private final ClinicalDataRepository clinicalDataRepository;

    private final PatientRepository patientRepository;

    public ClinicalDataRestController(ClinicalDataRepository clinicalDataRepository, PatientRepository patientRepository) {
        this.clinicalDataRepository = clinicalDataRepository;
        this.patientRepository = patientRepository;
    }

    @GetMapping
    public List<ClinicalData> getAllClinicalData() {
        return clinicalDataRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClinicalData> getClinicalDataById(@PathVariable Long id) {
        return clinicalDataRepository.findById(id)
                .map(data -> ResponseEntity.ok().body(data))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ClinicalData createClinicalData(@RequestBody ClinicalData clinicalData) {
        return clinicalDataRepository.save(clinicalData);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClinicalData> updateClinicalData(@PathVariable Long id, @RequestBody ClinicalData clinicalData) {
        return clinicalDataRepository.findById(id)
                .map(existingData -> {
                    existingData.setComponentValue(clinicalData.getComponentValue());
                    existingData.setMeasuredDateTime(clinicalData.getMeasuredDateTime());
                    return ResponseEntity.ok().body(clinicalDataRepository.save(existingData));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClinicalData(@PathVariable Long id) {
        return clinicalDataRepository.findById(id)
                .map(data -> {
                    clinicalDataRepository.delete(data);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // method that receives a patient id, clinical data as request body and saves it to the database
    @PostMapping("/patient/{id}")
    public ClinicalData saveClinicalData(@PathVariable Long id, @RequestBody ClinicalData clinicalData) {
        patientRepository.findById(id).ifPresent(clinicalData::setPatient);
        return clinicalDataRepository.save(clinicalData);
    }

}