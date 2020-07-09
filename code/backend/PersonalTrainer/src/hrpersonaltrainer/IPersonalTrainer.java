package hrpersonaltrainer;

import java.util.Date;

public interface IPersonalTrainer {

	String getUsername();

	/**
	 * 
	 * @param username
	 */
	void setUsername(String username);

	String getPassword();

	/**
	 * 
	 * @param password
	 */
	void setPassword(String password);

	String getEmail();

	/**
	 * 
	 * @param email
	 */
	void setEmail(String email);

	String getSex();

	/**
	 * 
	 * @param sex
	 */
	void setSex(String sex);

	String getName();

	/**
	 * 
	 * @param name
	 */
	void setName(String name);

	Date getBirthday();

	/**
	 * 
	 * @param birthday
	 */
	void setBirthday(Date birthday);

	float getPrice();

	/**
	 * 
	 * @param price
	 */
	void setPrice(float price);

	float getClassification();

	/**
	 * 
	 * @param classification
	 */
	void setClassification(float classification);

	/**
	 * 
	 * @param skill
	 */
	void setSkill(String skill);

	String getSkill();
}