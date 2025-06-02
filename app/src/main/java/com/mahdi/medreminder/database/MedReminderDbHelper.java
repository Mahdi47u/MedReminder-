package com.mahdi.medreminder.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MedReminderDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MedReminder.db";

    public static final String TABLE_MEDICATIONS = "medications";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_MEDICATION_NAME = "medication_name";
    public static final String COLUMN_DOSAGE = "dosage";
    public static final String COLUMN_DOSAGE_UNIT = "dosage_unit";
    public static final String COLUMN_METHOD_OF_CONSUMPTION = "method_of_consumption";
    public static final String COLUMN_FREQUENCY_TYPE = "frequency_type";
    public static final String COLUMN_SPECIFIC_TIMES = "specific_times"; // Will store as comma-separated string
    public static final String COLUMN_START_DATE = "start_date";
    public static final String COLUMN_END_DATE = "end_date";
    public static final String COLUMN_NOTES = "notes";
    public static final String COLUMN_ENABLE_REMINDERS = "enable_reminders";

    private static final String SQL_CREATE_MEDICATIONS_TABLE =
            "CREATE TABLE " + TABLE_MEDICATIONS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_MEDICATION_NAME + " TEXT NOT NULL," +
                    COLUMN_DOSAGE + " TEXT," +
                    COLUMN_DOSAGE_UNIT + " TEXT," +
                    COLUMN_METHOD_OF_CONSUMPTION + " TEXT," +
                    COLUMN_FREQUENCY_TYPE + " TEXT," +
                    COLUMN_SPECIFIC_TIMES + " TEXT," + // Storing list of times as a single text field
                    COLUMN_START_DATE + " TEXT," +
                    COLUMN_END_DATE + " TEXT," +
                    COLUMN_NOTES + " TEXT," +
                    COLUMN_ENABLE_REMINDERS + " INTEGER NOT NULL DEFAULT 1);";

    private static final String SQL_DELETE_MEDICATIONS_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_MEDICATIONS;

    public MedReminderDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_MEDICATIONS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_MEDICATIONS_TABLE);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
