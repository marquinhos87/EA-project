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

import org.orm.*;
import org.hibernate.Query;
import org.hibernate.LockMode;
import java.util.List;

public class NotificationDAO {
	public static Notification loadNotificationByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = notifications.DiagramasPersistentManager.instance().getSession();
			return loadNotificationByORMID(session, ID);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Notification getNotificationByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = notifications.DiagramasPersistentManager.instance().getSession();
			return getNotificationByORMID(session, ID);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Notification loadNotificationByORMID(int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = notifications.DiagramasPersistentManager.instance().getSession();
			return loadNotificationByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Notification getNotificationByORMID(int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = notifications.DiagramasPersistentManager.instance().getSession();
			return getNotificationByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Notification loadNotificationByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (Notification) session.load(notifications.Notification.class, new Integer(ID));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Notification getNotificationByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (Notification) session.get(notifications.Notification.class, new Integer(ID));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Notification loadNotificationByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Notification) session.load(notifications.Notification.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Notification getNotificationByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Notification) session.get(notifications.Notification.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryNotification(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = notifications.DiagramasPersistentManager.instance().getSession();
			return queryNotification(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryNotification(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = notifications.DiagramasPersistentManager.instance().getSession();
			return queryNotification(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Notification[] listNotificationByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = notifications.DiagramasPersistentManager.instance().getSession();
			return listNotificationByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Notification[] listNotificationByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = notifications.DiagramasPersistentManager.instance().getSession();
			return listNotificationByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryNotification(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From notifications.Notification as Notification");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryNotification(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From notifications.Notification as Notification");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Notification", lockMode);
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Notification[] listNotificationByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryNotification(session, condition, orderBy);
			return (Notification[]) list.toArray(new Notification[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Notification[] listNotificationByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryNotification(session, condition, orderBy, lockMode);
			return (Notification[]) list.toArray(new Notification[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Notification loadNotificationByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = notifications.DiagramasPersistentManager.instance().getSession();
			return loadNotificationByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Notification loadNotificationByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = notifications.DiagramasPersistentManager.instance().getSession();
			return loadNotificationByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Notification loadNotificationByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		Notification[] notifications = listNotificationByQuery(session, condition, orderBy);
		if (notifications != null && notifications.length > 0)
			return notifications[0];
		else
			return null;
	}
	
	public static Notification loadNotificationByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		Notification[] notifications = listNotificationByQuery(session, condition, orderBy, lockMode);
		if (notifications != null && notifications.length > 0)
			return notifications[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateNotificationByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = notifications.DiagramasPersistentManager.instance().getSession();
			return iterateNotificationByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateNotificationByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = notifications.DiagramasPersistentManager.instance().getSession();
			return iterateNotificationByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateNotificationByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From notifications.Notification as Notification");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateNotificationByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From notifications.Notification as Notification");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Notification", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Notification createNotification() {
		return new notifications.Notification();
	}
	
	public static boolean save(notifications.Notification notification) throws PersistentException {
		try {
			notifications.DiagramasPersistentManager.instance().saveObject(notification);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean delete(notifications.Notification notification) throws PersistentException {
		try {
			notifications.DiagramasPersistentManager.instance().deleteObject(notification);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean refresh(notifications.Notification notification) throws PersistentException {
		try {
			notifications.DiagramasPersistentManager.instance().getSession().refresh(notification);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean evict(notifications.Notification notification) throws PersistentException {
		try {
			notifications.DiagramasPersistentManager.instance().getSession().evict(notification);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Notification loadNotificationByCriteria(NotificationCriteria notificationCriteria) {
		Notification[] notifications = listNotificationByCriteria(notificationCriteria);
		if(notifications == null || notifications.length == 0) {
			return null;
		}
		return notifications[0];
	}
	
	public static Notification[] listNotificationByCriteria(NotificationCriteria notificationCriteria) {
		return notificationCriteria.listNotification();
	}
}
