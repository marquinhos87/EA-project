package core;

import exceptions.PlanDontExistException;
import org.orm.PersistentException;

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
	public void buildWeek(Integer planId, String data) throws PersistentException, PlanDontExistException {
		// Get plan or create a new one
		if(planId == null)
			this.plan = new Plan();
		else {
			if ((this.plan = PlanDAO.getPlanByORMID(CoreFacade.getSession(), planId)) == null)
				throw new PlanDontExistException(planId.toString());
		}

		// TODO
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