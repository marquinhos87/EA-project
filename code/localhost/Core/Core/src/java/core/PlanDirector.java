package core;

import exceptions.PlanDontExistException;
import org.orm.PersistentException;

public class PlanDirector {

	private PlanBuilder planBuilder;

	/**
	 * 
	 * @param planBuilder
	 */
	public PlanDirector(PlanBuilder planBuilder) {
		this.planBuilder = planBuilder;
	}

	/**
	 * 
	 * @param planBuilder
	 */
	public void setPlanBuilder(PlanBuilder planBuilder) {
		this.planBuilder = planBuilder;
	}

	/**
	 * 
	 * @param data
         * @return 
         * @throws PersistentException
         * @throws PlanDontExistException
	 */
	public Plan buildPlan(Integer planId, String data) throws PersistentException, PlanDontExistException {
		planBuilder.buildWeek(planId,data);
		return planBuilder.getPlan();
	}

}