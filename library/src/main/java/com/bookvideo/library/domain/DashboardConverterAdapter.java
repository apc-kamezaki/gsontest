package com.bookvideo.library.domain;

import com.google.gson.*;

import java.lang.reflect.Type;

public class DashboardConverterAdapter implements DomainConverterAdapter {

    DashboardConverterAdapter() {

    }

    @Override
    public void apply(GsonBuilder gsonBuilder) {
        gsonBuilder.registerTypeAdapter(Dashboard.Type.class, new DashboardTypeConverter());
    }

    private static class DashboardTypeConverter implements JsonDeserializer<Dashboard.Type> {
        @Override
        public Dashboard.Type deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            try {
                return Dashboard.Type.valueOf(json.getAsJsonPrimitive().getAsString().toUpperCase());
            } catch (Exception e) {
                return null;
            }
        }
    }
}
