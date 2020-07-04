/**
 * Licensee: joaomarques(Universidade do Minho)
 * License Type: Academic
 */
package ormsamples;

import core.data.*;
import core.data.client.Client;
import core.data.client.ClientDAO;
import core.data.personaltrainer.PersonalTrainer;
import core.data.personaltrainer.PersonalTrainerDAO;
import core.data.plan.Plan;
import core.data.plan.PlanDAO;
import core.data.serie.Serie;
import core.data.serie.SerieDAO;
import core.data.task.Task;
import core.data.task.TaskDAO;
import core.data.week.Week;
import core.data.week.WeekDAO;
import core.data.workout.Workout;
import core.data.workout.WorkoutDAO;
import org.orm.*;
public class CreateDiagramasData {
	public void createTestData() throws PersistentException {
		PersistentTransaction t = DiagramasPersistentManager.instance().getSession().beginTransaction();
		try {
			Plan lcorePlan = PlanDAO.createPlan();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : weeks, modified, currentWeek
			PlanDAO.save(lcorePlan);
			Workout lcoreWorkout = WorkoutDAO.createWorkout();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : tasks, done
			WorkoutDAO.save(lcoreWorkout);
			Task lcoreTask = TaskDAO.createTask();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : series
			TaskDAO.save(lcoreTask);
			Serie lcoreSerie = SerieDAO.createSerie();
			// Initialize the properties of the persistent object here
			SerieDAO.save(lcoreSerie);
			Client lcoreClient = ClientDAO.createClient();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : token, plan, username
			ClientDAO.save(lcoreClient);
			PersonalTrainer lcorePersonalTrainer = PersonalTrainerDAO.createPersonalTrainer();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : plans, token, username
			PersonalTrainerDAO.save(lcorePersonalTrainer);
			Week lcoreWeek = WeekDAO.createWeek();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : workouts, number
			WeekDAO.save(lcoreWeek);
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
		}
		
	}
	
	public static void main(String[] args) {
		try {
			CreateDiagramasData createDiagramasData = new CreateDiagramasData();
			try {
				createDiagramasData.createTestData();
			}
			finally {
				DiagramasPersistentManager.instance().disposePersistentManager();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
