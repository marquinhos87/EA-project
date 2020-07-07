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

public class UserToken {
	public UserToken() {
	}
	
	public boolean equals(Object aObj) {
		if (aObj == this)
			return true;
		if (!(aObj instanceof UserToken))
			return false;
		UserToken usertoken = (UserToken)aObj;
		if ((getUsername() != null && !getUsername().equals(usertoken.getUsername())) || (getUsername() == null && usertoken.getUsername() != null))
			return false;
		return true;
	}
	
	public int hashCode() {
		int hashcode = 0;
		hashcode = hashcode + (getUsername() == null ? 0 : getUsername().hashCode());
		return hashcode;
	}
	
	private String username;
	
	private String token;
	
	public void setUsername(String value) {
		this.username = value;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getORMID() {
		return getUsername();
	}
	
	public void setToken(String value) {
		this.token = value;
	}
	
	public String getToken() {
		return token;
	}
	
	public String toString() {
		return String.valueOf(getUsername());
	}
	
}
