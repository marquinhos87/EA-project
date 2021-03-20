/**
 * Licensee: joaomarques(Universidade do Minho)
 * License Type: Academic
 */
package ormsamples;

import org.orm.*;
public class RetrieveAndUpdateDiagramasData {
	public void retrieveAndUpdateTestData() throws PersistentException {
		PersistentTransaction t = core.DiagramasPersistentManager.instance().getSession().beginTransaction();
		try {
			core.Plan lcorePlan = core.PlanDAO.loadPlanByQuery(null, null);
			// Update the properties of the persistent object
			core.PlanDAO.save(lcorePlan);
			core.Workout lcoreWorkout = core.WorkoutDAO.loadWorkoutByQuery(null, null);
			// Update the properties of the persistent object
			core.WorkoutDAO.save(lcoreWorkout);
			core.Task lcoreTask = core.TaskDAO.loadTaskByQuery(null, null);
			// Update the properties of the persistent object
			core.TaskDAO.save(lcoreTask);
			core.Serie lcoreSerie = core.SerieDAO.loadSerieByQuery(null, null);
			// Update the properties of the persistent object
			core.SerieDAO.save(lcoreSerie);
			core.Client lcoreClient = core.ClientDAO.loadClientByQuery(null, null);
			// Update the properties of the persistent object
			core.ClientDAO.save(lcoreClient);
			core.PersonalTrainer lcorePersonalTrainer = core.PersonalTrainerDAO.loadPersonalTrainerByQuery(null, null);
			// Update the properties of the persistent object
			core.PersonalTrainerDAO.save(lcorePersonalTrainer);
			core.Week lcoreWeek = core.WeekDAO.loadWeekByQuery(null, null);
			// Update the properties of the persistent object
			core.WeekDAO.save(lcoreWeek);
			core.User lcoreUser = core.UserDAO.loadUserByQuery(null, null);
			// Update the properties of the persistent object
			core.UserDAO.save(lcoreUser);
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
		}
		
	}
	
	public void retrieveByCriteria() throws PersistentException {
		System.out.println("Retrieving Plan by PlanCriteria");
		core.PlanCriteria lcorePlanCriteria = new core.PlanCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//lcorePlanCriteria.ID.eq();
		System.out.println(lcorePlanCriteria.uniquePlan());
		
		System.out.println("Retrieving Workout by WorkoutCriteria");
		core.WorkoutCriteria lcoreWorkoutCriteria = new core.WorkoutCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//lcoreWorkoutCriteria.ID.eq();
		System.out.println(lcoreWorkoutCriteria.uniqueWorkout());
		
		System.out.println("Retrieving Task by TaskCriteria");
		core.TaskCriteria lcoreTaskCriteria = new core.TaskCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//lcoreTaskCriteria.ID.eq();
		System.out.println(lcoreTaskCriteria.uniqueTask());
		
		System.out.println("Retrieving Serie by SerieCriteria");
		core.SerieCriteria lcoreSerieCriteria = new core.SerieCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//lcoreSerieCriteria.ID.eq();
		System.out.println(lcoreSerieCriteria.uniqueSerie());
		
		System.out.println("Retrieving Client by ClientCriteria");
		core.ClientCriteria lcoreClientCriteria = new core.ClientCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//lcoreClientCriteria.username.eq();
		System.out.println(lcoreClientCriteria.uniqueClient());
		
		System.out.println("Retrieving PersonalTrainer by PersonalTrainerCriteria");
		core.PersonalTrainerCriteria lcorePersonalTrainerCriteria = new core.PersonalTrainerCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//lcorePersonalTrainerCriteria.username.eq();
		System.out.println(lcorePersonalTrainerCriteria.uniquePersonalTrainer());
		
		System.out.println("Retrieving Week by WeekCriteria");
		core.WeekCriteria lcoreWeekCriteria = new core.WeekCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//lcoreWeekCriteria.ID.eq();
		System.out.println(lcoreWeekCriteria.uniqueWeek());
		
		System.out.println("Retrieving User by UserCriteria");
		core.UserCriteria lcoreUserCriteria = new core.UserCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//lcoreUserCriteria.username.eq();
		System.out.println(lcoreUserCriteria.uniqueUser());
		
	}
	
	
	public static void main(String[] args) {
		try {
			RetrieveAndUpdateDiagramasData retrieveAndUpdateDiagramasData = new RetrieveAndUpdateDiagramasData();
			try {
				retrieveAndUpdateDiagramasData.retrieveAndUpdateTestData();
				//retrieveAndUpdateDiagramasData.retrieveByCriteria();
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
