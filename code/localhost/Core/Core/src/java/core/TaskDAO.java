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

import org.orm.*;
import org.hibernate.Query;
import org.hibernate.LockMode;
import java.util.List;

public class TaskDAO {
	public static Task loadTaskByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return loadTaskByORMID(session, ID);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Task getTaskByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return getTaskByORMID(session, ID);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Task loadTaskByORMID(int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return loadTaskByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Task getTaskByORMID(int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return getTaskByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Task loadTaskByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (Task) session.load(core.Task.class, new Integer(ID));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Task getTaskByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (Task) session.get(core.Task.class, new Integer(ID));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Task loadTaskByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Task) session.load(core.Task.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Task getTaskByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Task) session.get(core.Task.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryTask(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return queryTask(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryTask(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return queryTask(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Task[] listTaskByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return listTaskByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Task[] listTaskByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return listTaskByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryTask(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From core.Task as Task");
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
	
	public static List queryTask(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From core.Task as Task");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Task", lockMode);
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Task[] listTaskByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryTask(session, condition, orderBy);
			return (Task[]) list.toArray(new Task[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Task[] listTaskByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryTask(session, condition, orderBy, lockMode);
			return (Task[]) list.toArray(new Task[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Task loadTaskByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return loadTaskByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Task loadTaskByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return loadTaskByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Task loadTaskByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		Task[] tasks = listTaskByQuery(session, condition, orderBy);
		if (tasks != null && tasks.length > 0)
			return tasks[0];
		else
			return null;
	}
	
	public static Task loadTaskByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		Task[] tasks = listTaskByQuery(session, condition, orderBy, lockMode);
		if (tasks != null && tasks.length > 0)
			return tasks[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateTaskByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return iterateTaskByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateTaskByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return iterateTaskByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateTaskByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From core.Task as Task");
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
	
	public static java.util.Iterator iterateTaskByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From core.Task as Task");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Task", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Task createTask() {
		return new core.Task();
	}
	
	public static boolean save(core.Task task) throws PersistentException {
		try {
			DiagramasPersistentManager.instance().saveObject(task);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean delete(core.Task task) throws PersistentException {
		try {
			DiagramasPersistentManager.instance().deleteObject(task);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean refresh(core.Task task) throws PersistentException {
		try {
			DiagramasPersistentManager.instance().getSession().refresh(task);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean evict(core.Task task) throws PersistentException {
		try {
			DiagramasPersistentManager.instance().getSession().evict(task);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Task loadTaskByCriteria(TaskCriteria taskCriteria) {
		Task[] tasks = listTaskByCriteria(taskCriteria);
		if(tasks == null || tasks.length == 0) {
			return null;
		}
		return tasks[0];
	}
	
	public static Task[] listTaskByCriteria(TaskCriteria taskCriteria) {
		return taskCriteria.listTask();
	}
}
