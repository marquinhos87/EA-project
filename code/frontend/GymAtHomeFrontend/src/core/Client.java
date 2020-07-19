package core;

public class Client {
	public Client() {
	}
	
	public String username;
	
	public boolean submitedClassification;
	
	public void setUsername(String value) {
		this.username = value;
	}
	
	public String getUsername() {
		return username;
	}

	
	public void setSubmitedClassification(boolean value) {
		this.submitedClassification = value;
	}
	
	public boolean getSubmitedClassification() {
		return submitedClassification;
	}

	@Override
	public String toString() {
		return "Client{" +
				"username='" + username + '\'' +
				", submitedClassification=" + submitedClassification +
				'}';
	}
}
