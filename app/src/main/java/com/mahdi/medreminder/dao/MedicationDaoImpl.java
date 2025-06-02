package com.mahdi.medreminder.dao;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mahdi.medreminder.model.Medication;
import com.mahdi.medreminder.database.MedReminderDbHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
public class MedicationDaoImpl implements MedicationDao{

    private MedReminderDbHelper dbHelper;

    public MedicationDaoImpl(Context context){
        this.dbHelper = new MedReminderDbHelper(context);
    }

    @Override
    public long addMedication(Medication medication) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(MedReminderDbHelper.COLUMN_MEDICATION_NAME, medication.getMedicationName());
        values.put(MedReminderDbHelper.COLUMN_DOSAGE, medication.getDosage());
        values.put(MedReminderDbHelper.COLUMN_DOSAGE_UNIT, medication.getDosageUnit());
        values.put(MedReminderDbHelper.COLUMN_METHOD_OF_CONSUMPTION, medication.getMethodOfConsumption());
        values.put(MedReminderDbHelper.COLUMN_FREQUENCY_TYPE, medication.getFrequencyType());

        if (medication.getSpecificTimes() != null && !medication.getSpecificTimes().isEmpty()){
            StringJoiner joiner = new StringJoiner(",");
            for (String time : medication.getSpecificTimes()) {
                joiner.add(time);
            }
            values.put(MedReminderDbHelper.COLUMN_SPECIFIC_TIMES, joiner.toString());
        }

        else {
            values.putNull(MedReminderDbHelper.COLUMN_SPECIFIC_TIMES);}

        values.put(MedReminderDbHelper.COLUMN_START_DATE, medication.getStartDate());
        values.put(MedReminderDbHelper.COLUMN_END_DATE, medication.getEndDate());
        values.put(MedReminderDbHelper.COLUMN_NOTES, medication.getNotes());
        values.put(MedReminderDbHelper.COLUMN_ENABLE_REMINDERS, medication.isEnableReminders() ? 1 : 0);

        long id = db.insert(MedReminderDbHelper.TABLE_MEDICATIONS, null, values);
        return id;
    }

    @Override
    public Medication getMedicationById(long id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Medication medication = null;

        Cursor cursor = db.query(
                MedReminderDbHelper.TABLE_MEDICATIONS,
                null,
                MedReminderDbHelper.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            medication = cursorToMedication(cursor);
            cursor.close();
        }
        // db.close(); // Not strictly necessary
        return medication;
    }

    @Override
    public List<Medication> getAllMedication() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<Medication> medications = new ArrayList<>();
        Cursor cursor = null;

        try {
            cursor = db.query(
                    MedReminderDbHelper.TABLE_MEDICATIONS,
                    null,
                    null,
                    null,
                    null,
                    null,
                    MedReminderDbHelper.COLUMN_MEDICATION_NAME + " ASC"
            );

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    Medication medication = cursorToMedication(cursor);
                    medications.add(medication);
                } while (cursor.moveToNext());
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            // db.close(); // Not strictly necessary
        }
        return medications;
    }



    @Override
    public int updateMedication(Medication medication) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(MedReminderDbHelper.COLUMN_MEDICATION_NAME, medication.getMedicationName());
        values.put(MedReminderDbHelper.COLUMN_DOSAGE, medication.getDosage());
        values.put(MedReminderDbHelper.COLUMN_DOSAGE_UNIT, medication.getDosageUnit());
        values.put(MedReminderDbHelper.COLUMN_METHOD_OF_CONSUMPTION, medication.getMethodOfConsumption());
        values.put(MedReminderDbHelper.COLUMN_FREQUENCY_TYPE, medication.getFrequencyType());

        if (medication.getSpecificTimes() != null && !medication.getSpecificTimes().isEmpty()){
            StringJoiner joiner = new StringJoiner(",");
            for (String time : medication.getSpecificTimes()){
                joiner.add(time);

            }
            values.put(MedReminderDbHelper.COLUMN_SPECIFIC_TIMES, joiner.toString());

        } else {
            values.putNull(MedReminderDbHelper.COLUMN_SPECIFIC_TIMES);
        }

        values.put(MedReminderDbHelper.COLUMN_START_DATE, medication.getStartDate());
        values.put(MedReminderDbHelper.COLUMN_END_DATE, medication.getEndDate());
        values.put(MedReminderDbHelper.COLUMN_NOTES, medication.getNotes());
        values.put(MedReminderDbHelper.COLUMN_ENABLE_REMINDERS, medication.isEnableReminders() ? 1 : 0);

        int rowsAffected = db.update(
                MedReminderDbHelper.TABLE_MEDICATIONS,
                values,
                MedReminderDbHelper.COLUMN_ID + "=?",
                new String[]{String.valueOf(medication.getId())}
        );
        // db.close();
        return rowsAffected;

    }

    @Override
    public int deleteMedication(Medication medication) {
        return deleteMedicationById(medication.getId());
    }

    @Override
    public int deleteMedicationById(long id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rowsAffected = db.delete(
                MedReminderDbHelper.TABLE_MEDICATIONS,
                MedReminderDbHelper.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}
        );
        return rowsAffected;
    }

    private Medication cursorToMedication(Cursor cursor){
        Medication medication = new Medication();
        medication.setId(cursor.getLong(cursor.getColumnIndexOrThrow(MedReminderDbHelper.COLUMN_ID)));
        medication.setMedicationName(cursor.getString(cursor.getColumnIndexOrThrow(MedReminderDbHelper.COLUMN_MEDICATION_NAME)));
        medication.setDosage(cursor.getString(cursor.getColumnIndexOrThrow(MedReminderDbHelper.COLUMN_DOSAGE)));
        medication.setDosageUnit(cursor.getString(cursor.getColumnIndexOrThrow(MedReminderDbHelper.COLUMN_DOSAGE_UNIT)));
        medication.setMethodOfConsumption(cursor.getString(cursor.getColumnIndexOrThrow(MedReminderDbHelper.COLUMN_METHOD_OF_CONSUMPTION)));
        medication.setFrequencyType(cursor.getString(cursor.getColumnIndexOrThrow(MedReminderDbHelper.COLUMN_FREQUENCY_TYPE)));

        String specificTimesStr = cursor.getString(cursor.getColumnIndexOrThrow(MedReminderDbHelper.COLUMN_SPECIFIC_TIMES));
        if (specificTimesStr != null && !specificTimesStr.isEmpty()) {
            medication.setSpecificTimes(new ArrayList<>(Arrays.asList(specificTimesStr.split(","))));
        } else {
            medication.setSpecificTimes(new ArrayList<>()); // Empty list
        }

        medication.setStartDate(cursor.getString(cursor.getColumnIndexOrThrow(MedReminderDbHelper.COLUMN_START_DATE)));
        medication.setEndDate(cursor.getString(cursor.getColumnIndexOrThrow(MedReminderDbHelper.COLUMN_END_DATE)));
        medication.setNotes(cursor.getString(cursor.getColumnIndexOrThrow(MedReminderDbHelper.COLUMN_NOTES)));
        medication.setEnableReminders(cursor.getInt(cursor.getColumnIndexOrThrow(MedReminderDbHelper.COLUMN_ENABLE_REMINDERS)) == 1);

        return medication;
    }
}
