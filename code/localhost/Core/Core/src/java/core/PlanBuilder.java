package core;

import exceptions.PlanDontExistException;
import org.orm.PersistentException;

public interface PlanBuilder {

	void reset();

	/**
	 *
	 * @param planId
	 * @param data
	 * @throws PersistentException
	 * @throws PlanDontExistException
	 */
	void buildWeek(Integer planId, String data) throws PersistentException, PlanDontExistException;

	/**
	 * 
	 * @param data
	 */
	void buildWorkout(String data);

	Plan getPlan();

}