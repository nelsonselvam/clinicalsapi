
package com.throttle.patientclinicals.clinicalsapi.models;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "clinicaldata")
public class ClinicalData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String componentName;
    private String componentValue;

    @CreationTimestamp
    private Timestamp measuredDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    @JsonIgnore
    private Patient patient;

    /**
     * Default constructor for the ClinicalData class.
     */
    public ClinicalData() {
    }

    /**
     * Parameterized constructor for the ClinicalData class.
     * 
     * @param componentName    the name of the component
     * @param componentValue   the value of the component
     * @param measuredDateTime the timestamp when the component was measured
     */
    public ClinicalData(String componentName, String componentValue, Timestamp measuredDateTime) {
        this.componentName = componentName;
        this.componentValue = componentValue;
        this.measuredDateTime = measuredDateTime;
    }

    /**
     * Get the ID of the clinical data.
     * 
     * @return the ID of the clinical data
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the ID of the clinical data.
     * 
     * @param id the ID of the clinical data
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the name of the component.
     * 
     * @return the name of the component
     */
    public String getComponentName() {
        return componentName;
    }

    /**
     * Set the name of the component.
     * 
     * @param componentName the name of the component
     */
    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    /**
     * Get the value of the component.
     * 
     * @return the value of the component
     */
    public String getComponentValue() {
        return componentValue;
    }

    /**
     * Set the value of the component.
     * 
     * @param componentValue the value of the component
     */
    public void setComponentValue(String componentValue) {
        this.componentValue = componentValue;
    }

    /**
     * Get the measured date and time of the component.
     * 
     * @return the measured date and time of the component
     */
    public Timestamp getMeasuredDateTime() {
        return measuredDateTime;
    }

    /**
     * Set the measured date and time of the component.
     * 
     * @param measuredDateTime the measured date and time of the component
     */
    public void setMeasuredDateTime(Timestamp measuredDateTime) {
        this.measuredDateTime = measuredDateTime;
    }

    // getters and setters for Patient
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
