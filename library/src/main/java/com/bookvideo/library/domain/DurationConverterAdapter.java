package com.bookvideo.library.domain;

import com.google.gson.*;

import java.lang.reflect.Type;

public class DurationConverterAdapter implements DomainConverterAdapter {
    @Override
    public void apply(GsonBuilder gsonBuilder) {
        gsonBuilder.registerTypeAdapter(Duration.DayOfWeek.class, new DayOfWeekDeserializer());
    }

    private static class DayOfWeekDeserializer implements JsonDeserializer<Duration.DayOfWeek> {

        @Override
        public Duration.DayOfWeek deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            try {
                return Duration.DayOfWeek.valueByKey(json.getAsJsonPrimitive().getAsString());
            } catch (Exception e) {
                return null;
            }
        }
    }
}
