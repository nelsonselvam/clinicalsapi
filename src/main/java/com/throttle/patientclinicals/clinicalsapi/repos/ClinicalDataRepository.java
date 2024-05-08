package com.throttle.patientclinicals.clinicalsapi.repos;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.throttle.patientclinicals.clinicalsapi.models.ClinicalData;

/**
 * This interface represents a repository for managing ClinicalData entities.
 * It extends the JpaRepository interface, providing CRUD operations for ClinicalData objects.
 */
@Repository
public interface ClinicalDataRepository extends JpaRepository<ClinicalData, Long> {
    List<ClinicalData> findByComponentValue(String componentValue);
    List<ClinicalData> findByMeasuredDateTime(Timestamp measuredDateTime);
}