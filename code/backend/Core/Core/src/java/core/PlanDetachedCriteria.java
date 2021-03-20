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

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class PlanDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression ID;
	public final StringExpression personalTrainerId;
	public final AssociationExpression personalTrainer;
	public final BooleanExpression done;
	public final BooleanExpression modified;
	public final DateExpression initialDate;
	public final IntegerExpression currentWeek;
	public final CollectionExpression weeks;
	
	public PlanDetachedCriteria() {
		super(core.Plan.class, core.PlanCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		personalTrainerId = new StringExpression("personalTrainer.username", this.getDetachedCriteria());
		personalTrainer = new AssociationExpression("personalTrainer", this.getDetachedCriteria());
		done = new BooleanExpression("done", this.getDetachedCriteria());
		modified = new BooleanExpression("modified", this.getDetachedCriteria());
		initialDate = new DateExpression("initialDate", this.getDetachedCriteria());
		currentWeek = new IntegerExpression("currentWeek", this.getDetachedCriteria());
		weeks = new CollectionExpression("ORM_Weeks", this.getDetachedCriteria());
	}
	
	public PlanDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, core.PlanCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		personalTrainerId = new StringExpression("personalTrainer.username", this.getDetachedCriteria());
		personalTrainer = new AssociationExpression("personalTrainer", this.getDetachedCriteria());
		done = new BooleanExpression("done", this.getDetachedCriteria());
		modified = new BooleanExpression("modified", this.getDetachedCriteria());
		initialDate = new DateExpression("initialDate", this.getDetachedCriteria());
		currentWeek = new IntegerExpression("currentWeek", this.getDetachedCriteria());
		weeks = new CollectionExpression("ORM_Weeks", this.getDetachedCriteria());
	}
	
	public PersonalTrainerDetachedCriteria createPersonalTrainerCriteria() {
		return new PersonalTrainerDetachedCriteria(createCriteria("personalTrainer"));
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

