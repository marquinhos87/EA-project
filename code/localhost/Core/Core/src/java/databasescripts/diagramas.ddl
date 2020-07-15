ALTER TABLE `Plan` DROP FOREIGN KEY FKPlan877161;
ALTER TABLE Task DROP FOREIGN KEY FKTask764907;
ALTER TABLE Workout DROP FOREIGN KEY FKWorkout678567;
ALTER TABLE Serie DROP FOREIGN KEY FKSerie631958;
ALTER TABLE Week DROP FOREIGN KEY FKWeek714444;
ALTER TABLE Client DROP FOREIGN KEY FKClient717484;
DROP TABLE IF EXISTS `Plan`;
DROP TABLE IF EXISTS Workout;
DROP TABLE IF EXISTS Task;
DROP TABLE IF EXISTS Serie;
DROP TABLE IF EXISTS Client;
DROP TABLE IF EXISTS PersonalTrainer;
DROP TABLE IF EXISTS Week;
DROP TABLE IF EXISTS `User`;
CREATE TABLE `Plan` (ID int(10) NOT NULL AUTO_INCREMENT, PersonalTrainerUsername varchar(255) NOT NULL, Done tinyint(1), Modified tinyint(1) NOT NULL, InitialDate date, CurrentWeek int(10) NOT NULL, PRIMARY KEY (ID));
CREATE TABLE Workout (ID int(10) NOT NULL AUTO_INCREMENT, WeekID int(10) NOT NULL, Designation varchar(255), `Date` date, Done tinyint(1) NOT NULL, PRIMARY KEY (ID));
CREATE TABLE Task (ID int(10) NOT NULL AUTO_INCREMENT, WorkoutID int(10) NOT NULL, Designation varchar(255), Rest varchar(255), Duration varchar(255), Equipment varchar(255), PRIMARY KEY (ID));
CREATE TABLE Serie (ID int(10) NOT NULL AUTO_INCREMENT, TaskID int(10) NOT NULL, Description varchar(255), Repetitions varchar(255), Rest varchar(255), PRIMARY KEY (ID));
CREATE TABLE Client (Username varchar(255) NOT NULL, PlanID int(10) NOT NULL, PRIMARY KEY (Username));
CREATE TABLE PersonalTrainer (Username varchar(255) NOT NULL, PRIMARY KEY (Username));
CREATE TABLE Week (ID int(10) NOT NULL AUTO_INCREMENT, PlanID int(10) NOT NULL, Number int(10) NOT NULL, InitialDate date, FinalDate date, PRIMARY KEY (ID));
CREATE TABLE `User` (Username varchar(255) NOT NULL, Token varchar(255), PRIMARY KEY (Username));
ALTER TABLE `Plan` ADD CONSTRAINT FKPlan877161 FOREIGN KEY (PersonalTrainerUsername) REFERENCES PersonalTrainer (Username);
ALTER TABLE Task ADD CONSTRAINT FKTask764907 FOREIGN KEY (WorkoutID) REFERENCES Workout (ID);
ALTER TABLE Workout ADD CONSTRAINT FKWorkout678567 FOREIGN KEY (WeekID) REFERENCES Week (ID);
ALTER TABLE Serie ADD CONSTRAINT FKSerie631958 FOREIGN KEY (TaskID) REFERENCES Task (ID);
ALTER TABLE Week ADD CONSTRAINT FKWeek714444 FOREIGN KEY (PlanID) REFERENCES `Plan` (ID);
ALTER TABLE Client ADD CONSTRAINT FKClient717484 FOREIGN KEY (PlanID) REFERENCES `Plan` (ID);
