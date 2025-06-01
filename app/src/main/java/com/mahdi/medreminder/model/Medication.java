package com.mahdi.medreminder.model;

import java.util.List;
public class Medication {
    private long id;
    private String medicationName;
    private String dosage;
    private String dosageUnit;
    private String methodOfConsumption;
    private String frequencyType;
    private List<String> specificTimes;
    private String startDate;
    private String endDate;
    private String notes;
    private boolean enableReminders;

    public Medication(long id, String medicationName, String dosage, String dosageUnit,
                      String methodOfConsumption, String frequencyType, List<String> specificTimes,
                      String startDate, String endDate, String notes, boolean enableReminders) {
        this.id = id;
        this.medicationName = medicationName;
        this.dosage = dosage;
        this.dosageUnit = dosageUnit;
        this.methodOfConsumption = methodOfConsumption;
        this.frequencyType = frequencyType;
        this.specificTimes = specificTimes;
        this.startDate = startDate;
        this.endDate = endDate;
        this.notes = notes;
        this.enableReminders = enableReminders;
    }

    public String getMethodOfConsumption() {
        return methodOfConsumption;
    }

    public void setMethodOfConsumption(String methodOfConsumption) {
        this.methodOfConsumption = methodOfConsumption;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getDosageUnit() {
        return dosageUnit;
    }

    public void setDosageUnit(String dosageUnit) {
        this.dosageUnit = dosageUnit;
    }

    public String getFrequencyType() {
        return frequencyType;
    }

    public void setFrequencyType(String frequencyType) {
        this.frequencyType = frequencyType;
    }

    public List<String> getSpecificTimes() {
        return specificTimes;
    }

    public void setSpecificTimes(List<String> specificTimes) {
        this.specificTimes = specificTimes;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isEnableReminders() {
        return enableReminders;
    }

    public void setEnableReminders(boolean enableReminders) {
        this.enableReminders = enableReminders;
    }

    @Override
    public String toString(){
        return "Medication{" +
                "id" + id +
                ", medicationName='" + medicationName + '\'' +
                ", dosage='" + dosage + '\'' +
                ", dosageUnit='" + dosageUnit + '\'' +


                '}';
    }
}
