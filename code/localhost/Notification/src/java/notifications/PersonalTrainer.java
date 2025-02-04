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
package notifications;

public class PersonalTrainer {
	public PersonalTrainer() {
	}
	
	public boolean equals(Object aObj) {
		if (aObj == this)
			return true;
		if (!(aObj instanceof PersonalTrainer))
			return false;
		PersonalTrainer personaltrainer = (PersonalTrainer)aObj;
		if ((getUsername() != null && !getUsername().equals(personaltrainer.getUsername())) || (getUsername() == null && personaltrainer.getUsername() != null))
			return false;
		return true;
	}
	
	public int hashCode() {
		int hashcode = 0;
		hashcode = hashcode + (getUsername() == null ? 0 : getUsername().hashCode());
		return hashcode;
	}
	
	private java.util.Set this_getSet (int key) {
		if (key == ORMConstants.KEY_PERSONALTRAINER_NOTIFICATIONS) {
			return ORM_notifications;
		}
		
		return null;
	}
	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public java.util.Set getSet(int key) {
			return this_getSet(key);
		}
		
	};
	
	private String username;
	
	private java.util.Set ORM_notifications = new java.util.HashSet();
	
	public void setUsername(String value) {
		this.username = value;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getORMID() {
		return getUsername();
	}
	
	private void setORM_Notifications(java.util.Set value) {
		this.ORM_notifications = value;
	}
	
	private java.util.Set getORM_Notifications() {
		return ORM_notifications;
	}
	
	public final notifications.NotificationSetCollection notifications = new notifications.NotificationSetCollection(this, _ormAdapter, ORMConstants.KEY_PERSONALTRAINER_NOTIFICATIONS, ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public String toString() {
		return String.valueOf(getUsername());
	}
	
}
