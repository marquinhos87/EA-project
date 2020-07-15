/**
 * Licensee: joaomarques(Universidade do Minho)
 * License Type: Academic
 */
package ormsamples;

import org.orm.*;
public class ListDiagramasData {
	private static final int ROW_COUNT = 100;
	
	public void listTestData() throws PersistentException {
		System.out.println("Listing Plan...");
		core.Plan[] corePlans = core.PlanDAO.listPlanByQuery(null, null);
		int length = Math.min(corePlans.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(corePlans[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Workout...");
		core.Workout[] coreWorkouts = core.WorkoutDAO.listWorkoutByQuery(null, null);
		length = Math.min(coreWorkouts.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(coreWorkouts[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Task...");
		core.Task[] coreTasks = core.TaskDAO.listTaskByQuery(null, null);
		length = Math.min(coreTasks.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(coreTasks[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Serie...");
		core.Serie[] coreSeries = core.SerieDAO.listSerieByQuery(null, null);
		length = Math.min(coreSeries.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(coreSeries[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Client...");
		core.Client[] coreClients = core.ClientDAO.listClientByQuery(null, null);
		length = Math.min(coreClients.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(coreClients[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing PersonalTrainer...");
		core.PersonalTrainer[] corePersonalTrainers = core.PersonalTrainerDAO.listPersonalTrainerByQuery(null, null);
		length = Math.min(corePersonalTrainers.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(corePersonalTrainers[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Week...");
		core.Week[] coreWeeks = core.WeekDAO.listWeekByQuery(null, null);
		length = Math.min(coreWeeks.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(coreWeeks[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing User...");
		core.User[] coreUsers = core.UserDAO.listUserByQuery(null, null);
		length = Math.min(coreUsers.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(coreUsers[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
	}
	
	public void listByCriteria() throws PersistentException {
		System.out.println("Listing Plan by Criteria...");
		core.PlanCriteria lcorePlanCriteria = new core.PlanCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//lcorePlanCriteria.ID.eq();
		lcorePlanCriteria.setMaxResults(ROW_COUNT);
		core.Plan[] corePlans = lcorePlanCriteria.listPlan();
		int length =corePlans== null ? 0 : Math.min(corePlans.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(corePlans[i]);
		}
		System.out.println(length + " Plan record(s) retrieved."); 
		
		System.out.println("Listing Workout by Criteria...");
		core.WorkoutCriteria lcoreWorkoutCriteria = new core.WorkoutCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//lcoreWorkoutCriteria.ID.eq();
		lcoreWorkoutCriteria.setMaxResults(ROW_COUNT);
		core.Workout[] coreWorkouts = lcoreWorkoutCriteria.listWorkout();
		length =coreWorkouts== null ? 0 : Math.min(coreWorkouts.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(coreWorkouts[i]);
		}
		System.out.println(length + " Workout record(s) retrieved."); 
		
		System.out.println("Listing Task by Criteria...");
		core.TaskCriteria lcoreTaskCriteria = new core.TaskCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//lcoreTaskCriteria.ID.eq();
		lcoreTaskCriteria.setMaxResults(ROW_COUNT);
		core.Task[] coreTasks = lcoreTaskCriteria.listTask();
		length =coreTasks== null ? 0 : Math.min(coreTasks.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(coreTasks[i]);
		}
		System.out.println(length + " Task record(s) retrieved."); 
		
		System.out.println("Listing Serie by Criteria...");
		core.SerieCriteria lcoreSerieCriteria = new core.SerieCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//lcoreSerieCriteria.ID.eq();
		lcoreSerieCriteria.setMaxResults(ROW_COUNT);
		core.Serie[] coreSeries = lcoreSerieCriteria.listSerie();
		length =coreSeries== null ? 0 : Math.min(coreSeries.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(coreSeries[i]);
		}
		System.out.println(length + " Serie record(s) retrieved."); 
		
		System.out.println("Listing Client by Criteria...");
		core.ClientCriteria lcoreClientCriteria = new core.ClientCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//lcoreClientCriteria.username.eq();
		lcoreClientCriteria.setMaxResults(ROW_COUNT);
		core.Client[] coreClients = lcoreClientCriteria.listClient();
		length =coreClients== null ? 0 : Math.min(coreClients.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(coreClients[i]);
		}
		System.out.println(length + " Client record(s) retrieved."); 
		
		System.out.println("Listing PersonalTrainer by Criteria...");
		core.PersonalTrainerCriteria lcorePersonalTrainerCriteria = new core.PersonalTrainerCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//lcorePersonalTrainerCriteria.username.eq();
		lcorePersonalTrainerCriteria.setMaxResults(ROW_COUNT);
		core.PersonalTrainer[] corePersonalTrainers = lcorePersonalTrainerCriteria.listPersonalTrainer();
		length =corePersonalTrainers== null ? 0 : Math.min(corePersonalTrainers.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(corePersonalTrainers[i]);
		}
		System.out.println(length + " PersonalTrainer record(s) retrieved."); 
		
		System.out.println("Listing Week by Criteria...");
		core.WeekCriteria lcoreWeekCriteria = new core.WeekCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//lcoreWeekCriteria.ID.eq();
		lcoreWeekCriteria.setMaxResults(ROW_COUNT);
		core.Week[] coreWeeks = lcoreWeekCriteria.listWeek();
		length =coreWeeks== null ? 0 : Math.min(coreWeeks.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(coreWeeks[i]);
		}
		System.out.println(length + " Week record(s) retrieved."); 
		
		System.out.println("Listing User by Criteria...");
		core.UserCriteria lcoreUserCriteria = new core.UserCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//lcoreUserCriteria.username.eq();
		lcoreUserCriteria.setMaxResults(ROW_COUNT);
		core.User[] coreUsers = lcoreUserCriteria.listUser();
		length =coreUsers== null ? 0 : Math.min(coreUsers.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(coreUsers[i]);
		}
		System.out.println(length + " User record(s) retrieved."); 
		
	}
	
	public static void main(String[] args) {
		try {
			ListDiagramasData listDiagramasData = new ListDiagramasData();
			try {
				listDiagramasData.listTestData();
				//listDiagramasData.listByCriteria();
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
