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

import org.orm.*;
import org.hibernate.Query;
import org.hibernate.LockMode;
import java.util.List;

public class UserTokenDAO {
	public static UserToken loadUserTokenByORMID(String username) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return loadUserTokenByORMID(session, username);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static UserToken getUserTokenByORMID(String username) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return getUserTokenByORMID(session, username);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static UserToken loadUserTokenByORMID(String username, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return loadUserTokenByORMID(session, username, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static UserToken getUserTokenByORMID(String username, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return getUserTokenByORMID(session, username, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static UserToken loadUserTokenByORMID(PersistentSession session, String username) throws PersistentException {
		try {
			return (UserToken) session.load(core.UserToken.class, username);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static UserToken getUserTokenByORMID(PersistentSession session, String username) throws PersistentException {
		try {
			return (UserToken) session.get(core.UserToken.class, username);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static UserToken loadUserTokenByORMID(PersistentSession session, String username, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (UserToken) session.load(core.UserToken.class, username, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static UserToken getUserTokenByORMID(PersistentSession session, String username, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (UserToken) session.get(core.UserToken.class, username, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryUserToken(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return queryUserToken(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryUserToken(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return queryUserToken(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static UserToken[] listUserTokenByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return listUserTokenByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static UserToken[] listUserTokenByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return listUserTokenByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryUserToken(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From core.UserToken as UserToken");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryUserToken(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From core.UserToken as UserToken");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("UserToken", lockMode);
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static UserToken[] listUserTokenByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryUserToken(session, condition, orderBy);
			return (UserToken[]) list.toArray(new UserToken[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static UserToken[] listUserTokenByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryUserToken(session, condition, orderBy, lockMode);
			return (UserToken[]) list.toArray(new UserToken[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static UserToken loadUserTokenByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return loadUserTokenByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static UserToken loadUserTokenByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return loadUserTokenByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static UserToken loadUserTokenByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		UserToken[] userTokens = listUserTokenByQuery(session, condition, orderBy);
		if (userTokens != null && userTokens.length > 0)
			return userTokens[0];
		else
			return null;
	}
	
	public static UserToken loadUserTokenByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		UserToken[] userTokens = listUserTokenByQuery(session, condition, orderBy, lockMode);
		if (userTokens != null && userTokens.length > 0)
			return userTokens[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateUserTokenByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return iterateUserTokenByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateUserTokenByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return iterateUserTokenByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateUserTokenByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From core.UserToken as UserToken");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateUserTokenByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From core.UserToken as UserToken");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("UserToken", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static UserToken createUserToken() {
		return new core.UserToken();
	}
	
	public static boolean save(core.UserToken userToken) throws PersistentException {
		try {
			DiagramasPersistentManager.instance().saveObject(userToken);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean delete(core.UserToken userToken) throws PersistentException {
		try {
			DiagramasPersistentManager.instance().deleteObject(userToken);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean refresh(core.UserToken userToken) throws PersistentException {
		try {
			DiagramasPersistentManager.instance().getSession().refresh(userToken);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean evict(core.UserToken userToken) throws PersistentException {
		try {
			DiagramasPersistentManager.instance().getSession().evict(userToken);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static UserToken loadUserTokenByCriteria(UserTokenCriteria userTokenCriteria) {
		UserToken[] userTokens = listUserTokenByCriteria(userTokenCriteria);
		if(userTokens == null || userTokens.length == 0) {
			return null;
		}
		return userTokens[0];
	}
	
	public static UserToken[] listUserTokenByCriteria(UserTokenCriteria userTokenCriteria) {
		return userTokenCriteria.listUserToken();
	}
}
