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
package core.data.plan;

import java.util.List;

import core.data.week.WeekDetachedCriteria;
import org.hibernate.criterion.DetachedCriteria;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class PlanDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression ID;
	public final IntegerExpression currentWeekId;
	public final AssociationExpression currentWeek;
	public final BooleanExpression done;
	public final BooleanExpression modified;
	public final CollectionExpression weeks;
	
	public PlanDetachedCriteria() {
		super(Plan.class, PlanCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		currentWeekId = new IntegerExpression("currentWeek.ID", this.getDetachedCriteria());
		currentWeek = new AssociationExpression("currentWeek", this.getDetachedCriteria());
		done = new BooleanExpression("done", this.getDetachedCriteria());
		modified = new BooleanExpression("modified", this.getDetachedCriteria());
		weeks = new CollectionExpression("ORM_Weeks", this.getDetachedCriteria());
	}
	
	public PlanDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, PlanCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		currentWeekId = new IntegerExpression("currentWeek.ID", this.getDetachedCriteria());
		currentWeek = new AssociationExpression("currentWeek", this.getDetachedCriteria());
		done = new BooleanExpression("done", this.getDetachedCriteria());
		modified = new BooleanExpression("modified", this.getDetachedCriteria());
		weeks = new CollectionExpression("ORM_Weeks", this.getDetachedCriteria());
	}
	
	public WeekDetachedCriteria createCurrentWeekCriteria() {
		return new WeekDetachedCriteria(createCriteria("currentWeek"));
	}
	
	public WeekDetachedCriteria createWeeksCriteria() {
		return new WeekDetachedCriteria(createCriteria("ORM_Weeks"));
	}
	
	public Plan uniquePlan(PersistentSession session) {
		return (Plan) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Plan[] listPlan(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Plan[]) list.toArray(new Plan[list.size()]);
	}
}

