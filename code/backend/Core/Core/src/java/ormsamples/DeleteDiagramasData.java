/**
 * Licensee: joaomarques(Universidade do Minho)
 * License Type: Academic
 */
package ormsamples;

import org.orm.*;
public class DeleteDiagramasData {
	public void deleteTestData() throws PersistentException {
		PersistentTransaction t = core.DiagramasPersistentManager.instance().getSession().beginTransaction();
		try {
			core.Plan lcorePlan = core.PlanDAO.loadPlanByQuery(null, null);
			// Delete the persistent object
			core.PlanDAO.delete(lcorePlan);
			core.Workout lcoreWorkout = core.WorkoutDAO.loadWorkoutByQuery(null, null);
			// Delete the persistent object
			core.WorkoutDAO.delete(lcoreWorkout);
			core.Task lcoreTask = core.TaskDAO.loadTaskByQuery(null, null);
			// Delete the persistent object
			core.TaskDAO.delete(lcoreTask);
			core.Serie lcoreSerie = core.SerieDAO.loadSerieByQuery(null, null);
			// Delete the persistent object
			core.SerieDAO.delete(lcoreSerie);
			core.Client lcoreClient = core.ClientDAO.loadClientByQuery(null, null);
			// Delete the persistent object
			core.ClientDAO.delete(lcoreClient);
			core.PersonalTrainer lcorePersonalTrainer = core.PersonalTrainerDAO.loadPersonalTrainerByQuery(null, null);
			// Delete the persistent object
			core.PersonalTrainerDAO.delete(lcorePersonalTrainer);
			core.Week lcoreWeek = core.WeekDAO.loadWeekByQuery(null, null);
			// Delete the persistent object
			core.WeekDAO.delete(lcoreWeek);
			core.User lcoreUser = core.UserDAO.loadUserByQuery(null, null);
			// Delete the persistent object
			core.UserDAO.delete(lcoreUser);
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
				core.DiagramasPersistentManager.instance().disposePersistentManager();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
