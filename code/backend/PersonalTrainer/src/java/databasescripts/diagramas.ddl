CREATE TABLE PersonalTrainer (Username varchar(255) NOT NULL, Price float NOT NULL, Classification float NOT NULL, Skill varchar(255), Password varchar(255), Name varchar(255), Email varchar(255), Sex varchar(255), Birthday date, NumberOfClassifications int(10) NOT NULL, NumberOfClients int(10) NOT NULL, NumberOfCreatedPlans int(10) NOT NULL, Certified tinyint(1) NOT NULL, PRIMARY KEY (Username));
CREATE TABLE Client (Username varchar(255) NOT NULL, PersonalTrainerUsername varchar(255) NOT NULL, SubmitedClassification tinyint(1) NOT NULL, PRIMARY KEY (Username));
CREATE TABLE `User` (Username varchar(255) NOT NULL, Token varchar(255), PRIMARY KEY (Username));
ALTER TABLE Client ADD CONSTRAINT FKClient756698 FOREIGN KEY (PersonalTrainerUsername) REFERENCES PersonalTrainer (Username);
