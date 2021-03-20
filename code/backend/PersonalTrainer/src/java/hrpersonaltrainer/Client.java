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
package hrpersonaltrainer;

public class Client {
	public Client() {
	}
	
	public boolean equals(Object aObj) {
		if (aObj == this)
			return true;
		if (!(aObj instanceof Client))
			return false;
		Client client = (Client)aObj;
		if ((getUsername() != null && !getUsername().equals(client.getUsername())) || (getUsername() == null && client.getUsername() != null))
			return false;
		return true;
	}
	
	public int hashCode() {
		int hashcode = 0;
		hashcode = hashcode + (getUsername() == null ? 0 : getUsername().hashCode());
		return hashcode;
	}
	
	private String username;
	
	private boolean submitedClassification;
	
	public void setUsername(String value) {
		this.username = value;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getORMID() {
		return getUsername();
	}
	
	public void setSubmitedClassification(boolean value) {
		this.submitedClassification = value;
	}
	
	public boolean getSubmitedClassification() {
		return submitedClassification;
	}
	
	public String toString() {
		return String.valueOf(getUsername());
	}
	
}
