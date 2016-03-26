package com.bookvideo.library.domain;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class StyleConverterAdapter implements DomainConverterAdapter {
    StyleConverterAdapter() {

    }

    @Override
    public void apply(GsonBuilder gsonBuilder) {
        gsonBuilder.registerTypeAdapter(Style.Type.class, new StyleTypeConvereter());
        gsonBuilder.registerTypeAdapter(Style.Align.class, new StyleAlignConverter());
        gsonBuilder.registerTypeAdapter(Style.LabelStyle.class, new StyleLabelStyleConverter());
        gsonBuilder.registerTypeAdapter(Style.LineType.class, new LineTypeConverter());
    }

    private static class StyleTypeConvereter implements JsonDeserializer<Style.Type> {
        @Override
        public Style.Type deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            try {
                return Style.Type.valueOf(json.getAsJsonPrimitive().getAsString().toUpperCase());
            } catch (Exception e) {
                return Style.defaultType;
            }
        }
    }

    private static class StyleAlignConverter implements JsonDeserializer<Style.Align> {
        @Override
        public Style.Align deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            try {
                return Style.Align.valueOf(json.getAsJsonPrimitive().getAsString().toUpperCase());
            } catch (Exception e) {
                return Style.defaultAlign;
            }
        }
    }

    private static class StyleLabelStyleConverter implements JsonDeserializer<Style.LabelStyle> {
        @Override
        public Style.LabelStyle deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            try {
                return Style.LabelStyle.valueOf(json.getAsJsonPrimitive().getAsString().toUpperCase());
            } catch (Exception e) {
                return Style.defaultLabelStyle;
            }
        }
    }

    private static class LineTypeConverter implements  JsonDeserializer<Style.LineType> {
        @Override
        public Style.LineType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            try {
                return Style.LineType.valueOf(json.getAsJsonPrimitive().getAsString().toUpperCase());
            } catch (Exception e) {
                return Style.defaultLineType;
            }
        }
    }
}
