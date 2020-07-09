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

public class NotificationDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression ID;
	public final DateExpression date;
	public final BooleanExpression read;
	public final StringExpression description;
	
	public NotificationDetachedCriteria() {
		super(notifications.Notification.class, notifications.NotificationCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		date = new DateExpression("date", this.getDetachedCriteria());
		read = new BooleanExpression("read", this.getDetachedCriteria());
		description = new StringExpression("description", this.getDetachedCriteria());
	}
	
	public NotificationDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, notifications.NotificationCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		date = new DateExpression("date", this.getDetachedCriteria());
		read = new BooleanExpression("read", this.getDetachedCriteria());
		description = new StringExpression("description", this.getDetachedCriteria());
	}
	
	public Notification uniqueNotification(PersistentSession session) {
		return (Notification) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Notification[] listNotification(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Notification[]) list.toArray(new Notification[list.size()]);
	}
}

