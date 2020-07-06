/**
 * Licensee: joaomarques(Universidade do Minho)
 * License Type: Academic
 */
package ormsamples;

import core.DiagramasPersistentManager;
import core.Client;
import core.ClientDAO;
import core.PersonalTrainer;
import core.PersonalTrainerDAO;
import core.Plan;
import core.PlanDAO;
import core.Serie;
import core.SerieDAO;
import core.Task;
import core.TaskDAO;
import core.Week;
import core.WeekDAO;
import core.Workout;
import core.WorkoutDAO;
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
