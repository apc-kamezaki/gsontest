package com.bookvideo.library.domain;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class ColorValueConverterAdapter implements DomainConverterAdapter {
    @Override
    public void apply(GsonBuilder gsonBuilder) {
        gsonBuilder.registerTypeAdapter(ColorValue.class, new ColorValueConverter());
    }

    private static class ColorValueConverter implements JsonDeserializer<ColorValue> {

        @Override
        public ColorValue deserialize(
                JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {

            try {
                return ColorValue.getColorInstance(json.getAsString());
            } catch (Exception e) {
                return null;
            }
        }
    }
}
