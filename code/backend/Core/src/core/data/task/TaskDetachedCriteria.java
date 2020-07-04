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
package core.data.task;

import java.util.List;

import core.data.serie.SerieDetachedCriteria;
import org.hibernate.criterion.DetachedCriteria;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class TaskDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression ID;
	public final StringExpression designation;
	public final StringExpression rest;
	public final StringExpression duration;
	public final StringExpression equipment;
	public final CollectionExpression series;
	
	public TaskDetachedCriteria() {
		super(Task.class, TaskCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		designation = new StringExpression("designation", this.getDetachedCriteria());
		rest = new StringExpression("rest", this.getDetachedCriteria());
		duration = new StringExpression("duration", this.getDetachedCriteria());
		equipment = new StringExpression("equipment", this.getDetachedCriteria());
		series = new CollectionExpression("ORM_Series", this.getDetachedCriteria());
	}
	
	public TaskDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, TaskCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		designation = new StringExpression("designation", this.getDetachedCriteria());
		rest = new StringExpression("rest", this.getDetachedCriteria());
		duration = new StringExpression("duration", this.getDetachedCriteria());
		equipment = new StringExpression("equipment", this.getDetachedCriteria());
		series = new CollectionExpression("ORM_Series", this.getDetachedCriteria());
	}
	
	public SerieDetachedCriteria createSeriesCriteria() {
		return new SerieDetachedCriteria(createCriteria("ORM_Series"));
	}
	
	public Task uniqueTask(PersistentSession session) {
		return (Task) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Task[] listTask(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Task[]) list.toArray(new Task[list.size()]);
	}
}

