package beans;

import core.ConcretePlanBuilder;
import core.PlanDirector;

import javax.ejb.Local;
import javax.ejb.Stateless;

@Local(CoreFacadeBeanLocal.class)
@Stateless(name = "CoreFacadeEJB")
public class CoreFacadeBean implements CoreFacadeBeanLocal{

    private PlanDirector planDirector = new PlanDirector(new ConcretePlanBuilder());

    public CoreFacadeBean() {
    }

    @Override
    public void createClient(String usernameAsJson) {

    }

    @Override
    public void createPersonalTrainer(String usernameAsJson) {

    }

    @Override
    public void updateToken(String usernameAndTokenAsJson) {

    }

    @Override
    public String getPlan(String usernameAndWeekAsJSON) {
        return null;
    }

    @Override
    public void finishWorkout(String usernameAndWorkoutIdAsJSON) {

    }

    @Override
    public void createWeek(String weekAsJson) {

    }
}
