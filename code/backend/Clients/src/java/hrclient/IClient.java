package hrclient;

import java.util.Date;
import java.util.Set;

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

	Date getBirthday();

	/**
	 * 
	 * @param birthday
	 */
	void setBirthday(Date birthday);

	Set<BiometricData> getBiometricDatas();

	/**
	 * 
	 * @param biometricDatas
	 */
	void setBiometricDatas(Set<BiometricData> biometricDatas);

}