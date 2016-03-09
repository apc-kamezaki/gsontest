package com.bookvideo.library.domain;

import com.google.gson.*;

import java.lang.reflect.Type;

public class ContentConverterAdapter implements DomainConverterAdapter {
    @Override
    public void apply(GsonBuilder gsonBuilder) {
        gsonBuilder.registerTypeAdapter(Content.ContentType.class, new ContentTypeConverter());
        gsonBuilder.registerTypeAdapter(Content.ContentItemType.class, new ContentItemTypeConverter());
    }

    private static class ContentTypeConverter implements JsonDeserializer<Content.ContentType> {
        @Override
        public Content.ContentType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            try {
                return Content.ContentType.valueOf(json.getAsJsonPrimitive().getAsString().toUpperCase());
            } catch (Exception e) {
                return null;
            }
        }
    }

    private static class ContentItemTypeConverter implements JsonDeserializer<Content.ContentItemType> {
        @Override
        public Content.ContentItemType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            try {
                return Content.ContentItemType.valueOf(json.getAsJsonPrimitive().getAsString().toUpperCase());
            } catch (Exception e) {
                return null;
            }
        }
    }
}
