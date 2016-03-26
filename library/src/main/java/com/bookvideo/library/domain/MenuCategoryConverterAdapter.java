package com.bookvideo.library.domain;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class MenuCategoryConverterAdapter implements DomainConverterAdapter {
    @Override
    public void apply(GsonBuilder gsonBuilder) {
        gsonBuilder.registerTypeAdapter(MenuCategory.CategoryType.class, new CategoryTypeConverter());
    }

    private static class CategoryTypeConverter implements JsonDeserializer<MenuCategory.CategoryType> {
        @Override
        public MenuCategory.CategoryType deserialize(
                JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            try {
                return MenuCategory.CategoryType.valueOf(json.getAsJsonPrimitive().getAsString().toUpperCase());
            } catch (Exception e) {
                return null;
            }
        }
    }
}
