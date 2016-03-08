package com.bookvideo.library.domain;

import com.google.gson.*;

import java.lang.reflect.Type;

public class AllergenConverterAdapter implements DomainConverterAdapter {
    @Override
    public void apply(GsonBuilder gsonBuilder) {
        gsonBuilder.registerTypeAdapter(Allergen.class, new AllergenDeserializer());
    }

    private static class AllergenDeserializer implements JsonDeserializer<Allergen> {

        @Override
        public Allergen deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            try {
                return Allergen.valueOf(json.getAsJsonPrimitive().getAsString().toUpperCase());
            } catch (Exception e) {
                return null;
            }
        }
    }
}
