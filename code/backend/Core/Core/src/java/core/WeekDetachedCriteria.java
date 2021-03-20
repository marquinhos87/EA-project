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

public class WeekDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression ID;
	public final IntegerExpression number;
	public final DateExpression initialDate;
	public final DateExpression finalDate;
	public final CollectionExpression workouts;
	
	public WeekDetachedCriteria() {
		super(core.Week.class, core.WeekCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		number = new IntegerExpression("number", this.getDetachedCriteria());
		initialDate = new DateExpression("initialDate", this.getDetachedCriteria());
		finalDate = new DateExpression("finalDate", this.getDetachedCriteria());
		workouts = new CollectionExpression("ORM_Workouts", this.getDetachedCriteria());
	}
	
	public WeekDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, core.WeekCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		number = new IntegerExpression("number", this.getDetachedCriteria());
		initialDate = new DateExpression("initialDate", this.getDetachedCriteria());
		finalDate = new DateExpression("finalDate", this.getDetachedCriteria());
		workouts = new CollectionExpression("ORM_Workouts", this.getDetachedCriteria());
	}
	
	public WorkoutDetachedCriteria createWorkoutsCriteria() {
		return new WorkoutDetachedCriteria(createCriteria("ORM_Workouts"));
	}
	
	public Week uniqueWeek(PersistentSession session) {
		return (Week) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Week[] listWeek(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Week[]) list.toArray(new Week[list.size()]);
	}
}

