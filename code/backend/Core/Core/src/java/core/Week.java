/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: joaomarques(Universidade do Minho)
 * License Type: Academic
 */
package core;

import java.util.Arrays;

public class Week {
	public Week() {
	}
	
	private java.util.Set this_getSet (int key) {
		if (key == ORMConstants.KEY_WEEK_WORKOUTS) {
			return ORM_workouts;
		}
		
		return null;
	}
	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public java.util.Set getSet(int key) {
			return this_getSet(key);
		}
		
	};
	
	private int ID;
	
	private int number;
	
	private java.util.Date initialDate;
	
	private java.util.Date finalDate;
	
	private java.util.Set ORM_workouts = new java.util.HashSet();
	
	private void setID(int value) {
		this.ID = value;
	}
	
	public int getID() {
		return ID;
	}
	
	public int getORMID() {
		return getID();
	}
	
	public void setNumber(int value) {
		this.number = value;
	}
	
	public int getNumber() {
		return number;
	}
	
	public void setInitialDate(java.util.Date value) {
		this.initialDate = value;
	}
	
	public java.util.Date getInitialDate() {
		return initialDate;
	}
	
	public void setFinalDate(java.util.Date value) {
		this.finalDate = value;
	}
	
	public java.util.Date getFinalDate() {
		return finalDate;
	}
	
	private void setORM_Workouts(java.util.Set value) {
		this.ORM_workouts = value;
	}
	
	private java.util.Set getORM_Workouts() {
		return ORM_workouts;
	}
	
	public final core.WorkoutSetCollection workouts = new core.WorkoutSetCollection(this, _ormAdapter, ORMConstants.KEY_WEEK_WORKOUTS, ORMConstants.KEY_MUL_ONE_TO_MANY);

    @Override
    public String toString() {
        return "Week{" + "ID=" + ID + ", number=" + number + ", initialDate=" + initialDate + ", finalDate=" + finalDate + ", workouts=" + Arrays.toString(workouts.toArray()) + '}';
    }
	
}
