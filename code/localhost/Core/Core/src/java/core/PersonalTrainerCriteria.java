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

public class PersonalTrainerCriteria extends AbstractORMCriteria {
	public final StringExpression username;
	public final CollectionExpression plans;
	
	public PersonalTrainerCriteria(Criteria criteria) {
		super(criteria);
		username = new StringExpression("username", this);
		plans = new CollectionExpression("ORM_Plans", this);
	}
	
	public PersonalTrainerCriteria(PersistentSession session) {
		this(session.createCriteria(PersonalTrainer.class));
	}
	
	public PersonalTrainerCriteria() throws PersistentException {
		this(DiagramasPersistentManager.instance().getSession());
	}
	
	public PlanCriteria createPlansCriteria() {
		return new PlanCriteria(createCriteria("ORM_Plans"));
	}
	
	public PersonalTrainer uniquePersonalTrainer() {
		return (PersonalTrainer) super.uniqueResult();
	}
	
	public PersonalTrainer[] listPersonalTrainer() {
		java.util.List list = super.list();
		return (PersonalTrainer[]) list.toArray(new PersonalTrainer[list.size()]);
	}
}

