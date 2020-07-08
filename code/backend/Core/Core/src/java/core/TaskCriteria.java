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

public class TaskCriteria extends AbstractORMCriteria {
	public final IntegerExpression ID;
	public final StringExpression designation;
	public final StringExpression rest;
	public final StringExpression duration;
	public final StringExpression equipment;
	public final CollectionExpression series;
	
	public TaskCriteria(Criteria criteria) {
		super(criteria);
		ID = new IntegerExpression("ID", this);
		designation = new StringExpression("designation", this);
		rest = new StringExpression("rest", this);
		duration = new StringExpression("duration", this);
		equipment = new StringExpression("equipment", this);
		series = new CollectionExpression("ORM_Series", this);
	}
	
	public TaskCriteria(PersistentSession session) {
		this(session.createCriteria(Task.class));
	}
	
	public TaskCriteria() throws PersistentException {
		this(DiagramasPersistentManager.instance().getSession());
	}
	
	public SerieCriteria createSeriesCriteria() {
		return new SerieCriteria(createCriteria("ORM_Series"));
	}
	
	public Task uniqueTask() {
		return (Task) super.uniqueResult();
	}
	
	public Task[] listTask() {
		java.util.List list = super.list();
		return (Task[]) list.toArray(new Task[list.size()]);
	}
}

