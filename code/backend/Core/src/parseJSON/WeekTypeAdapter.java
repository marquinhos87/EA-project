package parseJSON;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import core.Week;

import java.io.IOException;

public class WeekTypeAdapter extends TypeAdapter<Week> {

    @Override
    public void write(JsonWriter jsonWriter, Week week) throws IOException {
        jsonWriter.beginObject();

        jsonWriter.name("ORM_workouts");
        jsonWriter.value(String.valueOf(week.workouts));

        jsonWriter.endObject();
    }

    @Override
    public Week read(JsonReader jsonReader) throws IOException {
        return null;
    }
}
