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
package hrclient;

public class BiometricData {
	public BiometricData() {
	}
	
	private int ID;
	
	private int height;
	
	private float weight;
	
	private int wrist;
	
	private int chest;
	
	private int tricep;
	
	private int waist;
	
	private int quadricep;
	
	private int twin;
	
	private java.util.Date date;
	
	private float BMI;
	
	private void setID(int value) {
		this.ID = value;
	}
	
	public int getID() {
		return ID;
	}
	
	public int getORMID() {
		return getID();
	}
	
	public void setHeight(int value) {
		this.height = value;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setWeight(float value) {
		this.weight = value;
	}
	
	public float getWeight() {
		return weight;
	}
	
	public void setWrist(int value) {
		this.wrist = value;
	}
	
	public int getWrist() {
		return wrist;
	}
	
	public void setChest(int value) {
		this.chest = value;
	}
	
	public int getChest() {
		return chest;
	}
	
	public void setTricep(int value) {
		this.tricep = value;
	}
	
	public int getTricep() {
		return tricep;
	}
	
	public void setWaist(int value) {
		this.waist = value;
	}
	
	public int getWaist() {
		return waist;
	}
	
	public void setQuadricep(int value) {
		this.quadricep = value;
	}
	
	public int getQuadricep() {
		return quadricep;
	}
	
	public void setTwin(int value) {
		this.twin = value;
	}
	
	public int getTwin() {
		return twin;
	}
	
	public void setDate(java.util.Date value) {
		this.date = value;
	}
	
	public java.util.Date getDate() {
		return date;
	}
	
	public void setBMI(float value) {
		this.BMI = value;
	}
	
	public float getBMI() {
		return BMI;
	}
	
	public String toString() {
		return String.valueOf(getID());
	}
	
}
