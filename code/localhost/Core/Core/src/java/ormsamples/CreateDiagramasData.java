/**
 * Licensee: joaomarques(Universidade do Minho)
 * License Type: Academic
 */
package ormsamples;

import org.orm.*;
public class CreateDiagramasData {
	public void createTestData() throws PersistentException {
		PersistentTransaction t = core.DiagramasPersistentManager.instance().getSession().beginTransaction();
		try {
			core.Plan lcorePlan = core.PlanDAO.createPlan();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : weeks, currentWeek, modified, personalTrainer
			core.PlanDAO.save(lcorePlan);
			core.Workout lcoreWorkout = core.WorkoutDAO.createWorkout();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : tasks, done, week
			core.WorkoutDAO.save(lcoreWorkout);
			core.Task lcoreTask = core.TaskDAO.createTask();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : series
			core.TaskDAO.save(lcoreTask);
			core.Serie lcoreSerie = core.SerieDAO.createSerie();
			// Initialize the properties of the persistent object here
			core.SerieDAO.save(lcoreSerie);
			core.Client lcoreClient = core.ClientDAO.createClient();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : plan, username
			core.ClientDAO.save(lcoreClient);
			core.PersonalTrainer lcorePersonalTrainer = core.PersonalTrainerDAO.createPersonalTrainer();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : plans, username
			core.PersonalTrainerDAO.save(lcorePersonalTrainer);
			core.Week lcoreWeek = core.WeekDAO.createWeek();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : workouts, number
			core.WeekDAO.save(lcoreWeek);
			core.User lcoreUser = core.UserDAO.createUser();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : username
			core.UserDAO.save(lcoreUser);
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
				core.DiagramasPersistentManager.instance().disposePersistentManager();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
