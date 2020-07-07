package parseJSON;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import core.Week;
import core.Workout;

import java.util.Date;

public class WeekJSON {
    private int number;
    private Date initialDate;
    private Date finalDate;
    private String workouts;

    public Week getWeek() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

        Week week = new Week();
        week.setNumber(number);
        week.setInitialDate(initialDate);
        week.setFinalDate(finalDate);

        WorkoutJSON[] workouts = gson.fromJson(this.workouts, WorkoutJSON[].class);
        for(WorkoutJSON workout: workouts)
            week.workouts.add(workout.getWorkout());

        return week;
    }
}
