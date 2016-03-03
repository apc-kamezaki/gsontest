package com.bookvideo.library.domain;

import com.google.gson.*;

import java.lang.reflect.Type;

public class LinkConverter implements DomainConverter {
    LinkConverter() {

    }

    @Override
    public void apply(GsonBuilder gsonBuilder) {
        gsonBuilder.registerTypeAdapter(Link.Type.class, new LinkTypeDeserializer());
    }

    private static class LinkTypeDeserializer implements JsonDeserializer<Link.Type> {
        @Override
        public Link.Type deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            try {
                return Link.Type.valueOf(json.getAsJsonPrimitive().getAsString().toUpperCase());
            } catch (Exception e) {
                return Link.defaultType;
            }
        }
    }
}
