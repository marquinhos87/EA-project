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

public class ClientDetachedCriteria extends AbstractORMDetachedCriteria {
	public final StringExpression username;
	public final IntegerExpression planId;
	public final AssociationExpression plan;
	
	public ClientDetachedCriteria() {
		super(core.Client.class, core.ClientCriteria.class);
		username = new StringExpression("username", this.getDetachedCriteria());
		planId = new IntegerExpression("plan.ID", this.getDetachedCriteria());
		plan = new AssociationExpression("plan", this.getDetachedCriteria());
	}
	
	public ClientDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, core.ClientCriteria.class);
		username = new StringExpression("username", this.getDetachedCriteria());
		planId = new IntegerExpression("plan.ID", this.getDetachedCriteria());
		plan = new AssociationExpression("plan", this.getDetachedCriteria());
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

