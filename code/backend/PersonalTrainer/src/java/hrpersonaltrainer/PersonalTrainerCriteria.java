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

import org.hibernate.Criteria;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class PersonalTrainerCriteria extends AbstractORMCriteria {
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
	
	public PersonalTrainerCriteria(Criteria criteria) {
		super(criteria);
		username = new StringExpression("username", this);
		price = new FloatExpression("price", this);
		classification = new FloatExpression("classification", this);
		skill = new StringExpression("skill", this);
		password = new StringExpression("password", this);
		name = new StringExpression("name", this);
		email = new StringExpression("email", this);
		sex = new StringExpression("sex", this);
		birthday = new DateExpression("birthday", this);
		numberOfClassifications = new IntegerExpression("numberOfClassifications", this);
		numberOfClients = new IntegerExpression("numberOfClients", this);
		numberOfCreatedPlans = new IntegerExpression("numberOfCreatedPlans", this);
		certified = new BooleanExpression("certified", this);
		clients = new CollectionExpression("ORM_Clients", this);
	}
	
	public PersonalTrainerCriteria(PersistentSession session) {
		this(session.createCriteria(PersonalTrainer.class));
	}
	
	public PersonalTrainerCriteria() throws PersistentException {
		this(hrpersonaltrainer.DiagramasPersistentManager.instance().getSession());
	}
	
	public hrpersonaltrainer.ClientCriteria createClientsCriteria() {
		return new hrpersonaltrainer.ClientCriteria(createCriteria("ORM_Clients"));
	}
	
	public PersonalTrainer uniquePersonalTrainer() {
		return (PersonalTrainer) super.uniqueResult();
	}
	
	public PersonalTrainer[] listPersonalTrainer() {
		java.util.List list = super.list();
		return (PersonalTrainer[]) list.toArray(new PersonalTrainer[list.size()]);
	}
}

