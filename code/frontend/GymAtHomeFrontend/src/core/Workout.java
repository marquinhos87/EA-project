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


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class Workout {

	public int id;
	public String designation;
	public Date date;
	public boolean done;
	public List<Task> tasks = new ArrayList<>();
	public int totalTime;

	public int getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(int weekDay) {
		this.weekDay = weekDay;
	}

	public int weekDay;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}



	@Override
	public String toString() {
		return "Workout{" +
				"id=" + id +
				", designation='" + designation + '\'' +
				", date=" + date +
				", done=" + done +
				", tasks=" + tasks +
				'}';
	}
}
