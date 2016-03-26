package com.bookvideo.library.domain;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class MenuItemConverterAdapter implements DomainConverterAdapter {
    @Override
    public void apply(GsonBuilder gsonBuilder) {
        gsonBuilder.registerTypeAdapter(MenuItem.InfoType.class, new InfoTypeConverter());

    }

    private static class InfoTypeConverter implements JsonDeserializer<MenuItem.InfoType> {

        @Override
        public MenuItem.InfoType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            try {
                String typeStr = json.getAsJsonPrimitive().getAsString();
                MenuItem.InfoType type;
                switch (typeStr) {
                    case "full":
                        type = MenuItem.InfoType.FULL;
                        break;
                    case "in":
                        type = MenuItem.InfoType.INPAGE;
                        break;
                    default:
                        type = MenuItem.InfoType.NONE;
                        break;
                }
                return type;
            } catch (Exception e) {
                return MenuItem.defaultInfoType;
            }
        }
    }

}
