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
package requests;

public class Request {
	public Request() {
	}
	
	private int ID;
	
	private int numberOfWeeks;
	
	private String objective;
	
	private int workoutPerWeek;
	
	private String weekDays;
	
	private int level;
	
	private boolean accepted;
	
	private void setID(int value) {
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
	
	public int getWokoutPerWeek() {
		//TODO: Implement Method
		throw new UnsupportedOperationException();
	}
	
	public void setWokoutPerWeek(int wokoutPerWeek) {
		//TODO: Implement Method
		throw new UnsupportedOperationException();
	}
	
	public boolean isAccepted() {
		//TODO: Implement Method
		throw new UnsupportedOperationException();
	}
	
	public String toString() {
		return String.valueOf(getID());
	}
	
}
