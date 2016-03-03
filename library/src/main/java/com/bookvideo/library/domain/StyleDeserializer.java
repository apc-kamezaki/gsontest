package com.bookvideo.library.domain;

import com.google.gson.*;

import java.lang.reflect.Type;

public class StyleDeserializer implements DomainDeserializer {
    StyleDeserializer() {

    }

    @Override
    public void apply(GsonBuilder gsonBuilder) {
        gsonBuilder.registerTypeAdapter(Style.Type.class, new StyleTypeDeserializer());
        gsonBuilder.registerTypeAdapter(Style.Align.class, new StyleAlignDeserializer());
        gsonBuilder.registerTypeAdapter(Style.LabelStyle.class, new StyleLabelStyleDeserializer());
    }

    private static class StyleTypeDeserializer implements JsonDeserializer<Style.Type> {
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

    private static class StyleAlignDeserializer implements JsonDeserializer<Style.Align> {
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

    private static class StyleLabelStyleDeserializer implements JsonDeserializer<Style.LabelStyle> {
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
}
