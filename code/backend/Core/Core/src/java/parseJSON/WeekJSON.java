package parseJSON;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import core.Week;

import java.util.Date;

public class WeekJSON {
    private static int number;
    private static Date initialDate;
    private static Date finalDate;
    private static String workouts;

    public static Week getWeek() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

        Week week = new Week();
        week.setNumber(number);
        week.setInitialDate(initialDate);
        week.setFinalDate(finalDate);

        WorkoutJSON[] workoutsJSON = gson.fromJson(workouts, WorkoutJSON[].class);
        for(WorkoutJSON workout: workoutsJSON)
            week.workouts.add(workout.getWorkout());

        return week;
    }
}
