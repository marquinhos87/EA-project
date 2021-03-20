package core;

import java.util.Comparator;

public class WorkoutComparatorByDate implements Comparator<Workout> {
    @Override
    public int compare(Workout w1, Workout w2) {
        return w1.date.compareTo(w2.date);
    }
}
