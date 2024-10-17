package com.example.samopumpaj.DB;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Pair;

import androidx.annotation.Nullable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    // Define the database name and version
    private static final String DATABASE_NAME = "pumpaj1.db";
    private static final int DATABASE_VERSION = 2;

    // Table and column names
    public static final String TABLE_WORKOUT = "Workouts";
    public static final String TABLE_VISIT = "Visits";
    public static final String TABLE_TRAINING = "Trainings";
    public static final String TABLE_EXERCISE = "Exercises";
    public static final String TABLE_RECORD = "LiftingRecords";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_LAST_VISIT = "lastVisit";
    public static final String COLUMN_NUMBER_OF_VISITS = "numberOfVisits";
    private static final String COLUMN_KILO = "kilos";
    private static final String COLUMN_DATE_TIME = "dateTime";

    // Constructor
    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Called when the database is created
    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create the Workout table
        String createTableSQL = "CREATE TABLE " + TABLE_WORKOUT + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT NOT NULL, " +
                COLUMN_LAST_VISIT + " TEXT, " +
                COLUMN_NUMBER_OF_VISITS + " INTEGER" +
                ");";

        // Execute the SQL to create the table
        db.execSQL(createTableSQL);
    }

    // Called when the database version is upgraded (used for migrations)
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the old table if it exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORKOUT);
        // Create the new table
        onCreate(db);
    }

    ///WORKOUT///

    // Method to add a new workout entry
    public boolean addWorkout(WorkoutModel workout) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, workout.getName());
        contentValues.put(COLUMN_NUMBER_OF_VISITS, workout.getNumberOfVisits());

        // Insert the new row, returning the primary key value of the new row
        long result = db.insert(TABLE_WORKOUT, null, contentValues);

        if (result == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    // Method to get all workout entries
    @SuppressLint("Range")
    public List<WorkoutModel> getAllWorkouts() {
        List<WorkoutModel> workoutList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Query to select all rows from the workout table
        String selectQuery = "SELECT * FROM " + TABLE_WORKOUT;
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Loop through all rows and add them to the list
        if (cursor.moveToFirst()) {
            do {
                // Retrieve data from the cursor
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                int numberOfVisits = getWorkoutLastVisit(id);

                // Create a new WorkoutModel object and add it to the list
                WorkoutModel workout = new WorkoutModel(id, name);
                workout.setNumberOfVisits(numberOfVisits);
                workoutList.add(workout);
            } while (cursor.moveToNext());
        }

        // Close the cursor and database connection
        cursor.close();
        db.close();

        return workoutList;
    }

    // Method to update the number of visits for a given workout ID
    public int getWorkoutLastVisit(int workoutId) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Query to count the number of visits for the given workout ID
        String countQuery = "SELECT COUNT(*) FROM " + TABLE_VISIT + " WHERE workoutFk =?";
        Cursor cursor = db.rawQuery(countQuery, new String[]{String.valueOf(workoutId)});

        int visitCount = 0;
        if (cursor.moveToFirst()) {
            visitCount = cursor.getInt(0); // Get the count from the first column
        }

        cursor.close();
        db.close();

        // If rowsAffected is 0, then the update was not successful
        return visitCount;
    }





    ///TRAINING///

    public List<TrainingModel> getAllTrainingsByWorkoutId(int workoutId) {
        List<TrainingModel> trainingList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_TRAINING + " WHERE workoutId = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(workoutId)});

        if (cursor.moveToFirst()) {
            do {
                TrainingModel training = new TrainingModel();
                int id = cursor.getColumnIndexOrThrow(COLUMN_ID);
                Pair<Integer, String> visitPair = getTrainingVisits(training.getId());

                training.setId(cursor.getInt(id));
                training.setName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)));
                training.setWorkoutFk(cursor.getInt(cursor.getColumnIndexOrThrow("workoutId")));
                training.setNumberOfVisits(visitPair.first);
                training.setLastVisit(visitPair.second);

                trainingList.add(training);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return trainingList;
    }

    public Pair<Integer, String> getTrainingVisits(int trainingId) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Get the count of all visits with the same trainingId
        String countQuery = "SELECT COUNT(*) FROM " + TABLE_VISIT + " WHERE trainingFk = ?";
        Cursor countCursor = db.rawQuery(countQuery, new String[]{String.valueOf(trainingId)});
        int numberOfVisits = 0;
        if (countCursor.moveToFirst()) {
            numberOfVisits = countCursor.getInt(0);
        }
        countCursor.close();

        // Get the most recent visit date (lastVisit) with the same trainingFkId
        String lastVisitQuery = "SELECT visitDate FROM " + TABLE_VISIT + " WHERE trainingFk = ? ORDER BY dateTime DESC LIMIT 1";
        Cursor lastVisitCursor = db.rawQuery(lastVisitQuery, new String[]{String.valueOf(trainingId)});
        String lastVisit = null;
        if (lastVisitCursor.moveToFirst()) {
            String dateTimeString = lastVisitCursor.getString(0);
            LocalDateTime dateTime = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                dateTime = LocalDateTime.parse(dateTimeString);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                lastVisit = dateTime.format(formatter);
            }

        }
        lastVisitCursor.close();
        db.close();

        return new Pair<>(numberOfVisits, lastVisit);
    }




    ///VISIT///
    public void addVisit(VisitModel visit) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_KILO, visit.getKilos());
        values.put(COLUMN_DATE_TIME, visit.getDateTime().toString());
        values.put("workoutFk", visit.getWorkoutFk());
        values.put("trainingFk", visit.getTrainingFk());

        // Insert the new row into the Visits table
        db.insert( TABLE_VISIT, null, values);
        db.close();
    }






    ///EXERCISE///
    @SuppressLint("Range")
    public List<ExerciseModel> getAllExercisesByTrainingId(int trainingId) {
        List<ExerciseModel> exerciseList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // SQL query to get all exercises with the specific training ID, ordered by order number
        String query = "SELECT * FROM " + TABLE_EXERCISE + " WHERE trainingId = ? ORDER BY orderNumber ASC";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(trainingId)});

        // Iterate through the results and create ExerciseModel instances
        if (cursor.moveToFirst()) {
            do {
                ExerciseModel exercise = new ExerciseModel();
                exercise.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                exercise.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                exercise.setTargetMuscle(cursor.getString(cursor.getColumnIndex("targetMuscle")));
                exercise.setVideoLink(cursor.getString(cursor.getColumnIndex("videoLink")));
                exercise.setOrderNumber(cursor.getInt(cursor.getColumnIndex("orderNumber")));
                exercise.setTrainingFk(cursor.getInt(cursor.getColumnIndex("trainingId")));

                // Add the exercise to the list
                exerciseList.add(exercise);
            } while (cursor.moveToNext());
        }

        // Close the cursor and the database
        cursor.close();
        db.close();

        return exerciseList;
    }

    @SuppressLint("Range")
    public ExerciseModel getExerciseById(int exerciseId) {
        SQLiteDatabase db = this.getReadableDatabase();
        ExerciseModel exercise = new ExerciseModel();

        // SQL query to get the exercise with the specific ID
        String query = "SELECT * FROM  " + TABLE_EXERCISE + "  WHERE id = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(exerciseId)});

        // Check if the cursor has a result
        if (cursor.moveToFirst()) {
            exercise.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
            exercise.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
            exercise.setTargetMuscle(cursor.getString(cursor.getColumnIndex("targetMuscle")));
            exercise.setVideoLink(cursor.getString(cursor.getColumnIndex("videoLink")));
            exercise.setOrderNumber(cursor.getInt(cursor.getColumnIndex("orderNumber")));
            exercise.setTrainingFk(cursor.getInt(cursor.getColumnIndex("trainingId")));
        }

        // Close the cursor and the database
        cursor.close();
        db.close();

        return exercise;
    }

    public Pair<Integer, String> getExerciseUpdates(int exerciseId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int numberOfUpdates = 0;
        String lastUpdate = null;

        // Get the count of all lifting records with the same exercise ID
        String countQuery = "SELECT COUNT(*) FROM  " + TABLE_RECORD + "  WHERE exerciseFk = ?";
        Cursor countCursor = db.rawQuery(countQuery, new String[]{String.valueOf(exerciseId)});
        if (countCursor.moveToFirst()) {
            numberOfUpdates = countCursor.getInt(0);
        }
        countCursor.close();

        // Get the most recent update date (lastUpdate) with the same exerciseFkId
        String lastUpdateQuery = "SELECT dateTime FROM  " + TABLE_RECORD + "  WHERE exerciseFk = ? ORDER BY dateTime DESC LIMIT 1";
        Cursor lastUpdateCursor = db.rawQuery(lastUpdateQuery, new String[]{String.valueOf(exerciseId)});
        if (lastUpdateCursor.moveToFirst()) {
            String dateTimeString = lastUpdateCursor.getString(0);
            LocalDateTime dateTime = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                dateTime = LocalDateTime.parse(dateTimeString);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                lastUpdate = dateTime.format(formatter);
            }
        }
        lastUpdateCursor.close();
        db.close();

        return new Pair<>(numberOfUpdates, lastUpdate);
    }





    ///RECORD///
    public void addRecord(LiftingRecordModel record) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("exerciseFk", record.getExerciseFk());
        values.put("weight", record.getWeight());
        values.put("sets", record.getSets());
        values.put("level", record.getLevel());
        values.put("reps", record.getReps());

        // Convert LocalDateTime to String for storage
        DateTimeFormatter formatter = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            String dateTimeString = record.getDate().format(formatter);
            values.put("dateTime", dateTimeString);
        }
        // Insert the record into the LiftingRecords table
            db.insert(TABLE_RECORD,null, values);

        // Close the database
        db.close();
    }








}
