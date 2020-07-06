package parseJSON;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import hrpersonaltrainer.PersonalTrainer;

import java.io.IOException;

public class PersonalTrainerProfileTypeAdapter extends TypeAdapter<PersonalTrainer> {

    @Override
    public void write(JsonWriter out, PersonalTrainer pt) throws IOException {

        out.beginObject();

        out.name("name");
        out.value(pt.getName());

        out.name("age");
        out.value(pt.getName());

        out.name("name");
        out.value(pt.getName());

        out.name("name");
        out.value(pt.getName());


        out.endObject();
    }

    @Override
    public PersonalTrainer read(JsonReader jsonReader) throws IOException {
        return null;
    }

}