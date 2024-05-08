package com.throttle.patientclinicals.clinicalsapi.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.throttle.patientclinicals.clinicalsapi.models.Patient;
import com.throttle.patientclinicals.clinicalsapi.repos.PatientRepository;

/**
 * This class represents the REST controller for managing patients.
 * It handles HTTP requests related to patients and interacts with the PatientRepository.
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/patients")
public class PatientRestController {

    private final PatientRepository patientRepository;

    private static final Logger logger = LoggerFactory.getLogger(PatientRestController.class);

    /**
     * Constructs a new PatientRestController with the given PatientRepository.
     * 
     * @param patientRepository the repository for managing patients
     */
    public PatientRestController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    /**
     * Retrieves all patients.
     * 
     * @return a list of all patients
     */
    @GetMapping
    public List<Patient> getAllPatients() {
        logger.info("Getting all patients");
        return patientRepository.findAll();
    }

    /**
     * Retrieves a patient by their ID.
     * 
     * @param id the ID of the patient to retrieve
     * @return the patient with the specified ID, or a 404 Not Found response if the patient does not exist
     */
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        logger.info("Getting patient with id: {}", id);
        return patientRepository.findById(id)
                .map(patient -> ResponseEntity.ok().body(patient))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Creates a new patient.
     * 
     * @param patient the patient to create
     * @return the created patient
     */
    @PostMapping
    public Patient createPatient(@RequestBody Patient patient) {
        return patientRepository.save(patient);
    }

    /**
     * Updates an existing patient.
     * 
     * @param id the ID of the patient to update
     * @param patient the updated patient information
     * @return the updated patient, or a 404 Not Found response if the patient does not exist
     */
    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
        return patientRepository.findById(id)
                .map(existingPatient -> {
                    existingPatient.setFirstName(patient.getFirstName());
                    existingPatient.setLastName(patient.getLastName());
                    existingPatient.setAge(patient.getAge());
                    // add other fields to be updated as necessary
                    return ResponseEntity.ok().body(patientRepository.save(existingPatient));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Deletes a patient by their ID.
     * 
     * @param id the ID of the patient to delete
     * @return a 200 OK response if the patient was successfully deleted, or a 404 Not Found response if the patient does not exist
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable Long id) {
        return patientRepository.findById(id)
                .map(patient -> {
                    patientRepository.delete(patient);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}