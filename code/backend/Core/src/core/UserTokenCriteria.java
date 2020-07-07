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

public class UserTokenCriteria extends AbstractORMCriteria {
	public final StringExpression username;
	public final StringExpression token;
	
	public UserTokenCriteria(Criteria criteria) {
		super(criteria);
		username = new StringExpression("username", this);
		token = new StringExpression("token", this);
	}
	
	public UserTokenCriteria(PersistentSession session) {
		this(session.createCriteria(UserToken.class));
	}
	
	public UserTokenCriteria() throws PersistentException {
		this(DiagramasPersistentManager.instance().getSession());
	}
	
	public UserToken uniqueUserToken() {
		return (UserToken) super.uniqueResult();
	}
	
	public UserToken[] listUserToken() {
		java.util.List list = super.list();
		return (UserToken[]) list.toArray(new UserToken[list.size()]);
	}
}

