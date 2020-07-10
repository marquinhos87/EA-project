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

import java.util.Set;

public class Client implements hrclient.IClient {
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
		if (key == ORMConstants.KEY_CLIENT_BIOMETRICDATAS) {
			return ORM_biometricDatas;
		}
		
		return null;
	}
	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public java.util.Set getSet(int key) {
			return this_getSet(key);
		}
		
	};
	
	private String username;
	
	private String password;
	
	private String name;
	
	private String email;
	
	private String sex;
	
	private java.util.Date birthday;
	
	private java.util.Set ORM_biometricDatas = new java.util.HashSet();
	
	public void setUsername(String value) {
		this.username = value;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getORMID() {
		return getUsername();
	}
	
	public void setPassword(String value) {
		this.password = value;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return name;
	}
	
	public void setEmail(String value) {
		this.email = value;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setSex(String value) {
		this.sex = value;
	}
	
	public String getSex() {
		return sex;
	}
	
	public void setBirthday(java.util.Date value) {
		this.birthday = value;
	}
	
	public java.util.Date getBirthday() {
		return birthday;
	}
	
	private void setORM_BiometricDatas(java.util.Set value) {
		this.ORM_biometricDatas = value;
	}
	
	private java.util.Set getORM_BiometricDatas() {
		return ORM_biometricDatas;
	}
	
	public final hrclient.BiometricDataSetCollection biometricDatas = new hrclient.BiometricDataSetCollection(this, _ormAdapter, ORMConstants.KEY_CLIENT_BIOMETRICDATAS, ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public Set<BiometricData> getBiometricDatas() {
		//TODO: Implement Method
		throw new UnsupportedOperationException();
	}
	
	public void setBiometricDatas(Set<BiometricData> biometricDatas) {
		//TODO: Implement Method
		throw new UnsupportedOperationException();
	}
	
	public String toString() {
		return String.valueOf(getUsername());
	}
	
}
