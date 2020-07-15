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

public class PersonalTrainerDetachedCriteria extends AbstractORMDetachedCriteria {
	public final StringExpression username;
	public final CollectionExpression plans;
	
	public PersonalTrainerDetachedCriteria() {
		super(core.PersonalTrainer.class, core.PersonalTrainerCriteria.class);
		username = new StringExpression("username", this.getDetachedCriteria());
		plans = new CollectionExpression("ORM_Plans", this.getDetachedCriteria());
	}
	
	public PersonalTrainerDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, core.PersonalTrainerCriteria.class);
		username = new StringExpression("username", this.getDetachedCriteria());
		plans = new CollectionExpression("ORM_Plans", this.getDetachedCriteria());
	}
	
	public PlanDetachedCriteria createPlansCriteria() {
		return new PlanDetachedCriteria(createCriteria("ORM_Plans"));
	}
	
	public PersonalTrainer uniquePersonalTrainer(PersistentSession session) {
		return (PersonalTrainer) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public PersonalTrainer[] listPersonalTrainer(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (PersonalTrainer[]) list.toArray(new PersonalTrainer[list.size()]);
	}
}

