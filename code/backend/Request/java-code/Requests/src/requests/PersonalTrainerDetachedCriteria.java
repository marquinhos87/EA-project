/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: josepereira(Universidade do Minho)
 * License Type: Academic
 */
package requests;

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class PersonalTrainerDetachedCriteria extends AbstractORMDetachedCriteria {
	public final StringExpression username;
	public final CollectionExpression requests;
	
	public PersonalTrainerDetachedCriteria() {
		super(requests.PersonalTrainer.class, requests.PersonalTrainerCriteria.class);
		username = new StringExpression("username", this.getDetachedCriteria());
		requests = new CollectionExpression("ORM_Requests", this.getDetachedCriteria());
	}
	
	public PersonalTrainerDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, requests.PersonalTrainerCriteria.class);
		username = new StringExpression("username", this.getDetachedCriteria());
		requests = new CollectionExpression("ORM_Requests", this.getDetachedCriteria());
	}
	
	public RequestDetachedCriteria createRequestsCriteria() {
		return new RequestDetachedCriteria(createCriteria("ORM_Requests"));
	}
	
	public PersonalTrainer uniquePersonalTrainer(PersistentSession session) {
		return (PersonalTrainer) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public PersonalTrainer[] listPersonalTrainer(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (PersonalTrainer[]) list.toArray(new PersonalTrainer[list.size()]);
	}
}

