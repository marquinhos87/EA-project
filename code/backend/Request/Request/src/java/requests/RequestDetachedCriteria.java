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

public class RequestDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression ID;
	public final StringExpression personalTrainerId;
	public final AssociationExpression personalTrainer;
	public final StringExpression clientId;
	public final AssociationExpression client;
	public final IntegerExpression numberOfWeeks;
	public final StringExpression objective;
	public final IntegerExpression workoutPerWeek;
	public final StringExpression weekDays;
	public final IntegerExpression level;
	public final IntegerExpression state;
	
	public RequestDetachedCriteria() {
		super(requests.Request.class, requests.RequestCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		personalTrainerId = new StringExpression("personalTrainer.username", this.getDetachedCriteria());
		personalTrainer = new AssociationExpression("personalTrainer", this.getDetachedCriteria());
		clientId = new StringExpression("client.username", this.getDetachedCriteria());
		client = new AssociationExpression("client", this.getDetachedCriteria());
		numberOfWeeks = new IntegerExpression("numberOfWeeks", this.getDetachedCriteria());
		objective = new StringExpression("objective", this.getDetachedCriteria());
		workoutPerWeek = new IntegerExpression("workoutPerWeek", this.getDetachedCriteria());
		weekDays = new StringExpression("weekDays", this.getDetachedCriteria());
		level = new IntegerExpression("level", this.getDetachedCriteria());
		state = new IntegerExpression("state", this.getDetachedCriteria());
	}
	
	public RequestDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, requests.RequestCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		personalTrainerId = new StringExpression("personalTrainer.username", this.getDetachedCriteria());
		personalTrainer = new AssociationExpression("personalTrainer", this.getDetachedCriteria());
		clientId = new StringExpression("client.username", this.getDetachedCriteria());
		client = new AssociationExpression("client", this.getDetachedCriteria());
		numberOfWeeks = new IntegerExpression("numberOfWeeks", this.getDetachedCriteria());
		objective = new StringExpression("objective", this.getDetachedCriteria());
		workoutPerWeek = new IntegerExpression("workoutPerWeek", this.getDetachedCriteria());
		weekDays = new StringExpression("weekDays", this.getDetachedCriteria());
		level = new IntegerExpression("level", this.getDetachedCriteria());
		state = new IntegerExpression("state", this.getDetachedCriteria());
	}
	
	public PersonalTrainerDetachedCriteria createPersonalTrainerCriteria() {
		return new PersonalTrainerDetachedCriteria(createCriteria("personalTrainer"));
	}
	
	public ClientDetachedCriteria createClientCriteria() {
		return new ClientDetachedCriteria(createCriteria("client"));
	}
	
	public Request uniqueRequest(PersistentSession session) {
		return (Request) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Request[] listRequest(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Request[]) list.toArray(new Request[list.size()]);
	}
}

