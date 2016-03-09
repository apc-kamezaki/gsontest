package com.bookvideo.library.domain;

import com.google.gson.*;

import java.lang.reflect.Type;

public class DurationConverterAdapter implements DomainConverterAdapter {
    @Override
    public void apply(GsonBuilder gsonBuilder) {
        gsonBuilder.registerTypeAdapter(Duration.DayOfWeek.class, new DayOfWeekConverter());
        gsonBuilder.registerTypeAdapter(Duration.TimeSlot.class, new TimeSlotConverter());
    }

    private static class DayOfWeekConverter implements JsonDeserializer<Duration.DayOfWeek> {

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

    private static class TimeSlotConverter implements JsonDeserializer<Duration.TimeSlot> {

        @Override
        public Duration.TimeSlot deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            try {
                return Duration.TimeSlot.valueOf(json.getAsJsonPrimitive().getAsString().toUpperCase());
            } catch (Exception e) {
                return null;
            }
        }
    }
}
