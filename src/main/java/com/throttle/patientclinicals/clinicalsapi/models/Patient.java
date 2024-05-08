package com.throttle.patientclinicals.clinicalsapi.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

/**
 * Represents a patient in the clinicals API.
 */
@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private int age;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ClinicalData> clinicalData;

    // Default constructor
    /**
     * Creates a new instance of the Patient class with default values.
     */
    public Patient() {
    }

    // Parameterized constructor
    /**
     * Creates a new instance of the Patient class with the specified values.
     * 
     * @param firstName The first name of the patient.
     * @param lastName  The last name of the patient.
     * @param age       The age of the patient.
     */
    public Patient(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    // Getters and setters
    /**
     * Gets the ID of the patient.
     * 
     * @return The ID of the patient.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the patient.
     * 
     * @param id The ID of the patient.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the first name of the patient.
     * 
     * @return The first name of the patient.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the patient.
     * 
     * @param firstName The first name of the patient.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the patient.
     * 
     * @return The last name of the patient.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the patient.
     * 
     * @param lastName The last name of the patient.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the age of the patient.
     * 
     * @return The age of the patient.
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the patient.
     * 
     * @param age The age of the patient.
     */
    public void setAge(int age) {
        this.age = age;
    }

    // getter and setter for clinicalData
    /**
     * Gets the clinical data of the patient.
     * 
     * @return The clinical data of the patient.
     */
    public List<ClinicalData> getClinicalData() {
        return clinicalData;
    }

    /**
     * Sets the clinical data of the patient.
     * 
     * @param clinicalData The clinical data of the patient.
     */
    public void setClinicalData(List<ClinicalData> clinicalData) {
        this.clinicalData = clinicalData;
    }
}