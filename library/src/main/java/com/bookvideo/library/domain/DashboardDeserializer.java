package com.bookvideo.library.domain;

import com.google.gson.*;

import java.lang.reflect.Type;

public class DashboardDeserializer implements DomainDeserializer {

    DashboardDeserializer() {

    }

    @Override
    public void apply(GsonBuilder gsonBuilder) {
        gsonBuilder.registerTypeAdapter(Dashboard.Type.class, new DashboardTypeDeserializer());
    }

    private static class DashboardTypeDeserializer implements JsonDeserializer<Dashboard.Type> {
        @Override
        public Dashboard.Type deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            try {
                return Dashboard.Type.valueOf(json.getAsJsonPrimitive().getAsString().toUpperCase());
            } catch (Exception e) {
                return Dashboard.Type.LINE;
            }
        }
    }
}
