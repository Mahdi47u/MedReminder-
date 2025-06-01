package com.mahdi.medreminder.dao;

import com.mahdi.medreminder.model.Medication;
import java.util.List;
public interface MedicationDao {

    long addMedication(Medication medication);

    Medication getMedicationById(long id);

    List<Medication> getAllMedication();

    int updateMedication(Medication medication);

    int deleteMedication(Medication medication);

    int deleteMedicationById(long id);

}
