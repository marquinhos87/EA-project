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

import org.hibernate.Criteria;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class PlanCriteria extends AbstractORMCriteria {
	public final IntegerExpression ID;
	public final StringExpression personalTrainerId;
	public final AssociationExpression personalTrainer;
	public final BooleanExpression done;
	public final BooleanExpression modified;
	public final DateExpression initialDate;
	public final IntegerExpression currentWeek;
	public final CollectionExpression weeks;
	
	public PlanCriteria(Criteria criteria) {
		super(criteria);
		ID = new IntegerExpression("ID", this);
		personalTrainerId = new StringExpression("personalTrainer.username", this);
		personalTrainer = new AssociationExpression("personalTrainer", this);
		done = new BooleanExpression("done", this);
		modified = new BooleanExpression("modified", this);
		initialDate = new DateExpression("initialDate", this);
		currentWeek = new IntegerExpression("currentWeek", this);
		weeks = new CollectionExpression("ORM_Weeks", this);
	}
	
	public PlanCriteria(PersistentSession session) {
		this(session.createCriteria(Plan.class));
	}
	
	public PlanCriteria() throws PersistentException {
		this(DiagramasPersistentManager.instance().getSession());
	}
	
	public PersonalTrainerCriteria createPersonalTrainerCriteria() {
		return new PersonalTrainerCriteria(createCriteria("personalTrainer"));
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

