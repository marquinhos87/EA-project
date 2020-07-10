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

import org.hibernate.Criteria;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class BiometricDataCriteria extends AbstractORMCriteria {
	public final IntegerExpression ID;
	public final IntegerExpression height;
	public final FloatExpression weight;
	public final IntegerExpression wrist;
	public final IntegerExpression chest;
	public final IntegerExpression tricep;
	public final IntegerExpression waist;
	public final IntegerExpression quadricep;
	public final IntegerExpression twin;
	public final DateExpression date;
	public final FloatExpression BMI;
	
	public BiometricDataCriteria(Criteria criteria) {
		super(criteria);
		ID = new IntegerExpression("ID", this);
		height = new IntegerExpression("height", this);
		weight = new FloatExpression("weight", this);
		wrist = new IntegerExpression("wrist", this);
		chest = new IntegerExpression("chest", this);
		tricep = new IntegerExpression("tricep", this);
		waist = new IntegerExpression("waist", this);
		quadricep = new IntegerExpression("quadricep", this);
		twin = new IntegerExpression("twin", this);
		date = new DateExpression("date", this);
		BMI = new FloatExpression("BMI", this);
	}
	
	public BiometricDataCriteria(PersistentSession session) {
		this(session.createCriteria(BiometricData.class));
	}
	
	public BiometricDataCriteria() throws PersistentException {
		this(DiagramasPersistentManager.instance().getSession());
	}
	
	public BiometricData uniqueBiometricData() {
		return (BiometricData) super.uniqueResult();
	}
	
	public BiometricData[] listBiometricData() {
		java.util.List list = super.list();
		return (BiometricData[]) list.toArray(new BiometricData[list.size()]);
	}
}

