/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: josepereira(Universidade do Minho)
 * License Type: Academic
 */
package core;

public class Request {
	public Request() {
	}
	
	public int ID;

	public String clientUsername;

	public String personalTrainerUsername;
	
	public int numberOfWeeks;
	
	public String objective;
	
	public int workoutPerWeek;
	
	public String weekDays;
	
	public int level;
	
	public boolean accepted;
	
	public void setID(int value) {
		this.ID = value;
	}
	
	public int getID() {
		return ID;
	}
	
	public int getORMID() {
		return getID();
	}
	
	public void setNumberOfWeeks(int value) {
		this.numberOfWeeks = value;
	}
	
	public int getNumberOfWeeks() {
		return numberOfWeeks;
	}
	
	public void setObjective(String value) {
		this.objective = value;
	}
	
	public String getObjective() {
		return objective;
	}
	
	public void setWorkoutPerWeek(int value) {
		this.workoutPerWeek = value;
	}
	
	public int getWorkoutPerWeek() {
		return workoutPerWeek;
	}
	
	public void setWeekDays(String value) {
		this.weekDays = value;
	}
	
	public String getWeekDays() {
		return weekDays;
	}
	
	public void setLevel(int value) {
		this.level = value;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setAccepted(boolean value) {
		this.accepted = value;
	}
	
	public boolean getAccepted() {
		return accepted;
	}
	
	public String toString() {
		return String.valueOf(getID());
	}
	
}
