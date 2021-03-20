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
package notifications;

import org.hibernate.Criteria;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class ClientCriteria extends AbstractORMCriteria {
	public final StringExpression username;
	public final CollectionExpression notifications;
	
	public ClientCriteria(Criteria criteria) {
		super(criteria);
		username = new StringExpression("username", this);
		notifications = new CollectionExpression("ORM_Notifications", this);
	}
	
	public ClientCriteria(PersistentSession session) {
		this(session.createCriteria(Client.class));
	}
	
	public ClientCriteria() throws PersistentException {
		this(DiagramasPersistentManager.instance().getSession());
	}
	
	public notifications.NotificationCriteria createNotificationsCriteria() {
		return new notifications.NotificationCriteria(createCriteria("ORM_Notifications"));
	}
	
	public Client uniqueClient() {
		return (Client) super.uniqueResult();
	}
	
	public Client[] listClient() {
		java.util.List list = super.list();
		return (Client[]) list.toArray(new Client[list.size()]);
	}
}

