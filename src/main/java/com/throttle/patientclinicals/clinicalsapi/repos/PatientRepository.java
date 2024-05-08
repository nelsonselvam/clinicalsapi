package com.throttle.patientclinicals.clinicalsapi.repos;

import com.throttle.patientclinicals.clinicalsapi.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface represents a JPA repository for managing Patient entities.
 * It extends the JpaRepository interface, providing CRUD operations for the Patient entity.
 *
 * @param <Patient> The type of the entity being managed (Patient).
 * @param <Long> The type of the primary key of the entity (Long).
 */
public interface PatientRepository extends JpaRepository<Patient, Long> {
}