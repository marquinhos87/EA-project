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

public class User {
    public User() {
    }

    @Override
    public boolean equals(Object aObj) {
        if (aObj == this)
            return true;
        if (!(aObj instanceof User))
            return false;
        User user = (User)aObj;
        return !((getUsername() != null && !getUsername().equals(user.getUsername())) || (getUsername() == null && user.getUsername() != null));
    }

    @Override
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

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", token=" + token + '}';
    }	
}
