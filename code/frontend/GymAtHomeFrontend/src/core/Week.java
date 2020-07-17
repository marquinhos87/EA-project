/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: Ricardo Petronilho(Universidade do Minho)
 * License Type: Academic
 */
package core;

import java.util.*;

public class Week {
	
	public int number;
	public Date initialDate;
	public Date finalDate;
	public Map<Integer, Workout> workouts = new HashMap<>();

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Date getInitialDate() {
		return initialDate;
	}

	public void setInitialDate(Date initialDate) {
		this.initialDate = initialDate;
	}

	public Date getFinalDate() {
		return finalDate;
	}

	public void setFinalDate(Date finalDate) {
		this.finalDate = finalDate;
	}

	public Map<Integer, Workout> getWorkouts() {
		return workouts;
	}

	public void setWorkouts(Map<Integer, Workout> workouts) {
		this.workouts = workouts;
	}

	@Override
	public String toString() {
		return "Week{" +
				"number=" + number +
				", initialDate=" + initialDate +
				", finalDate=" + finalDate +
				", workouts=" + workouts +
				'}';
	}
}
