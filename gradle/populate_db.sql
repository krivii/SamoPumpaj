-- Drop tables if they exist to avoid conflicts
DROP TABLE IF EXISTS BodyMeasurements;
DROP TABLE IF EXISTS LiftingRecords;
DROP TABLE IF EXISTS Exercises;
DROP TABLE IF EXISTS Visits;
DROP TABLE IF EXISTS Trainings;
DROP TABLE IF EXISTS Workouts;

-- Create Workouts table
CREATE TABLE Workouts (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL
);

-- Create Trainings table
CREATE TABLE Trainings (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    workoutFk INTEGER,
    FOREIGN KEY (workoutFk) REFERENCES Workouts(id) ON DELETE CASCADE
);

-- Create Visits table
CREATE TABLE Visits (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    kilos REAL,
    dateTime TEXT,
    workoutFk INTEGER,
    trainingFk INTEGER,
    FOREIGN KEY (workoutFk) REFERENCES Workouts(id) ON DELETE CASCADE,
    FOREIGN KEY (trainingFk) REFERENCES Trainings(id) ON DELETE CASCADE
);

-- Create Exercises table
CREATE TABLE Exercises (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    targetMuscle TEXT,
    videoLink TEXT,
    orderNumber INTEGER,
    trainingFk INTEGER,
    FOREIGN KEY (trainingFk) REFERENCES Trainings(id) ON DELETE CASCADE
);

-- Create LiftingRecords table
CREATE TABLE LiftingRecords (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    exerciseFk INTEGER,
    weight REAL,
    sets INTEGER,
    level INTEGER,
    reps INTEGER,
    dateTime TEXT,
    FOREIGN KEY (exerciseFk) REFERENCES Exercises(id) ON DELETE CASCADE
);

-- Create BodyMeasurements table
CREATE TABLE BodyMeasurements (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    kilos REAL,
    waist REAL,
    chest REAL,
    leftArm REAL,
    rightArm REAL,
    leftLeg REAL,
    rightLeg REAL,
    dateTime TEXT
);

-- Insert sample data into Workouts
INSERT INTO Workouts (name) VALUES ('Morning Workout');

-- Insert sample data into Trainings (3 trainings for the above workout)
INSERT INTO Trainings (name, workoutId) VALUES
('Strength Training', 1),
('Cardio Training', 1),
('Flexibility Training', 1);

-- Insert sample data into Visits (5 visits for each training)
INSERT INTO Visits (kilos, dateTime, workoutFk, trainingFk) VALUES
(70.0, '2024-10-01 08:00:00', 1, 1),
(75.0, '2024-10-02 09:00:00', 1, 1),
(80.0, '2024-10-03 10:00:00', 1, 1),
(85.0, '2024-10-04 11:00:00', 1, 1),
(90.0, '2024-10-05 12:00:00', 1, 1),

(60.0, '2024-10-01 08:00:00', 1, 2),
(65.0, '2024-10-02 09:00:00', 1, 2),
(70.0, '2024-10-03 10:00:00', 1, 2),
(75.0, '2024-10-04 11:00:00', 1, 2),
(80.0, '2024-10-05 12:00:00', 1, 2),

(50.0, '2024-10-01 08:00:00', 1, 3),
(55.0, '2024-10-02 09:00:00', 1, 3),
(60.0, '2024-10-03 10:00:00', 1, 3),
(65.0, '2024-10-04 11:00:00', 1, 3),
(70.0, '2024-10-05 12:00:00', 1, 3);

-- Insert sample data into Exercises (7 exercises for each training)
INSERT INTO Exercises (name, targetMuscle, videoLink, orderNumber, trainingId) VALUES
('Bench Press', 'Chest', 'http://example.com/video1', 1, 1),
('Squats', 'Legs', 'http://example.com/video2', 2, 1),
('Deadlift', 'Back', 'http://example.com/video3', 3, 1),
('Push Ups', 'Chest', 'http://example.com/video4', 4, 1),
('Pull Ups', 'Back', 'http://example.com/video5', 5, 1),
('Lunges', 'Legs', 'http://example.com/video6', 6, 1),
('Plank', 'Core', 'http://example.com/video7', 7, 1),

('Running', 'Cardio', 'http://example.com/video8', 1, 2),
('Cycling', 'Cardio', 'http://example.com/video9', 2, 2),
('Jump Rope', 'Cardio', 'http://example.com/video10', 3, 2),
('Burpees', 'Cardio', 'http://example.com/video11', 4, 2),
('Rowing', 'Cardio', 'http://example.com/video12', 5, 2),
('Stair Climbing', 'Cardio', 'http://example.com/video13', 6, 2),
('Hiking', 'Legs', 'http://example.com/video14', 7, 2),

('Yoga', 'Flexibility', 'http://example.com/video15', 1, 3),
('Pilates', 'Flexibility', 'http://example.com/video16', 2, 3),
('Dynamic Stretching', 'Flexibility', 'http://example.com/video17', 3, 3),
('Static Stretching', 'Flexibility', 'http://example.com/video18', 4, 3),
('Foam Rolling', 'Flexibility', 'http://example.com/video19', 5, 3),
('Balance Exercises', 'Flexibility', 'http://example.com/video20', 6, 3),
('Resistance Bands', 'Flexibility', 'http://example.com/video21', 7, 3);

-- Insert sample data into LiftingRecords (3 lifting records for each exercise)
INSERT INTO LiftingRecords (exerciseFk, weight, sets, level, reps, dateTime) VALUES
(1, 80.0, 3, 1, 10, '2024-10-01 08:00:00'),
(1, 85.0, 3, 1, 8, '2024-10-02 09:00:00'),
(1, 90.0, 3, 2, 6, '2024-10-03 10:00:00'),

(2, 100.0, 4, 1, 10, '2024-10-01 08:00:00'),
(2, 105.0, 4, 1, 8, '2024-10-02 09:00:00'),
(2, 110.0, 4, 2, 6, '2024-10-03 10:00:00'),

(3, 120.0, 3, 1, 10, '2024-10-01 08:00:00'),
(3, 125.0, 3, 1, 8, '2024-10-02 09:00:00'),
(3, 130.0, 3, 2, 6, '2024-10-03 10:00:00'),

(4, 40.0, 3, 1, 10, '2024-10-01 08:00:00'),
(4, 45.0, 3, 1, 8, '2024-10-02 09:00:00'),
(4, 50.0, 3, 2, 6, '2024-10-03 10:00:00'),

(5, 50.0, 3, 1, 10, '2024-10-01 08:00:00'),
(5, 55.0, 3, 1, 8, '2024-10-02 09:00:00'),
(5, 60.0, 3, 2, 6, '2024-10-03 10:00:00'),

(6, 60.0, 3, 1, 10, '2024-10-01 08:00:00'),
(6, 65.0, 3, 1, 8, '2024-10-02 09:00:00'),
(6, 70.0, 3, 2, 6, '2024-10-03 10:00:00'),

(7, 70.0, 3, 1, 10, '2024-10-01 08:00:00'),
(7, 75.0, 3, 1, 8, '2024-10-02 09:00:00'),
(7, 80.0, 3, 2, 6, '2024-10-03 10:00:00');

-- Insert a sample body measurement
INSERT INTO BodyMeasurements (kilos, waist, chest, leftArm, rightArm, leftLeg, rightLeg, dateTime) VALUES
(75.0, 30.0, 38.0, 12.0, 12.0, 20.0, 20.0, '2024-10-01 08:00:00');
