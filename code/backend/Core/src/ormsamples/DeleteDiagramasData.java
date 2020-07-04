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
public class DeleteDiagramasData {
	public void deleteTestData() throws PersistentException {
		PersistentTransaction t = DiagramasPersistentManager.instance().getSession().beginTransaction();
		try {
			Plan lcorePlan = PlanDAO.loadPlanByQuery(null, null);
			// Delete the persistent object
			PlanDAO.delete(lcorePlan);
			Workout lcoreWorkout = WorkoutDAO.loadWorkoutByQuery(null, null);
			// Delete the persistent object
			WorkoutDAO.delete(lcoreWorkout);
			Task lcoreTask = TaskDAO.loadTaskByQuery(null, null);
			// Delete the persistent object
			TaskDAO.delete(lcoreTask);
			Serie lcoreSerie = SerieDAO.loadSerieByQuery(null, null);
			// Delete the persistent object
			SerieDAO.delete(lcoreSerie);
			Client lcoreClient = ClientDAO.loadClientByQuery(null, null);
			// Delete the persistent object
			ClientDAO.delete(lcoreClient);
			PersonalTrainer lcorePersonalTrainer = PersonalTrainerDAO.loadPersonalTrainerByQuery(null, null);
			// Delete the persistent object
			PersonalTrainerDAO.delete(lcorePersonalTrainer);
			Week lcoreWeek = WeekDAO.loadWeekByQuery(null, null);
			// Delete the persistent object
			WeekDAO.delete(lcoreWeek);
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
		}
		
	}
	
	public static void main(String[] args) {
		try {
			DeleteDiagramasData deleteDiagramasData = new DeleteDiagramasData();
			try {
				deleteDiagramasData.deleteTestData();
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
