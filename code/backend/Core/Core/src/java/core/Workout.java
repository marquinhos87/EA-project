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

import java.util.Iterator;
import javax.persistence.Transient;

public class Workout {
        
        @Transient
        private int weekDay;

    public int getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(int weekDay) {
        this.weekDay = weekDay;
    }
    
	public Workout() {
	}
	
	private java.util.Set this_getSet (int key) {
		if (key == ORMConstants.KEY_WORKOUT_TASKS) {
			return ORM_tasks;
		}
		
		return null;
	}
	
	private void this_setOwner(Object owner, int key) {
		if (key == ORMConstants.KEY_WORKOUT_WEEK) {
			this.week = (core.Week) owner;
		}
	}
	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public java.util.Set getSet(int key) {
			return this_getSet(key);
		}
		
		public void setOwner(Object owner, int key) {
			this_setOwner(owner, key);
		}
		
	};
	
	private int ID;
	
	private core.Week week;
	
	private String designation;
	
	private java.util.Date date;
	
	private boolean done;
	
	private java.util.Set ORM_tasks = new java.util.HashSet();
	
	private void setID(int value) {
		this.ID = value;
	}
	
	public int getID() {
		return ID;
	}
	
	public int getORMID() {
		return getID();
	}
	
	public void setDesignation(String value) {
		this.designation = value;
	}
	
	public String getDesignation() {
		return designation;
	}
	
	public void setDate(java.util.Date value) {
		this.date = value;
	}
	
	public java.util.Date getDate() {
		return date;
	}
	
	public void setDone(boolean value) {
		this.done = value;
	}
	
	public boolean getDone() {
		return done;
	}
	
	private void setORM_Tasks(java.util.Set value) {
		this.ORM_tasks = value;
	}
	
	private java.util.Set getORM_Tasks() {
		return ORM_tasks;
	}
	
	public final core.TaskSetCollection tasks = new core.TaskSetCollection(this, _ormAdapter, ORMConstants.KEY_WORKOUT_TASKS, ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public void setWeek(core.Week value) {
		if (week != null) {
			week.workouts.remove(this);
		}
		if (value != null) {
			value.workouts.add(this);
		}
	}
	
	public core.Week getWeek() {
		return week;
	}
	
	/**
	 * This method is for internal use only.
	 */
	public void setORM_Week(core.Week value) {
		this.week = value;
	}
	
	private core.Week getORM_Week() {
		return week;
	}
	
	public boolean isDone() {
		return done;
	}
	
	public String toString() {
		return String.valueOf(getID());
	}
        
          public Workout clone() {
            Workout w = new Workout();
            //w.ID = this.ID;
            //w.ORM_tasks = this.ORM_tasks;
            //w._ormAdapter = this._ormAdapter;
            w.date = this.date;
            w.designation = this.designation;
            w.done = this.done;
            Iterator it = this.tasks.getIterator();
            while(it.hasNext()) {
                w.tasks.add((Task)it.next());
            }
            w.week = this.week;
            w.weekDay = this.weekDay;
            return w;
        }

	
}
