package core;

import core.data.plan.Plan;

public class ConcretePlanBuilder implements PlanBuilder {

	private Plan plan;

	@Override
	public Plan getPlan() {
		return this.plan;
	}

	@Override
	public void reset() {
		// TODO - implement ConcretePlanBuilder.reset
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param data
	 */
	@Override
	public void buildWeek(String data) {
		// TODO - implement ConcretePlanBuilder.buildWeek
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param data
	 */
	@Override
	public void buildWorkout(String data) {
		// TODO - implement ConcretePlanBuilder.buildWeek
		throw new UnsupportedOperationException();
	}

}