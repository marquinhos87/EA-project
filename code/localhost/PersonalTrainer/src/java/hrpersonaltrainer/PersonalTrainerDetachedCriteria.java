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
package hrpersonaltrainer;

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class PersonalTrainerDetachedCriteria extends AbstractORMDetachedCriteria {
	public final StringExpression username;
	public final FloatExpression price;
	public final FloatExpression classification;
	public final StringExpression skill;
	public final StringExpression password;
	public final StringExpression name;
	public final StringExpression email;
	public final StringExpression sex;
	public final DateExpression birthday;
	public final IntegerExpression numberOfClassifications;
	public final IntegerExpression numberOfClients;
	public final IntegerExpression numberOfCreatedPlans;
	public final BooleanExpression certified;
	public final CollectionExpression clients;
	
	public PersonalTrainerDetachedCriteria() {
		super(hrpersonaltrainer.PersonalTrainer.class, hrpersonaltrainer.PersonalTrainerCriteria.class);
		username = new StringExpression("username", this.getDetachedCriteria());
		price = new FloatExpression("price", this.getDetachedCriteria());
		classification = new FloatExpression("classification", this.getDetachedCriteria());
		skill = new StringExpression("skill", this.getDetachedCriteria());
		password = new StringExpression("password", this.getDetachedCriteria());
		name = new StringExpression("name", this.getDetachedCriteria());
		email = new StringExpression("email", this.getDetachedCriteria());
		sex = new StringExpression("sex", this.getDetachedCriteria());
		birthday = new DateExpression("birthday", this.getDetachedCriteria());
		numberOfClassifications = new IntegerExpression("numberOfClassifications", this.getDetachedCriteria());
		numberOfClients = new IntegerExpression("numberOfClients", this.getDetachedCriteria());
		numberOfCreatedPlans = new IntegerExpression("numberOfCreatedPlans", this.getDetachedCriteria());
		certified = new BooleanExpression("certified", this.getDetachedCriteria());
		clients = new CollectionExpression("ORM_Clients", this.getDetachedCriteria());
	}
	
	public PersonalTrainerDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, hrpersonaltrainer.PersonalTrainerCriteria.class);
		username = new StringExpression("username", this.getDetachedCriteria());
		price = new FloatExpression("price", this.getDetachedCriteria());
		classification = new FloatExpression("classification", this.getDetachedCriteria());
		skill = new StringExpression("skill", this.getDetachedCriteria());
		password = new StringExpression("password", this.getDetachedCriteria());
		name = new StringExpression("name", this.getDetachedCriteria());
		email = new StringExpression("email", this.getDetachedCriteria());
		sex = new StringExpression("sex", this.getDetachedCriteria());
		birthday = new DateExpression("birthday", this.getDetachedCriteria());
		numberOfClassifications = new IntegerExpression("numberOfClassifications", this.getDetachedCriteria());
		numberOfClients = new IntegerExpression("numberOfClients", this.getDetachedCriteria());
		numberOfCreatedPlans = new IntegerExpression("numberOfCreatedPlans", this.getDetachedCriteria());
		certified = new BooleanExpression("certified", this.getDetachedCriteria());
		clients = new CollectionExpression("ORM_Clients", this.getDetachedCriteria());
	}
	
	public hrpersonaltrainer.ClientDetachedCriteria createClientsCriteria() {
		return new hrpersonaltrainer.ClientDetachedCriteria(createCriteria("ORM_Clients"));
	}
	
	public PersonalTrainer uniquePersonalTrainer(PersistentSession session) {
		return (PersonalTrainer) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public PersonalTrainer[] listPersonalTrainer(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (PersonalTrainer[]) list.toArray(new PersonalTrainer[list.size()]);
	}
}

