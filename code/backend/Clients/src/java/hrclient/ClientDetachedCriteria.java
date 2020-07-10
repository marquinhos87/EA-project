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

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class ClientDetachedCriteria extends AbstractORMDetachedCriteria {
	public final StringExpression username;
	public final StringExpression password;
	public final StringExpression name;
	public final StringExpression email;
	public final StringExpression sex;
	public final DateExpression birthday;
	public final CollectionExpression biometricDatas;
	
	public ClientDetachedCriteria() {
		super(hrclient.Client.class, hrclient.ClientCriteria.class);
		username = new StringExpression("username", this.getDetachedCriteria());
		password = new StringExpression("password", this.getDetachedCriteria());
		name = new StringExpression("name", this.getDetachedCriteria());
		email = new StringExpression("email", this.getDetachedCriteria());
		sex = new StringExpression("sex", this.getDetachedCriteria());
		birthday = new DateExpression("birthday", this.getDetachedCriteria());
		biometricDatas = new CollectionExpression("ORM_BiometricDatas", this.getDetachedCriteria());
	}
	
	public ClientDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, hrclient.ClientCriteria.class);
		username = new StringExpression("username", this.getDetachedCriteria());
		password = new StringExpression("password", this.getDetachedCriteria());
		name = new StringExpression("name", this.getDetachedCriteria());
		email = new StringExpression("email", this.getDetachedCriteria());
		sex = new StringExpression("sex", this.getDetachedCriteria());
		birthday = new DateExpression("birthday", this.getDetachedCriteria());
		biometricDatas = new CollectionExpression("ORM_BiometricDatas", this.getDetachedCriteria());
	}
	
	public BiometricDataDetachedCriteria createBiometricDatasCriteria() {
		return new BiometricDataDetachedCriteria(createCriteria("ORM_BiometricDatas"));
	}
	
	public Client uniqueClient(PersistentSession session) {
		return (Client) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Client[] listClient(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Client[]) list.toArray(new Client[list.size()]);
	}
}

