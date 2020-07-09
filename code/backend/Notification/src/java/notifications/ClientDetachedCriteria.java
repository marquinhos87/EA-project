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

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class ClientDetachedCriteria extends AbstractORMDetachedCriteria {
	public final StringExpression username;
	public final CollectionExpression notifications;
	
	public ClientDetachedCriteria() {
		super(notifications.Client.class, notifications.ClientCriteria.class);
		username = new StringExpression("username", this.getDetachedCriteria());
		notifications = new CollectionExpression("ORM_Notifications", this.getDetachedCriteria());
	}
	
	public ClientDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, notifications.ClientCriteria.class);
		username = new StringExpression("username", this.getDetachedCriteria());
		notifications = new CollectionExpression("ORM_Notifications", this.getDetachedCriteria());
	}
	
	public notifications.NotificationDetachedCriteria createNotificationsCriteria() {
		return new notifications.NotificationDetachedCriteria(createCriteria("ORM_Notifications"));
	}
	
	public Client uniqueClient(PersistentSession session) {
		return (Client) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Client[] listClient(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Client[]) list.toArray(new Client[list.size()]);
	}
}

