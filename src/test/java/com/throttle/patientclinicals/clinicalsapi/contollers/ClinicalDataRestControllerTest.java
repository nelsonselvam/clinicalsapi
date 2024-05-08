// FILEPATH: /c:/Users/nelso/OneDrive/Documents/My Learning/GitHub Copilot/clinicalsapi/clinicalsapi/src/test/java/com/throttle/patientclinicals/clinicalsapi/controllers/ClinicalDataRestControllerTest.java
package com.throttle.patientclinicals.clinicalsapi.contollers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.throttle.patientclinicals.clinicalsapi.controllers.ClinicalDataRestController;
import com.throttle.patientclinicals.clinicalsapi.models.ClinicalData;
import com.throttle.patientclinicals.clinicalsapi.models.Patient;
import com.throttle.patientclinicals.clinicalsapi.repos.ClinicalDataRepository;
import com.throttle.patientclinicals.clinicalsapi.repos.PatientRepository;

public class ClinicalDataRestControllerTest {

    private ClinicalDataRepository clinicalDataRepository;
    private PatientRepository patientRepository;
    private ClinicalDataRestController clinicalDataRestController;

    @BeforeEach
    public void setup() {
        clinicalDataRepository = mock(ClinicalDataRepository.class);
        patientRepository = mock(PatientRepository.class);
        clinicalDataRestController = new ClinicalDataRestController(clinicalDataRepository, patientRepository);
    }

    @Test
    public void testGetAllClinicalData() {
        when(clinicalDataRepository.findAll()).thenReturn(Arrays.asList(new ClinicalData(), new ClinicalData()));
        assertEquals(2, clinicalDataRestController.getAllClinicalData().size());
    }

    @Test
    public void testGetClinicalDataById() {
        ClinicalData clinicalData = new ClinicalData();
        when(clinicalDataRepository.findById(1L)).thenReturn(Optional.of(clinicalData));
        ResponseEntity<ClinicalData> response = clinicalDataRestController.getClinicalDataById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clinicalData, response.getBody());
    }

    @Test
    public void testGetClinicalDataByIdNotFound() {
        when(clinicalDataRepository.findById(1L)).thenReturn(Optional.empty());
        ResponseEntity<ClinicalData> response = clinicalDataRestController.getClinicalDataById(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testCreateClinicalData() {
        ClinicalData clinicalData = new ClinicalData();
        when(clinicalDataRepository.save(any(ClinicalData.class))).thenReturn(clinicalData);
        assertEquals(clinicalData, clinicalDataRestController.createClinicalData(new ClinicalData()));
    }

    @Test
    public void testUpdateClinicalData() {
        ClinicalData existingData = new ClinicalData();
        ClinicalData newData = new ClinicalData();
        when(clinicalDataRepository.findById(1L)).thenReturn(Optional.of(existingData));
        when(clinicalDataRepository.save(any(ClinicalData.class))).thenReturn(newData);
        ResponseEntity<ClinicalData> response = clinicalDataRestController.updateClinicalData(1L, new ClinicalData());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(newData, response.getBody());
    }

    @Test
    public void testUpdateClinicalDataNotFound() {
        when(clinicalDataRepository.findById(1L)).thenReturn(Optional.empty());
        ResponseEntity<ClinicalData> response = clinicalDataRestController.updateClinicalData(1L, new ClinicalData());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testDeleteClinicalData() {
        ClinicalData clinicalData = new ClinicalData();
        when(clinicalDataRepository.findById(1L)).thenReturn(Optional.of(clinicalData));
        ResponseEntity<?> response = clinicalDataRestController.deleteClinicalData(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(clinicalDataRepository, times(1)).delete(clinicalData);
    }

    @Test
    public void testDeleteClinicalDataNotFound() {
        when(clinicalDataRepository.findById(1L)).thenReturn(Optional.empty());
        ResponseEntity<?> response = clinicalDataRestController.deleteClinicalData(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testSaveClinicalData() {
        ClinicalData clinicalData = new ClinicalData();
        when(patientRepository.findById(1L)).thenReturn(Optional.of(new Patient()));
        when(clinicalDataRepository.save(any(ClinicalData.class))).thenReturn(clinicalData);
        assertEquals(clinicalData, clinicalDataRestController.saveClinicalData(1L, new ClinicalData()));
    }
}