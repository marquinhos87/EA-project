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
package requests;

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
	
	private java.util.Set this_getSet (int key) {
		if (key == ORMConstants.KEY_CLIENT_REQUESTS) {
			return ORM_requests;
		}
		
		return null;
	}
	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public java.util.Set getSet(int key) {
			return this_getSet(key);
		}
		
	};
	
	private String username;
	
	private java.util.Set ORM_requests = new java.util.HashSet();
	
	public void setUsername(String value) {
		this.username = value;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getORMID() {
		return getUsername();
	}
	
	private void setORM_Requests(java.util.Set value) {
		this.ORM_requests = value;
	}
	
	private java.util.Set getORM_Requests() {
		return ORM_requests;
	}
	
	public final requests.RequestSetCollection requests = new requests.RequestSetCollection(this, _ormAdapter, ORMConstants.KEY_CLIENT_REQUESTS, ORMConstants.KEY_REQUEST_CLIENT, ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public String toString() {
		return String.valueOf(getUsername());
	}
	
}
