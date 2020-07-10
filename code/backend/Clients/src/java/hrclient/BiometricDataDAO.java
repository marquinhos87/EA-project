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
package hrclient;

import org.orm.*;
import org.hibernate.Query;
import org.hibernate.LockMode;
import java.util.List;

public class BiometricDataDAO {
	public static BiometricData loadBiometricDataByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return loadBiometricDataByORMID(session, ID);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static BiometricData getBiometricDataByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return getBiometricDataByORMID(session, ID);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static BiometricData loadBiometricDataByORMID(int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return loadBiometricDataByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static BiometricData getBiometricDataByORMID(int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return getBiometricDataByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static BiometricData loadBiometricDataByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (BiometricData) session.load(hrclient.BiometricData.class, new Integer(ID));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static BiometricData getBiometricDataByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (BiometricData) session.get(hrclient.BiometricData.class, new Integer(ID));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static BiometricData loadBiometricDataByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (BiometricData) session.load(hrclient.BiometricData.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static BiometricData getBiometricDataByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (BiometricData) session.get(hrclient.BiometricData.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryBiometricData(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return queryBiometricData(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryBiometricData(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return queryBiometricData(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static BiometricData[] listBiometricDataByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return listBiometricDataByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static BiometricData[] listBiometricDataByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return listBiometricDataByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryBiometricData(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From hrclient.BiometricData as BiometricData");
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
	
	public static List queryBiometricData(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From hrclient.BiometricData as BiometricData");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("BiometricData", lockMode);
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static BiometricData[] listBiometricDataByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryBiometricData(session, condition, orderBy);
			return (BiometricData[]) list.toArray(new BiometricData[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static BiometricData[] listBiometricDataByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryBiometricData(session, condition, orderBy, lockMode);
			return (BiometricData[]) list.toArray(new BiometricData[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static BiometricData loadBiometricDataByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return loadBiometricDataByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static BiometricData loadBiometricDataByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return loadBiometricDataByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static BiometricData loadBiometricDataByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		BiometricData[] biometricDatas = listBiometricDataByQuery(session, condition, orderBy);
		if (biometricDatas != null && biometricDatas.length > 0)
			return biometricDatas[0];
		else
			return null;
	}
	
	public static BiometricData loadBiometricDataByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		BiometricData[] biometricDatas = listBiometricDataByQuery(session, condition, orderBy, lockMode);
		if (biometricDatas != null && biometricDatas.length > 0)
			return biometricDatas[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateBiometricDataByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return iterateBiometricDataByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateBiometricDataByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = DiagramasPersistentManager.instance().getSession();
			return iterateBiometricDataByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateBiometricDataByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From hrclient.BiometricData as BiometricData");
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
	
	public static java.util.Iterator iterateBiometricDataByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From hrclient.BiometricData as BiometricData");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("BiometricData", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static BiometricData createBiometricData() {
		return new hrclient.BiometricData();
	}
	
	public static boolean save(hrclient.BiometricData biometricData) throws PersistentException {
		try {
			DiagramasPersistentManager.instance().saveObject(biometricData);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean delete(hrclient.BiometricData biometricData) throws PersistentException {
		try {
			DiagramasPersistentManager.instance().deleteObject(biometricData);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean refresh(hrclient.BiometricData biometricData) throws PersistentException {
		try {
			DiagramasPersistentManager.instance().getSession().refresh(biometricData);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean evict(hrclient.BiometricData biometricData) throws PersistentException {
		try {
			DiagramasPersistentManager.instance().getSession().evict(biometricData);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static BiometricData loadBiometricDataByCriteria(BiometricDataCriteria biometricDataCriteria) {
		BiometricData[] biometricDatas = listBiometricDataByCriteria(biometricDataCriteria);
		if(biometricDatas == null || biometricDatas.length == 0) {
			return null;
		}
		return biometricDatas[0];
	}
	
	public static BiometricData[] listBiometricDataByCriteria(BiometricDataCriteria biometricDataCriteria) {
		return biometricDataCriteria.listBiometricData();
	}
}
