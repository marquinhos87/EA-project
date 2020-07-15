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

public class Client {
    public Client() {
    }

    @Override
    public boolean equals(Object aObj) {
        if (aObj == this)
            return true;
        if (!(aObj instanceof Client))
            return false;
        Client client = (Client)aObj;
        return !((getUsername() != null && !getUsername().equals(client.getUsername())) || (getUsername() == null && client.getUsername() != null));
    }

    @Override
    public int hashCode() {
        int hashcode = 0;
        hashcode = hashcode + (getUsername() == null ? 0 : getUsername().hashCode());
        return hashcode;
    }

    private String username;

    private core.Plan plan;

    public void setUsername(String value) {
        this.username = value;
    }

    public String getUsername() {
        return username;
    }

    public String getORMID() {
        return getUsername();
    }

    public void setPlan(core.Plan value) {
        this.plan = value;
    }

    public core.Plan getPlan() {
        return plan;
    }

    @Override
    public String toString() {
        return "Client{" + "username=" + username + ", plan=" + plan + '}';
    }	
}
