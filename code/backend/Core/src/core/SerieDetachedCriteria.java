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

public class SerieDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression ID;
	public final StringExpression description;
	public final StringExpression repetitions;
	public final StringExpression rest;
	
	public SerieDetachedCriteria() {
		super(core.Serie.class, core.SerieCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		description = new StringExpression("description", this.getDetachedCriteria());
		repetitions = new StringExpression("repetitions", this.getDetachedCriteria());
		rest = new StringExpression("rest", this.getDetachedCriteria());
	}
	
	public SerieDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, core.SerieCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		description = new StringExpression("description", this.getDetachedCriteria());
		repetitions = new StringExpression("repetitions", this.getDetachedCriteria());
		rest = new StringExpression("rest", this.getDetachedCriteria());
	}
	
	public Serie uniqueSerie(PersistentSession session) {
		return (Serie) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Serie[] listSerie(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Serie[]) list.toArray(new Serie[list.size()]);
	}
}

