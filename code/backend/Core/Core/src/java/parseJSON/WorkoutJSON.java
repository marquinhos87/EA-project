package parseJSON;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import core.Workout;

import java.util.Date;

public class WorkoutJSON {
    private String designation;
    private Date date;
    private boolean done;
    private String tasks;

    public Workout getWorkout() {
        Gson gson = new GsonBuilder().create();

        Workout workout = new Workout();
        workout.setDesignation(designation);
        workout.setDate(date);
        workout.setDone(done);

        TaskJSON[] tasks = gson.fromJson(this.tasks, TaskJSON[].class);
        for(TaskJSON task: tasks)
            workout.tasks.add(task.getTask());

        return workout;
    }
}
