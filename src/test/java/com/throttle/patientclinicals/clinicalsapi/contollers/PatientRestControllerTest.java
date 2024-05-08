// FILEPATH: /c:/Users/nelso/OneDrive/Documents/My Learning/GitHub Copilot/clinicalsapi/clinicalsapi/src/test/java/com/throttle/patientclinicals/clinicalsapi/controllers/PatientRestControllerTest.java
package com.throttle.patientclinicals.clinicalsapi.contollers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.throttle.patientclinicals.clinicalsapi.models.Patient;
import com.throttle.patientclinicals.clinicalsapi.repos.PatientRepository;
import com.throttle.patientclinicals.clinicalsapi.controllers.PatientRestController;

public class PatientRestControllerTest {

    private PatientRepository patientRepository;
    private PatientRestController patientRestController;

    @BeforeEach
    public void setup() {
        patientRepository = mock(PatientRepository.class);
        patientRestController = new PatientRestController(patientRepository);
    }

    @Test
    public void testGetAllPatients() {
        when(patientRepository.findAll()).thenReturn(Arrays.asList(new Patient(), new Patient()));
        assertEquals(2, patientRestController.getAllPatients().size());
    }

    @Test
    public void testGetPatientById() {
        Patient patient = new Patient();
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        ResponseEntity<Patient> response = patientRestController.getPatientById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(patient, response.getBody());
    }

    @Test
    public void testGetPatientByIdNotFound() {
        when(patientRepository.findById(1L)).thenReturn(Optional.empty());
        ResponseEntity<Patient> response = patientRestController.getPatientById(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testCreatePatient() {
        Patient patient = new Patient();
        when(patientRepository.save(any(Patient.class))).thenReturn(patient);
        assertEquals(patient, patientRestController.createPatient(new Patient()));
    }

    @Test
    public void testUpdatePatient() {
        Patient existingPatient = new Patient();
        Patient newPatient = new Patient();
        when(patientRepository.findById(1L)).thenReturn(Optional.of(existingPatient));
        when(patientRepository.save(any(Patient.class))).thenReturn(newPatient);
        ResponseEntity<Patient> response = patientRestController.updatePatient(1L, new Patient());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(newPatient, response.getBody());
    }

    @Test
    public void testUpdatePatientNotFound() {
        when(patientRepository.findById(1L)).thenReturn(Optional.empty());
        ResponseEntity<Patient> response = patientRestController.updatePatient(1L, new Patient());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testDeletePatient() {
        Patient patient = new Patient();
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        ResponseEntity<?> response = patientRestController.deletePatient(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(patientRepository, times(1)).delete(patient);
    }

    @Test
    public void testDeletePatientNotFound() {
        when(patientRepository.findById(1L)).thenReturn(Optional.empty());
        ResponseEntity<?> response = patientRestController.deletePatient(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}