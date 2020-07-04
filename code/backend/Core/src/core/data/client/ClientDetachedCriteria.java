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
package core.data.client;

import java.util.List;

import core.data.plan.PlanDetachedCriteria;
import org.hibernate.criterion.DetachedCriteria;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class ClientDetachedCriteria extends AbstractORMDetachedCriteria {
	public final StringExpression username;
	public final IntegerExpression planId;
	public final AssociationExpression plan;
	public final StringExpression token;
	
	public ClientDetachedCriteria() {
		super(Client.class, ClientCriteria.class);
		username = new StringExpression("username", this.getDetachedCriteria());
		planId = new IntegerExpression("plan.ID", this.getDetachedCriteria());
		plan = new AssociationExpression("plan", this.getDetachedCriteria());
		token = new StringExpression("token", this.getDetachedCriteria());
	}
	
	public ClientDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, ClientCriteria.class);
		username = new StringExpression("username", this.getDetachedCriteria());
		planId = new IntegerExpression("plan.ID", this.getDetachedCriteria());
		plan = new AssociationExpression("plan", this.getDetachedCriteria());
		token = new StringExpression("token", this.getDetachedCriteria());
	}
	
	public PlanDetachedCriteria createPlanCriteria() {
		return new PlanDetachedCriteria(createCriteria("plan"));
	}
	
	public Client uniqueClient(PersistentSession session) {
		return (Client) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Client[] listClient(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Client[]) list.toArray(new Client[list.size()]);
	}
}

