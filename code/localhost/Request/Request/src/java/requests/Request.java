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

public class Request {
	public Request() {
	}
	
	private void this_setOwner(Object owner, int key) {
		if (key == ORMConstants.KEY_REQUEST_CLIENT) {
			this.client = (requests.Client) owner;
		}
		
		else if (key == ORMConstants.KEY_REQUEST_PERSONALTRAINER) {
			this.personalTrainer = (requests.PersonalTrainer) owner;
		}
	}
	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public void setOwner(Object owner, int key) {
			this_setOwner(owner, key);
		}
		
	};
	
	private int ID;
	
	private requests.PersonalTrainer personalTrainer;
	
	private requests.Client client;
	
	private int numberOfWeeks;
	
	private String objective;
	
	private int workoutPerWeek;
	
	private String weekDays;
	
	private int level;
	
	private int state;
	
	private void setID(int value) {
		this.ID = value;
	}
	
	public int getID() {
		return ID;
	}
	
	public int getORMID() {
		return getID();
	}
	
	public void setNumberOfWeeks(int value) {
		this.numberOfWeeks = value;
	}
	
	public int getNumberOfWeeks() {
		return numberOfWeeks;
	}
	
	public void setObjective(String value) {
		this.objective = value;
	}
	
	public String getObjective() {
		return objective;
	}
	
	public void setWorkoutPerWeek(int value) {
		this.workoutPerWeek = value;
	}
	
	public int getWorkoutPerWeek() {
		return workoutPerWeek;
	}
	
	public void setWeekDays(String value) {
		this.weekDays = value;
	}
	
	public String getWeekDays() {
		return weekDays;
	}
	
	public void setLevel(int value) {
		this.level = value;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setState(int value) {
		this.state = value;
	}
	
	public int getState() {
		return state;
	}
	
	public void setClient(requests.Client value) {
		if (client != null) {
			client.requests.remove(this);
		}
		if (value != null) {
			value.requests.add(this);
		}
	}
	
	public requests.Client getClient() {
		return client;
	}
	
	/**
	 * This method is for internal use only.
	 */
	public void setORM_Client(requests.Client value) {
		this.client = value;
	}
	
	private requests.Client getORM_Client() {
		return client;
	}
	
	public void setPersonalTrainer(requests.PersonalTrainer value) {
		if (personalTrainer != null) {
			personalTrainer.requests.remove(this);
		}
		if (value != null) {
			value.requests.add(this);
		}
	}
	
	public requests.PersonalTrainer getPersonalTrainer() {
		return personalTrainer;
	}
	
	/**
	 * This method is for internal use only.
	 */
	public void setORM_PersonalTrainer(requests.PersonalTrainer value) {
		this.personalTrainer = value;
	}
	
	private requests.PersonalTrainer getORM_PersonalTrainer() {
		return personalTrainer;
	}
	
	public int getWokoutPerWeek() {
		//TODO: Implement Method
		throw new UnsupportedOperationException();
	}
	
	public void setWokoutPerWeek(int wokoutPerWeek) {
		//TODO: Implement Method
		throw new UnsupportedOperationException();
	}
	
	public String toString() {
		return String.valueOf(getID());
	}
        
        public String toJson(){
            StringBuilder sb = new StringBuilder("{");
            sb.append("\"ID\":").append(this.ID).append(",");
            sb.append("\"clientUsername\":").append("\"" +this.client.getUsername()+ "\"").append(",");
            sb.append("\"personalTrainerUsername\":").append("\"" +this.personalTrainer.getUsername()+ "\"").append(",");
            sb.append("\"numberOfWeeks\":").append(this.numberOfWeeks).append(",");
            sb.append("\"objective\":").append("\"" + this.objective + "\"").append(",");
            sb.append("\"workoutPerWeek\":").append(this.workoutPerWeek).append(",");
            sb.append("\"weekDays\":").append("\"" + this.weekDays + "\"").append(",");
            sb.append("\"level\":").append(this.level).append(",");
            sb.append("\"state\":").append(this.state);
            
            sb.append("}");
            return sb.toString();
        }
	
}
