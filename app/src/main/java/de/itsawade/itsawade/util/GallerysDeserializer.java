package de.itsawade.itsawade.util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import de.itsawade.itsawade.model.Gallerys;


/**
 * Created by hendrik on 28.11.15.
 */
public class GallerysDeserializer implements JsonDeserializer<Gallerys> {
    @Override
    public Gallerys deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        Gallerys gallerys = new Gallerys();

        JsonObject jsonObject = json.getAsJsonObject();
        gallerys.setId(jsonObject.get("id").getAsInt());
        gallerys.setContent(jsonObject.get("content").getAsString().replace("<p>", "").replace("</p>",""));
        gallerys.setTitle(jsonObject.get("title").getAsString());



        return gallerys;
    }
}
