package com.bookvideo.library.domain;

import com.google.gson.*;

import java.lang.reflect.Type;

public class ImageSizeConverterAdapter implements DomainConverterAdapter {
    @Override
    public void apply(GsonBuilder gsonBuilder) {
        gsonBuilder.registerTypeAdapter(ImageSize.class, new ImageSizeDeserializer());
    }

    private static class ImageSizeDeserializer implements JsonDeserializer<ImageSize> {

        @Override
        public ImageSize deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            try {
                return ImageSize.valueOf(json.getAsJsonPrimitive().getAsString().toUpperCase());
            } catch (Exception e) {
                return ImageSize.NONE;
            }
        }
    }
}
