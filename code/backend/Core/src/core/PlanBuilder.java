package core;

import core.data.plan.Plan;

public interface PlanBuilder {

	void reset();

	/**
	 * 
	 * @param data
	 */
	void buildWeek(String data);

	/**
	 * 
	 * @param data
	 */
	void buildWorkout(String data);

	Plan getPlan();

}