/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: Ricardo Petronilho(Universidade do Minho)
 * License Type: Academic
 */
package hrpersonaltrainer;

import org.orm.*;
import org.hibernate.Query;
import org.hibernate.LockMode;
import java.util.List;

public class ClientDAO {
	public static Client loadClientByORMID(String username) throws PersistentException {
		try {
			PersistentSession session = hrpersonaltrainer.DiagramasPersistentManager.instance().getSession();
			return loadClientByORMID(session, username);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Client getClientByORMID(String username) throws PersistentException {
		try {
			PersistentSession session = hrpersonaltrainer.DiagramasPersistentManager.instance().getSession();
			return getClientByORMID(session, username);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Client loadClientByORMID(String username, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = hrpersonaltrainer.DiagramasPersistentManager.instance().getSession();
			return loadClientByORMID(session, username, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Client getClientByORMID(String username, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = hrpersonaltrainer.DiagramasPersistentManager.instance().getSession();
			return getClientByORMID(session, username, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Client loadClientByORMID(PersistentSession session, String username) throws PersistentException {
		try {
			return (Client) session.load(hrpersonaltrainer.Client.class, username);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Client getClientByORMID(PersistentSession session, String username) throws PersistentException {
		try {
			return (Client) session.get(hrpersonaltrainer.Client.class, username);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Client loadClientByORMID(PersistentSession session, String username, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Client) session.load(hrpersonaltrainer.Client.class, username, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Client getClientByORMID(PersistentSession session, String username, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Client) session.get(hrpersonaltrainer.Client.class, username, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryClient(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = hrpersonaltrainer.DiagramasPersistentManager.instance().getSession();
			return queryClient(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryClient(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = hrpersonaltrainer.DiagramasPersistentManager.instance().getSession();
			return queryClient(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Client[] listClientByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = hrpersonaltrainer.DiagramasPersistentManager.instance().getSession();
			return listClientByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Client[] listClientByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = hrpersonaltrainer.DiagramasPersistentManager.instance().getSession();
			return listClientByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryClient(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From hrpersonaltrainer.Client as Client");
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
	
	public static List queryClient(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From hrpersonaltrainer.Client as Client");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Client", lockMode);
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Client[] listClientByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryClient(session, condition, orderBy);
			return (Client[]) list.toArray(new Client[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Client[] listClientByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryClient(session, condition, orderBy, lockMode);
			return (Client[]) list.toArray(new Client[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Client loadClientByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = hrpersonaltrainer.DiagramasPersistentManager.instance().getSession();
			return loadClientByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Client loadClientByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = hrpersonaltrainer.DiagramasPersistentManager.instance().getSession();
			return loadClientByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Client loadClientByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		Client[] clients = listClientByQuery(session, condition, orderBy);
		if (clients != null && clients.length > 0)
			return clients[0];
		else
			return null;
	}
	
	public static Client loadClientByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		Client[] clients = listClientByQuery(session, condition, orderBy, lockMode);
		if (clients != null && clients.length > 0)
			return clients[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateClientByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = hrpersonaltrainer.DiagramasPersistentManager.instance().getSession();
			return iterateClientByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateClientByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = hrpersonaltrainer.DiagramasPersistentManager.instance().getSession();
			return iterateClientByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateClientByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From hrpersonaltrainer.Client as Client");
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
	
	public static java.util.Iterator iterateClientByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From hrpersonaltrainer.Client as Client");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Client", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Client createClient() {
		return new hrpersonaltrainer.Client();
	}
	
	public static boolean save(hrpersonaltrainer.Client client) throws PersistentException {
		try {
			hrpersonaltrainer.DiagramasPersistentManager.instance().saveObject(client);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean delete(hrpersonaltrainer.Client client) throws PersistentException {
		try {
			hrpersonaltrainer.DiagramasPersistentManager.instance().deleteObject(client);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean refresh(hrpersonaltrainer.Client client) throws PersistentException {
		try {
			hrpersonaltrainer.DiagramasPersistentManager.instance().getSession().refresh(client);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean evict(hrpersonaltrainer.Client client) throws PersistentException {
		try {
			hrpersonaltrainer.DiagramasPersistentManager.instance().getSession().evict(client);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Client loadClientByCriteria(ClientCriteria clientCriteria) {
		Client[] clients = listClientByCriteria(clientCriteria);
		if(clients == null || clients.length == 0) {
			return null;
		}
		return clients[0];
	}
	
	public static Client[] listClientByCriteria(ClientCriteria clientCriteria) {
		return clientCriteria.listClient();
	}
}
