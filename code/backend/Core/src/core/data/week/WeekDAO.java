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
package core.data.week;

import core.data.DiagramasPersistentManager;
import org.orm.*;
import org.hibernate.Query;

import java.util.List;

public class WeekDAO {
	public static Week loadWeekByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return loadWeekByORMID(session, ID);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Week getWeekByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return getWeekByORMID(session, ID);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Week loadWeekByORMID(int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return loadWeekByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Week getWeekByORMID(int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return getWeekByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Week loadWeekByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (Week) session.load(Week.class, new Integer(ID));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Week getWeekByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (Week) session.get(Week.class, new Integer(ID));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Week loadWeekByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Week) session.load(Week.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Week getWeekByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Week) session.get(Week.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryWeek(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return queryWeek(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryWeek(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return queryWeek(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Week[] listWeekByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return listWeekByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Week[] listWeekByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return listWeekByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryWeek(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From core.data.week.Week as Week");
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
	
	public static List queryWeek(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From core.data.week.Week as Week");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Week", lockMode);
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Week[] listWeekByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryWeek(session, condition, orderBy);
			return (Week[]) list.toArray(new Week[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Week[] listWeekByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryWeek(session, condition, orderBy, lockMode);
			return (Week[]) list.toArray(new Week[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Week loadWeekByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return loadWeekByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Week loadWeekByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return loadWeekByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Week loadWeekByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		Week[] weeks = listWeekByQuery(session, condition, orderBy);
		if (weeks != null && weeks.length > 0)
			return weeks[0];
		else
			return null;
	}
	
	public static Week loadWeekByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		Week[] weeks = listWeekByQuery(session, condition, orderBy, lockMode);
		if (weeks != null && weeks.length > 0)
			return weeks[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateWeekByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return iterateWeekByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateWeekByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return iterateWeekByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateWeekByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From core.data.week.Week as Week");
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
	
	public static java.util.Iterator iterateWeekByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From core.data.week.Week as Week");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Week", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Week createWeek() {
		return new Week();
	}
	
	public static boolean save(Week week) throws PersistentException {
		try {
			DiagramasPersistentManager.instance().saveObject(week);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean delete(Week week) throws PersistentException {
		try {
			DiagramasPersistentManager.instance().deleteObject(week);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean refresh(Week week) throws PersistentException {
		try {
			DiagramasPersistentManager.instance().getSession().refresh(week);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean evict(Week week) throws PersistentException {
		try {
			DiagramasPersistentManager.instance().getSession().evict(week);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Week loadWeekByCriteria(WeekCriteria weekCriteria) {
		Week[] weeks = listWeekByCriteria(weekCriteria);
		if(weeks == null || weeks.length == 0) {
			return null;
		}
		return weeks[0];
	}
	
	public static Week[] listWeekByCriteria(WeekCriteria weekCriteria) {
		return weekCriteria.listWeek();
	}
}
