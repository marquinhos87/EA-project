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

public class Plan {
	public Plan() {
	}
	
	private java.util.Set this_getSet (int key) {
		if (key == ORMConstants.KEY_PLAN_WEEKS) {
			return ORM_weeks;
		}
		
		return null;
	}
	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public java.util.Set getSet(int key) {
			return this_getSet(key);
		}
		
	};
	
	private int ID;
	
	private boolean done;
	
	private boolean modified;
	
	private java.util.Date initialDate;
	
	private int currentWeek;
	
	private java.util.Set ORM_weeks = new java.util.HashSet();
	
	private void setID(int value) {
		this.ID = value;
	}
	
	public int getID() {
		return ID;
	}
	
	public int getORMID() {
		return getID();
	}
	
	public void setDone(boolean value) {
		this.done = value;
	}
	
	public boolean getDone() {
		return done;
	}
	
	public void setModified(boolean value) {
		this.modified = value;
	}
	
	public boolean getModified() {
		return modified;
	}
	
	public void setInitialDate(java.util.Date value) {
		this.initialDate = value;
	}
	
	public java.util.Date getInitialDate() {
		return initialDate;
	}
	
	public void setCurrentWeek(int value) {
		this.currentWeek = value;
	}
	
	public int getCurrentWeek() {
		return currentWeek;
	}
	
	private void setORM_Weeks(java.util.Set value) {
		this.ORM_weeks = value;
	}
	
	private java.util.Set getORM_Weeks() {
		return ORM_weeks;
	}
	
	public final core.WeekSetCollection weeks = new core.WeekSetCollection(this, _ormAdapter, ORMConstants.KEY_PLAN_WEEKS, ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public boolean isDone() {
            return done;
	}
	
	public boolean isModified() {
		return modified;
	}
	
	public String toString() {
		return String.valueOf(getID());
	}
	
}
