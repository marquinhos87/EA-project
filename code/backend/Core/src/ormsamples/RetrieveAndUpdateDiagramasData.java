/**
 * Licensee: joaomarques(Universidade do Minho)
 * License Type: Academic
 */
package ormsamples;

import core.data.*;
import org.orm.*;
public class RetrieveAndUpdateDiagramasData {
	public void retrieveAndUpdateTestData() throws PersistentException {
		PersistentTransaction t = DiagramasPersistentManager.instance().getSession().beginTransaction();
		try {
			Plan lcorePlan = PlanDAO.loadPlanByQuery(null, null);
			// Update the properties of the persistent object
			PlanDAO.save(lcorePlan);
			Workout lcoreWorkout = WorkoutDAO.loadWorkoutByQuery(null, null);
			// Update the properties of the persistent object
			WorkoutDAO.save(lcoreWorkout);
			Task lcoreTask = TaskDAO.loadTaskByQuery(null, null);
			// Update the properties of the persistent object
			TaskDAO.save(lcoreTask);
			Serie lcoreSerie = SerieDAO.loadSerieByQuery(null, null);
			// Update the properties of the persistent object
			SerieDAO.save(lcoreSerie);
			Client lcoreClient = ClientDAO.loadClientByQuery(null, null);
			// Update the properties of the persistent object
			ClientDAO.save(lcoreClient);
			PersonalTrainer lcorePersonalTrainer = PersonalTrainerDAO.loadPersonalTrainerByQuery(null, null);
			// Update the properties of the persistent object
			PersonalTrainerDAO.save(lcorePersonalTrainer);
			Week lcoreWeek = WeekDAO.loadWeekByQuery(null, null);
			// Update the properties of the persistent object
			WeekDAO.save(lcoreWeek);
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
		}
		
	}
	
	public void retrieveByCriteria() throws PersistentException {
		System.out.println("Retrieving Plan by PlanCriteria");
		PlanCriteria lcorePlanCriteria = new PlanCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//lcorePlanCriteria.ID.eq();
		System.out.println(lcorePlanCriteria.uniquePlan());
		
		System.out.println("Retrieving Workout by WorkoutCriteria");
		WorkoutCriteria lcoreWorkoutCriteria = new WorkoutCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//lcoreWorkoutCriteria.ID.eq();
		System.out.println(lcoreWorkoutCriteria.uniqueWorkout());
		
		System.out.println("Retrieving Task by TaskCriteria");
		TaskCriteria lcoreTaskCriteria = new TaskCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//lcoreTaskCriteria.ID.eq();
		System.out.println(lcoreTaskCriteria.uniqueTask());
		
		System.out.println("Retrieving Serie by SerieCriteria");
		SerieCriteria lcoreSerieCriteria = new SerieCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//lcoreSerieCriteria.ID.eq();
		System.out.println(lcoreSerieCriteria.uniqueSerie());
		
		System.out.println("Retrieving Client by ClientCriteria");
		ClientCriteria lcoreClientCriteria = new ClientCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//lcoreClientCriteria.username.eq();
		System.out.println(lcoreClientCriteria.uniqueClient());
		
		System.out.println("Retrieving PersonalTrainer by PersonalTrainerCriteria");
		PersonalTrainerCriteria lcorePersonalTrainerCriteria = new PersonalTrainerCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//lcorePersonalTrainerCriteria.username.eq();
		System.out.println(lcorePersonalTrainerCriteria.uniquePersonalTrainer());
		
		System.out.println("Retrieving Week by WeekCriteria");
		WeekCriteria lcoreWeekCriteria = new WeekCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//lcoreWeekCriteria.ID.eq();
		System.out.println(lcoreWeekCriteria.uniqueWeek());
		
	}
	
	
	public static void main(String[] args) {
		try {
			RetrieveAndUpdateDiagramasData retrieveAndUpdateDiagramasData = new RetrieveAndUpdateDiagramasData();
			try {
				retrieveAndUpdateDiagramasData.retrieveAndUpdateTestData();
				//retrieveAndUpdateDiagramasData.retrieveByCriteria();
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
