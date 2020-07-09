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

import org.hibernate.Criteria;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class RequestCriteria extends AbstractORMCriteria {
	public final IntegerExpression ID;
	public final IntegerExpression numberOfWeeks;
	public final StringExpression objective;
	public final IntegerExpression workoutPerWeek;
	public final StringExpression weekDays;
	public final IntegerExpression level;
	public final BooleanExpression accepted;
	
	public RequestCriteria(Criteria criteria) {
		super(criteria);
		ID = new IntegerExpression("ID", this);
		numberOfWeeks = new IntegerExpression("numberOfWeeks", this);
		objective = new StringExpression("objective", this);
		workoutPerWeek = new IntegerExpression("workoutPerWeek", this);
		weekDays = new StringExpression("weekDays", this);
		level = new IntegerExpression("level", this);
		accepted = new BooleanExpression("accepted", this);
	}
	
	public RequestCriteria(PersistentSession session) {
		this(session.createCriteria(Request.class));
	}
	
	public RequestCriteria() throws PersistentException {
		this(DiagramasPersistentManager.instance().getSession());
	}
	
	public Request uniqueRequest() {
		return (Request) super.uniqueResult();
	}
	
	public Request[] listRequest() {
		java.util.List list = super.list();
		return (Request[]) list.toArray(new Request[list.size()]);
	}
}

