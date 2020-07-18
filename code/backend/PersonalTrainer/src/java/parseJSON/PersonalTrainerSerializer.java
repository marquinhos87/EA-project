package parseJSON;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import hrpersonaltrainer.PersonalTrainer;
import hrpersonaltrainer.Utils;

import java.lang.reflect.Type;
import java.util.Date;

public class PersonalTrainerSerializer implements JsonSerializer<PersonalTrainer> {

    @Override
    public JsonElement serialize(PersonalTrainer pt, Type type, JsonSerializationContext context) {
        final JsonObject jsonObj = new JsonObject();
        jsonObj.add("username", context.serialize(pt.getUsername()));
        jsonObj.add("name", context.serialize(pt.getName()));
        jsonObj.add("email", context.serialize(pt.getEmail()));
        jsonObj.add("birthday", context.serialize(pt.getBirthday()));
        jsonObj.add("age", context.serialize(Utils.years(pt.getBirthday(), new Date())));
        jsonObj.add("sex", context.serialize(pt.getSex()));
        jsonObj.add("skill",  context.serialize(pt.getSkill()));
        jsonObj.add("price", context.serialize(pt.getPrice()));
        // so it won't divide by 0
        if (pt.getNumberOfClassifications() == 0) jsonObj.add("classification", context.serialize(0));
        else jsonObj.add("classification", context.serialize(pt.getClassification() / (float)pt.getNumberOfClassifications()));
        jsonObj.add("nClassifications", context.serialize(pt.getNumberOfClassifications()));
        jsonObj.add("nClients", context.serialize(pt.getNumberOfClients()));
        jsonObj.add("nPlans", context.serialize(pt.getNumberOfCreatedPlans()));
        return jsonObj;
    }
}
