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
package core.data;

public class Task {
	public Task() {
	}
	
	private java.util.Set this_getSet (int key) {
		if (key == ORMConstants.KEY_TASK_SERIES) {
			return ORM_series;
		}
		
		return null;
	}
	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public java.util.Set getSet(int key) {
			return this_getSet(key);
		}
		
	};
	
	private int ID;
	
	private String designation;
	
	private String rest;
	
	private String duration;
	
	private String equipment;
	
	private java.util.Set ORM_series = new java.util.HashSet();
	
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
	
	public void setRest(String value) {
		this.rest = value;
	}
	
	public String getRest() {
		return rest;
	}
	
	public void setDuration(String value) {
		this.duration = value;
	}
	
	public String getDuration() {
		return duration;
	}
	
	public void setEquipment(String value) {
		this.equipment = value;
	}
	
	public String getEquipment() {
		return equipment;
	}
	
	private void setORM_Series(java.util.Set value) {
		this.ORM_series = value;
	}
	
	private java.util.Set getORM_Series() {
		return ORM_series;
	}
	
	public final SerieSetCollection series = new SerieSetCollection(this, _ormAdapter, ORMConstants.KEY_TASK_SERIES, ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public String toString() {
		return String.valueOf(getID());
	}
	
}
