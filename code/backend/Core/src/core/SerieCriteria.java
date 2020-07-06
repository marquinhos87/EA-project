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

public class SerieCriteria extends AbstractORMCriteria {
	public final IntegerExpression ID;
	public final StringExpression description;
	public final StringExpression repetitions;
	public final StringExpression rest;
	
	public SerieCriteria(Criteria criteria) {
		super(criteria);
		ID = new IntegerExpression("ID", this);
		description = new StringExpression("description", this);
		repetitions = new StringExpression("repetitions", this);
		rest = new StringExpression("rest", this);
	}
	
	public SerieCriteria(PersistentSession session) {
		this(session.createCriteria(Serie.class));
	}
	
	public SerieCriteria() throws PersistentException {
		this(DiagramasPersistentManager.instance().getSession());
	}
	
	public Serie uniqueSerie() {
		return (Serie) super.uniqueResult();
	}
	
	public Serie[] listSerie() {
		java.util.List list = super.list();
		return (Serie[]) list.toArray(new Serie[list.size()]);
	}
}

