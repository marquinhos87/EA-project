package hrclient;

import java.time.LocalDate;
import java.util.Collection;

public interface IClient {

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

	LocalDate getBirthday();

	/**
	 * 
	 * @param birthday
	 */
	void setBirthday(LocalDate birthday);

}