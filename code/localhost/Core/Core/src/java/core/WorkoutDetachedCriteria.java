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

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class WorkoutDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression ID;
	public final IntegerExpression weekId;
	public final AssociationExpression week;
	public final StringExpression designation;
	public final DateExpression date;
	public final BooleanExpression done;
	public final CollectionExpression tasks;
	
	public WorkoutDetachedCriteria() {
		super(core.Workout.class, core.WorkoutCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		weekId = new IntegerExpression("week.ID", this.getDetachedCriteria());
		week = new AssociationExpression("week", this.getDetachedCriteria());
		designation = new StringExpression("designation", this.getDetachedCriteria());
		date = new DateExpression("date", this.getDetachedCriteria());
		done = new BooleanExpression("done", this.getDetachedCriteria());
		tasks = new CollectionExpression("ORM_Tasks", this.getDetachedCriteria());
	}
	
	public WorkoutDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, core.WorkoutCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		weekId = new IntegerExpression("week.ID", this.getDetachedCriteria());
		week = new AssociationExpression("week", this.getDetachedCriteria());
		designation = new StringExpression("designation", this.getDetachedCriteria());
		date = new DateExpression("date", this.getDetachedCriteria());
		done = new BooleanExpression("done", this.getDetachedCriteria());
		tasks = new CollectionExpression("ORM_Tasks", this.getDetachedCriteria());
	}
	
	public WeekDetachedCriteria createWeekCriteria() {
		return new WeekDetachedCriteria(createCriteria("week"));
	}
	
	public TaskDetachedCriteria createTasksCriteria() {
		return new TaskDetachedCriteria(createCriteria("ORM_Tasks"));
	}
	
	public Workout uniqueWorkout(PersistentSession session) {
		return (Workout) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Workout[] listWorkout(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Workout[]) list.toArray(new Workout[list.size()]);
	}
}

