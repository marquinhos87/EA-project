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
package core.data.workout;

import core.data.DiagramasPersistentManager;
import core.data.task.TaskCriteria;
import org.hibernate.Criteria;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class WorkoutCriteria extends AbstractORMCriteria {
	public final IntegerExpression ID;
	public final StringExpression designation;
	public final DateExpression date;
	public final BooleanExpression done;
	public final CollectionExpression tasks;
	
	public WorkoutCriteria(Criteria criteria) {
		super(criteria);
		ID = new IntegerExpression("ID", this);
		designation = new StringExpression("designation", this);
		date = new DateExpression("date", this);
		done = new BooleanExpression("done", this);
		tasks = new CollectionExpression("ORM_Tasks", this);
	}
	
	public WorkoutCriteria(PersistentSession session) {
		this(session.createCriteria(Workout.class));
	}
	
	public WorkoutCriteria() throws PersistentException {
		this(DiagramasPersistentManager.instance().getSession());
	}
	
	public TaskCriteria createTasksCriteria() {
		return new TaskCriteria(createCriteria("ORM_Tasks"));
	}
	
	public Workout uniqueWorkout() {
		return (Workout) super.uniqueResult();
	}
	
	public Workout[] listWorkout() {
		java.util.List list = super.list();
		return (Workout[]) list.toArray(new Workout[list.size()]);
	}
}

