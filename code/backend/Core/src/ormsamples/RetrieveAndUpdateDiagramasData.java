/**
 * Licensee: joaomarques(Universidade do Minho)
 * License Type: Academic
 */
package ormsamples;

import core.data.*;
import core.data.client.Client;
import core.data.client.ClientCriteria;
import core.data.client.ClientDAO;
import core.data.personaltrainer.PersonalTrainer;
import core.data.personaltrainer.PersonalTrainerCriteria;
import core.data.personaltrainer.PersonalTrainerDAO;
import core.data.plan.Plan;
import core.data.plan.PlanCriteria;
import core.data.plan.PlanDAO;
import core.data.serie.Serie;
import core.data.serie.SerieCriteria;
import core.data.serie.SerieDAO;
import core.data.task.Task;
import core.data.task.TaskCriteria;
import core.data.task.TaskDAO;
import core.data.week.Week;
import core.data.week.WeekCriteria;
import core.data.week.WeekDAO;
import core.data.workout.Workout;
import core.data.workout.WorkoutCriteria;
import core.data.workout.WorkoutDAO;
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
