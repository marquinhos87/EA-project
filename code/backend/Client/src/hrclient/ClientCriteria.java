/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: jose(Universidade do Minho)
 * License Type: Academic
 */
package hrclient;

import org.hibernate.Criteria;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class ClientCriteria extends AbstractORMCriteria {
	public final StringExpression username;
	public final StringExpression password;
	public final StringExpression name;
	public final StringExpression email;
	public final StringExpression sex;
	public final StringExpression token;
	public final CollectionExpression biometricDatas;
	
	public ClientCriteria(Criteria criteria) {
		super(criteria);
		username = new StringExpression("username", this);
		password = new StringExpression("password", this);
		name = new StringExpression("name", this);
		email = new StringExpression("email", this);
		sex = new StringExpression("sex", this);
		token = new StringExpression("token", this);
		biometricDatas = new CollectionExpression("ORM_BiometricDatas", this);
	}
	
	public ClientCriteria(PersistentSession session) {
		this(session.createCriteria(Client.class));
	}
	
	public ClientCriteria() throws PersistentException {
		this(hrclient.DiagramasPersistentManager.instance().getSession());
	}
	
	public hrclient.BiometricDataCriteria createBiometricDatasCriteria() {
		return new hrclient.BiometricDataCriteria(createCriteria("ORM_BiometricDatas"));
	}
	
	public Client uniqueClient() {
		return (Client) super.uniqueResult();
	}
	
	public Client[] listClient() {
		java.util.List list = super.list();
		return (Client[]) list.toArray(new Client[list.size()]);
	}
}

