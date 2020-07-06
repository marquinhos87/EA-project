/**
 * Licensee: joaomarques(Universidade do Minho)
 * License Type: Academic
 */
package ormsamples;

import core.DiagramasPersistentManager;
import core.Client;
import core.ClientCriteria;
import core.ClientDAO;
import core.PersonalTrainer;
import core.PersonalTrainerCriteria;
import core.PersonalTrainerDAO;
import core.Plan;
import core.PlanCriteria;
import core.PlanDAO;
import core.Serie;
import core.SerieCriteria;
import core.SerieDAO;
import core.Task;
import core.TaskCriteria;
import core.TaskDAO;
import core.Week;
import core.WeekCriteria;
import core.WeekDAO;
import core.Workout;
import core.WorkoutCriteria;
import core.WorkoutDAO;
import org.orm.*;
public class ListDiagramasData {
	private static final int ROW_COUNT = 100;
	
	public void listTestData() throws PersistentException {
		System.out.println("Listing Plan...");
		Plan[] corePlans = PlanDAO.listPlanByQuery(null, null);
		int length = Math.min(corePlans.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(corePlans[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Workout...");
		Workout[] coreWorkouts = WorkoutDAO.listWorkoutByQuery(null, null);
		length = Math.min(coreWorkouts.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(coreWorkouts[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Task...");
		Task[] coreTasks = TaskDAO.listTaskByQuery(null, null);
		length = Math.min(coreTasks.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(coreTasks[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Serie...");
		Serie[] coreSeries = SerieDAO.listSerieByQuery(null, null);
		length = Math.min(coreSeries.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(coreSeries[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Client...");
		Client[] coreClients = ClientDAO.listClientByQuery(null, null);
		length = Math.min(coreClients.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(coreClients[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing PersonalTrainer...");
		PersonalTrainer[] corePersonalTrainers = PersonalTrainerDAO.listPersonalTrainerByQuery(null, null);
		length = Math.min(corePersonalTrainers.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(corePersonalTrainers[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Week...");
		Week[] coreWeeks = WeekDAO.listWeekByQuery(null, null);
		length = Math.min(coreWeeks.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(coreWeeks[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
	}
	
	public void listByCriteria() throws PersistentException {
		System.out.println("Listing Plan by Criteria...");
		PlanCriteria lcorePlanCriteria = new PlanCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//lcorePlanCriteria.ID.eq();
		lcorePlanCriteria.setMaxResults(ROW_COUNT);
		Plan[] corePlans = lcorePlanCriteria.listPlan();
		int length =corePlans== null ? 0 : Math.min(corePlans.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(corePlans[i]);
		}
		System.out.println(length + " Plan record(s) retrieved."); 
		
		System.out.println("Listing Workout by Criteria...");
		WorkoutCriteria lcoreWorkoutCriteria = new WorkoutCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//lcoreWorkoutCriteria.ID.eq();
		lcoreWorkoutCriteria.setMaxResults(ROW_COUNT);
		Workout[] coreWorkouts = lcoreWorkoutCriteria.listWorkout();
		length =coreWorkouts== null ? 0 : Math.min(coreWorkouts.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(coreWorkouts[i]);
		}
		System.out.println(length + " Workout record(s) retrieved."); 
		
		System.out.println("Listing Task by Criteria...");
		TaskCriteria lcoreTaskCriteria = new TaskCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//lcoreTaskCriteria.ID.eq();
		lcoreTaskCriteria.setMaxResults(ROW_COUNT);
		Task[] coreTasks = lcoreTaskCriteria.listTask();
		length =coreTasks== null ? 0 : Math.min(coreTasks.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(coreTasks[i]);
		}
		System.out.println(length + " Task record(s) retrieved."); 
		
		System.out.println("Listing Serie by Criteria...");
		SerieCriteria lcoreSerieCriteria = new SerieCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//lcoreSerieCriteria.ID.eq();
		lcoreSerieCriteria.setMaxResults(ROW_COUNT);
		Serie[] coreSeries = lcoreSerieCriteria.listSerie();
		length =coreSeries== null ? 0 : Math.min(coreSeries.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(coreSeries[i]);
		}
		System.out.println(length + " Serie record(s) retrieved."); 
		
		System.out.println("Listing Client by Criteria...");
		ClientCriteria lcoreClientCriteria = new ClientCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//lcoreClientCriteria.username.eq();
		lcoreClientCriteria.setMaxResults(ROW_COUNT);
		Client[] coreClients = lcoreClientCriteria.listClient();
		length =coreClients== null ? 0 : Math.min(coreClients.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(coreClients[i]);
		}
		System.out.println(length + " Client record(s) retrieved."); 
		
		System.out.println("Listing PersonalTrainer by Criteria...");
		PersonalTrainerCriteria lcorePersonalTrainerCriteria = new PersonalTrainerCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//lcorePersonalTrainerCriteria.username.eq();
		lcorePersonalTrainerCriteria.setMaxResults(ROW_COUNT);
		PersonalTrainer[] corePersonalTrainers = lcorePersonalTrainerCriteria.listPersonalTrainer();
		length =corePersonalTrainers== null ? 0 : Math.min(corePersonalTrainers.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(corePersonalTrainers[i]);
		}
		System.out.println(length + " PersonalTrainer record(s) retrieved."); 
		
		System.out.println("Listing Week by Criteria...");
		WeekCriteria lcoreWeekCriteria = new WeekCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//lcoreWeekCriteria.ID.eq();
		lcoreWeekCriteria.setMaxResults(ROW_COUNT);
		Week[] coreWeeks = lcoreWeekCriteria.listWeek();
		length =coreWeeks== null ? 0 : Math.min(coreWeeks.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(coreWeeks[i]);
		}
		System.out.println(length + " Week record(s) retrieved."); 
		
	}
	
	public static void main(String[] args) {
		try {
			ListDiagramasData listDiagramasData = new ListDiagramasData();
			try {
				listDiagramasData.listTestData();
				//listDiagramasData.listByCriteria();
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
