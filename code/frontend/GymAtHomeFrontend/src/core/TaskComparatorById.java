package core;

import java.util.Comparator;

public class TaskComparatorById implements Comparator<Task> {
    @Override
    public int compare(Task t1, Task t2) {
        return Integer.compare(t1.id, t2.id);
    }
}
