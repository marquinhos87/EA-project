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

public class UserTokenDetachedCriteria extends AbstractORMDetachedCriteria {
	public final StringExpression username;
	public final StringExpression token;
	
	public UserTokenDetachedCriteria() {
		super(core.UserToken.class, core.UserTokenCriteria.class);
		username = new StringExpression("username", this.getDetachedCriteria());
		token = new StringExpression("token", this.getDetachedCriteria());
	}
	
	public UserTokenDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, core.UserTokenCriteria.class);
		username = new StringExpression("username", this.getDetachedCriteria());
		token = new StringExpression("token", this.getDetachedCriteria());
	}
	
	public UserToken uniqueUserToken(PersistentSession session) {
		return (UserToken) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public UserToken[] listUserToken(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (UserToken[]) list.toArray(new UserToken[list.size()]);
	}
}

