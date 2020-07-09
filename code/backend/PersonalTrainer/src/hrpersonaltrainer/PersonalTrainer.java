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

public class PersonalTrainer implements hrpersonaltrainer.IPersonalTrainer {
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
		if (key == hrpersonaltrainer.ORMConstants.KEY_PERSONALTRAINER_CLIENTS) {
			return ORM_clients;
		}
		
		return null;
	}
	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public java.util.Set getSet(int key) {
			return this_getSet(key);
		}
		
	};
	
	private String username;
	
	private float price;
	
	private float classification;
	
	private String skill;
	
	private String password;
	
	private String name;
	
	private String email;
	
	private String sex;
	
	private java.util.Date birthday;
	
	private int numberOfClassifications;
	
	private int numberOfClients;
	
	private int numberOfCreatedPlans;
	
	private boolean certified;
	
	private java.util.Set ORM_clients = new java.util.HashSet();
	
	public void setPrice(float value) {
		this.price = value;
	}
	
	public float getPrice() {
		return price;
	}
	
	public void setClassification(float value) {
		this.classification = value;
	}
	
	public float getClassification() {
		return classification;
	}
	
	public void setSkill(String value) {
		this.skill = value;
	}
	
	public String getSkill() {
		return skill;
	}
	
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
	
	public void setNumberOfClassifications(int value) {
		this.numberOfClassifications = value;
	}
	
	public int getNumberOfClassifications() {
		return numberOfClassifications;
	}
	
	public void setNumberOfClients(int value) {
		this.numberOfClients = value;
	}
	
	public int getNumberOfClients() {
		return numberOfClients;
	}
	
	public void setNumberOfCreatedPlans(int value) {
		this.numberOfCreatedPlans = value;
	}
	
	public int getNumberOfCreatedPlans() {
		return numberOfCreatedPlans;
	}
	
	public void setCertified(boolean value) {
		this.certified = value;
	}
	
	public boolean getCertified() {
		return certified;
	}
	
	private void setORM_Clients(java.util.Set value) {
		this.ORM_clients = value;
	}
	
	private java.util.Set getORM_Clients() {
		return ORM_clients;
	}
	
	public final hrpersonaltrainer.ClientSetCollection clients = new hrpersonaltrainer.ClientSetCollection(this, _ormAdapter, hrpersonaltrainer.ORMConstants.KEY_PERSONALTRAINER_CLIENTS, hrpersonaltrainer.ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public String toString() {
		return String.valueOf(getUsername());
	}
	
}
