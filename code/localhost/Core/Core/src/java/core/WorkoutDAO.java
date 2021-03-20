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
package core;

import org.orm.*;
import org.hibernate.Query;
import org.hibernate.LockMode;
import java.util.List;

public class WorkoutDAO {
	public static Workout loadWorkoutByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return loadWorkoutByORMID(session, ID);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Workout getWorkoutByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return getWorkoutByORMID(session, ID);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Workout loadWorkoutByORMID(int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return loadWorkoutByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Workout getWorkoutByORMID(int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return getWorkoutByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Workout loadWorkoutByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (Workout) session.load(core.Workout.class, new Integer(ID));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Workout getWorkoutByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (Workout) session.get(core.Workout.class, new Integer(ID));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Workout loadWorkoutByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Workout) session.load(core.Workout.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Workout getWorkoutByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Workout) session.get(core.Workout.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryWorkout(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return queryWorkout(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryWorkout(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return queryWorkout(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Workout[] listWorkoutByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return listWorkoutByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Workout[] listWorkoutByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return listWorkoutByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryWorkout(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From core.Workout as Workout");
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
	
	public static List queryWorkout(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From core.Workout as Workout");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Workout", lockMode);
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Workout[] listWorkoutByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryWorkout(session, condition, orderBy);
			return (Workout[]) list.toArray(new Workout[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Workout[] listWorkoutByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryWorkout(session, condition, orderBy, lockMode);
			return (Workout[]) list.toArray(new Workout[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Workout loadWorkoutByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return loadWorkoutByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Workout loadWorkoutByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return loadWorkoutByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Workout loadWorkoutByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		Workout[] workouts = listWorkoutByQuery(session, condition, orderBy);
		if (workouts != null && workouts.length > 0)
			return workouts[0];
		else
			return null;
	}
	
	public static Workout loadWorkoutByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		Workout[] workouts = listWorkoutByQuery(session, condition, orderBy, lockMode);
		if (workouts != null && workouts.length > 0)
			return workouts[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateWorkoutByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return iterateWorkoutByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateWorkoutByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return iterateWorkoutByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateWorkoutByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From core.Workout as Workout");
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
	
	public static java.util.Iterator iterateWorkoutByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From core.Workout as Workout");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Workout", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Workout createWorkout() {
		return new core.Workout();
	}
	
	public static boolean save(core.Workout workout) throws PersistentException {
		try {
			DiagramasPersistentManager.instance().saveObject(workout);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean delete(core.Workout workout) throws PersistentException {
		try {
			DiagramasPersistentManager.instance().deleteObject(workout);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(core.Workout workout)throws PersistentException {
		try {
			if (workout.getWeek() != null) {
				workout.getWeek().workouts.remove(workout);
			}
			
			return delete(workout);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(core.Workout workout, org.orm.PersistentSession session)throws PersistentException {
		try {
			if (workout.getWeek() != null) {
				workout.getWeek().workouts.remove(workout);
			}
			
			try {
				session.delete(workout);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean refresh(core.Workout workout) throws PersistentException {
		try {
			DiagramasPersistentManager.instance().getSession().refresh(workout);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean evict(core.Workout workout) throws PersistentException {
		try {
			DiagramasPersistentManager.instance().getSession().evict(workout);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Workout loadWorkoutByCriteria(WorkoutCriteria workoutCriteria) {
		Workout[] workouts = listWorkoutByCriteria(workoutCriteria);
		if(workouts == null || workouts.length == 0) {
			return null;
		}
		return workouts[0];
	}
	
	public static Workout[] listWorkoutByCriteria(WorkoutCriteria workoutCriteria) {
		return workoutCriteria.listWorkout();
	}
}
