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

        jsonWriter.name("workouts");
        jsonWriter.value(String.valueOf(week.workouts));

        jsonWriter.name("number");
        jsonWriter.value(week.getNumber());

        jsonWriter.name("finalDate");
        jsonWriter.value(String.valueOf(week.getFinalDate()));

        jsonWriter.name("initialDate");
        jsonWriter.value(String.valueOf(week.getInitialDate()));

        jsonWriter.endObject();
    }

    @Override
    public Week read(JsonReader jsonReader) throws IOException {
        return null;
    }
}
