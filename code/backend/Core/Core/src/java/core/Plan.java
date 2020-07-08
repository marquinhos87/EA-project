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

public class Plan {
	public Plan() {
	}
	
	private java.util.Set this_getSet (int key) {
		if (key == ORMConstants.KEY_PLAN_WEEKS) {
			return ORM_weeks;
		}
		
		return null;
	}
	
	private void this_setOwner(Object owner, int key) {
		if (key == ORMConstants.KEY_PLAN_CURRENTWEEK) {
			this.currentWeek = (core.Week) owner;
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
	
	private core.Week currentWeek;
	
	private boolean done;
	
	private boolean modified;
	
	private java.util.Date initialDate;
	
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
	
	private void setORM_Weeks(java.util.Set value) {
		this.ORM_weeks = value;
	}
	
	private java.util.Set getORM_Weeks() {
		return ORM_weeks;
	}
	
	public final core.WeekSetCollection weeks = new core.WeekSetCollection(this, _ormAdapter, ORMConstants.KEY_PLAN_WEEKS, ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public void setCurrentWeek(core.Week value) {
		this.currentWeek = value;
	}
	
	public core.Week getCurrentWeek() {
		return currentWeek;
	}
	
	public boolean isDone() {
		//TODO: Implement Method
		throw new UnsupportedOperationException();
	}
	
	public boolean isModified() {
		//TODO: Implement Method
		throw new UnsupportedOperationException();
	}
	
	public String toString() {
		return String.valueOf(getID());
	}
	
}
