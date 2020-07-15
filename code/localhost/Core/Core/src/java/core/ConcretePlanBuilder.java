package core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exceptions.PlanDontExistException;
import static java.time.DayOfWeek.SUNDAY;
import java.time.LocalDate;
import java.time.ZoneId;
import static java.time.temporal.TemporalAdjusters.next;
import java.util.Date;
import org.orm.PersistentException;

public class ConcretePlanBuilder implements PlanBuilder {

	private Plan plan;
	private final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

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
         * @throws PersistentException
         * @throws PlanDontExistException
	 */
	@Override
	public void buildWeek(Integer planId, String data) throws PersistentException, PlanDontExistException {
            // Get plan or create a new one
            if(planId == null) {
                this.plan = new Plan();
                this.plan.setDone(false);
                ZoneId defaultZoneId = ZoneId.systemDefault();
                LocalDate localDate = LocalDate.now();
                if(localDate.getDayOfWeek() != SUNDAY)
                    localDate = localDate.with(next(SUNDAY));
                this.plan.setInitialDate(Date.from(localDate.atStartOfDay(defaultZoneId).toInstant()));
            }
            else {
                if ((this.plan = PlanDAO.getPlanByORMID(CoreFacade.getSession(), planId)) == null)
                    throw new PlanDontExistException(planId.toString());
            }
            
            
            //WeekJSON weekJSON = gson.fromJson(data, WeekJSON.class);
            /*Week week = weekJSON.getWeek();
            this.plan.weeks.add(week);
            if(planId == null) {
                this.plan.setCurrentWeek(week);
            }*/
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