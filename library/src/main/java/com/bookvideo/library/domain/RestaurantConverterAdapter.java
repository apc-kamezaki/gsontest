package com.bookvideo.library.domain;

import com.google.gson.*;

import java.lang.reflect.Type;

public class RestaurantConverterAdapter implements DomainConverterAdapter {
    @Override
    public void apply(GsonBuilder gsonBuilder) {
        gsonBuilder.registerTypeAdapter(Restaurant.LogoStyleType.class, new LogoStyleTypeConverter());
    }

    private static class LogoStyleTypeConverter implements JsonDeserializer<Restaurant.LogoStyleType> {

        @Override
        public Restaurant.LogoStyleType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            try {
                return Restaurant.LogoStyleType.valueOf(json.getAsJsonPrimitive().getAsString().toUpperCase());
            } catch (Exception e) {
                return Restaurant.defaultLogoStyleType;
            }
        }
    }
}
