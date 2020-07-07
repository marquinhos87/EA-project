package parseJSON;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import core.Serie;
import core.Task;

public class TaskJSON {
    private String designation;
    private String rest;
    private String duration;
    private String equipment;
    private String series;

    public Task getTask() {
        Gson gson = new GsonBuilder().create();

        Task task = new Task();
        task.setDesignation(designation);
        task.setRest(rest);
        task.setDuration(duration);
        task.setEquipment(equipment);

        Serie[] series = gson.fromJson(this.series, Serie[].class);
        for(Serie serie: series)
            task.series.add(serie);

        return task;
    }
}
