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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int state;


	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public String getClientUsername() {
		return clientUsername;
	}

	public void setClientUsername(String clientUsername) {
		this.clientUsername = clientUsername;
	}

	public String getPersonalTrainerUsername() {
		return personalTrainerUsername;
	}

	public void setPersonalTrainerUsername(String personalTrainerUsername) {
		this.personalTrainerUsername = personalTrainerUsername;
	}

	public int getNumberOfWeeks() {
		return numberOfWeeks;
	}

	public void setNumberOfWeeks(int numberOfWeeks) {
		this.numberOfWeeks = numberOfWeeks;
	}

	public String getObjective() {
		return objective;
	}

	public void setObjective(String objective) {
		this.objective = objective;
	}

	public int getWorkoutPerWeek() {
		return workoutPerWeek;
	}

	public void setWorkoutPerWeek(int workoutPerWeek) {
		this.workoutPerWeek = workoutPerWeek;
	}

	public String getWeekDays() {
		return weekDays;
	}

	public void setWeekDays(String weekDays) {
		this.weekDays = weekDays;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	@Override
	public String toString() {
		return "Request{" +
				"ID=" + ID +
				", clientUsername='" + clientUsername + '\'' +
				", personalTrainerUsername='" + personalTrainerUsername + '\'' +
				", numberOfWeeks=" + numberOfWeeks +
				", objective='" + objective + '\'' +
				", workoutPerWeek=" + workoutPerWeek +
				", weekDays='" + weekDays + '\'' +
				", level=" + level +
				", accepted=" + accepted +
				'}';
	}
}
