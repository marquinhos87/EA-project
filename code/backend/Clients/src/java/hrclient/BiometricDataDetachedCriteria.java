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

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class BiometricDataDetachedCriteria extends AbstractORMDetachedCriteria {
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
	
	public BiometricDataDetachedCriteria() {
		super(hrclient.BiometricData.class, hrclient.BiometricDataCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		height = new IntegerExpression("height", this.getDetachedCriteria());
		weight = new FloatExpression("weight", this.getDetachedCriteria());
		wrist = new IntegerExpression("wrist", this.getDetachedCriteria());
		chest = new IntegerExpression("chest", this.getDetachedCriteria());
		tricep = new IntegerExpression("tricep", this.getDetachedCriteria());
		waist = new IntegerExpression("waist", this.getDetachedCriteria());
		quadricep = new IntegerExpression("quadricep", this.getDetachedCriteria());
		twin = new IntegerExpression("twin", this.getDetachedCriteria());
		date = new DateExpression("date", this.getDetachedCriteria());
		BMI = new FloatExpression("BMI", this.getDetachedCriteria());
	}
	
	public BiometricDataDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, hrclient.BiometricDataCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		height = new IntegerExpression("height", this.getDetachedCriteria());
		weight = new FloatExpression("weight", this.getDetachedCriteria());
		wrist = new IntegerExpression("wrist", this.getDetachedCriteria());
		chest = new IntegerExpression("chest", this.getDetachedCriteria());
		tricep = new IntegerExpression("tricep", this.getDetachedCriteria());
		waist = new IntegerExpression("waist", this.getDetachedCriteria());
		quadricep = new IntegerExpression("quadricep", this.getDetachedCriteria());
		twin = new IntegerExpression("twin", this.getDetachedCriteria());
		date = new DateExpression("date", this.getDetachedCriteria());
		BMI = new FloatExpression("BMI", this.getDetachedCriteria());
	}
	
	public BiometricData uniqueBiometricData(PersistentSession session) {
		return (BiometricData) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public BiometricData[] listBiometricData(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (BiometricData[]) list.toArray(new BiometricData[list.size()]);
	}
}

