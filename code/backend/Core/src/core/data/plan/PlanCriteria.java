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

import core.data.DiagramasPersistentManager;
import core.data.week.WeekCriteria;
import org.hibernate.Criteria;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class PlanCriteria extends AbstractORMCriteria {
	public final IntegerExpression ID;
	public final IntegerExpression currentWeekId;
	public final AssociationExpression currentWeek;
	public final BooleanExpression done;
	public final BooleanExpression modified;
	public final CollectionExpression weeks;
	
	public PlanCriteria(Criteria criteria) {
		super(criteria);
		ID = new IntegerExpression("ID", this);
		currentWeekId = new IntegerExpression("currentWeek.ID", this);
		currentWeek = new AssociationExpression("currentWeek", this);
		done = new BooleanExpression("done", this);
		modified = new BooleanExpression("modified", this);
		weeks = new CollectionExpression("ORM_Weeks", this);
	}
	
	public PlanCriteria(PersistentSession session) {
		this(session.createCriteria(Plan.class));
	}
	
	public PlanCriteria() throws PersistentException {
		this(DiagramasPersistentManager.instance().getSession());
	}
	
	public WeekCriteria createCurrentWeekCriteria() {
		return new WeekCriteria(createCriteria("currentWeek"));
	}
	
	public WeekCriteria createWeeksCriteria() {
		return new WeekCriteria(createCriteria("ORM_Weeks"));
	}
	
	public Plan uniquePlan() {
		return (Plan) super.uniqueResult();
	}
	
	public Plan[] listPlan() {
		java.util.List list = super.list();
		return (Plan[]) list.toArray(new Plan[list.size()]);
	}
}

