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
package core.data;

import org.hibernate.Criteria;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class WeekCriteria extends AbstractORMCriteria {
	public final IntegerExpression ID;
	public final IntegerExpression number;
	public final CollectionExpression workouts;
	
	public WeekCriteria(Criteria criteria) {
		super(criteria);
		ID = new IntegerExpression("ID", this);
		number = new IntegerExpression("number", this);
		workouts = new CollectionExpression("ORM_Workouts", this);
	}
	
	public WeekCriteria(PersistentSession session) {
		this(session.createCriteria(Week.class));
	}
	
	public WeekCriteria() throws PersistentException {
		this(DiagramasPersistentManager.instance().getSession());
	}
	
	public WorkoutCriteria createWorkoutsCriteria() {
		return new WorkoutCriteria(createCriteria("ORM_Workouts"));
	}
	
	public Week uniqueWeek() {
		return (Week) super.uniqueResult();
	}
	
	public Week[] listWeek() {
		java.util.List list = super.list();
		return (Week[]) list.toArray(new Week[list.size()]);
	}
}

