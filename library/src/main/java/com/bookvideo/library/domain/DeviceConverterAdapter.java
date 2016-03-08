package com.bookvideo.library.domain;

import com.google.gson.*;

import java.lang.reflect.Type;

public class DeviceConverterAdapter implements DomainConverterAdapter {
    @Override
    public void apply(GsonBuilder gsonBuilder) {
        gsonBuilder.registerTypeAdapter(Device.ApplicationStatus.class, new ApplicationStatusConverter());
        gsonBuilder.registerTypeAdapter(Device.LocationType.class, new LocationTypeConverter());
        gsonBuilder.registerTypeAdapter(Device.DeviceStatus.class, new DeviceStatusConverter());
        gsonBuilder.registerTypeAdapter(Device.BatteryHealth.class, new BatteryHealthConverter());
        gsonBuilder.registerTypeAdapter(Device.BatteryStatus.class, new BatteryStatusConverter());
    }

    private static class ApplicationStatusConverter
            implements JsonSerializer<Device.ApplicationStatus> {

        @Override
        public JsonElement serialize(Device.ApplicationStatus src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject object = new JsonObject();
            object.add("installedTime", new JsonPrimitive(src.getInstalledTime()));
            object.add("version", new JsonPrimitive(src.getVersion()));
            if (src.getOpenedTime() > 0) {
                object.add("openedTime", new JsonPrimitive(src.getOpenedTime()));
            }
            return object;
        }
    }

    private static class LocationTypeConverter
            implements JsonSerializer<Device.LocationType>, JsonDeserializer<Device.LocationType> {

        @Override
        public Device.LocationType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            try {
                if (json.isJsonPrimitive()) {
                    if (json.getAsJsonPrimitive().isNumber()) {
                        return Device.LocationType.valueOf(json.getAsInt());
                    } else if (json.getAsJsonPrimitive().isString()){
                        return Device.LocationType.valueOf(Integer.valueOf(json.getAsString()));
                    }
                }
            } catch (Exception ignore) {
                // ignore
            }
            return null;
        }

        @Override
        public JsonElement serialize(Device.LocationType src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.getLocationValue());
        }
    }

    private static class DeviceStatusConverter
            implements JsonSerializer<Device.DeviceStatus>, JsonDeserializer<Device.DeviceStatus> {

        @Override
        public Device.DeviceStatus deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            try {
                return Device.DeviceStatus.valueOf(json.getAsJsonPrimitive().getAsString().toUpperCase());
            } catch (Exception e) {
                return null;
            }
        }

        @Override
        public JsonElement serialize(Device.DeviceStatus src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.name().toLowerCase());
        }
    }

    private static class BatteryHealthConverter
            implements JsonSerializer<Device.BatteryHealth>, JsonDeserializer<Device.BatteryHealth> {

        @Override
        public Device.BatteryHealth deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            try {
                return Device.BatteryHealth.valueOf(json.getAsJsonPrimitive().getAsString().toUpperCase());
            } catch (Exception e) {
                return null;
            }
        }

        @Override
        public JsonElement serialize(Device.BatteryHealth src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.name().toLowerCase());
        }
    }

    private static class BatteryStatusConverter
            implements JsonSerializer<Device.BatteryStatus>, JsonDeserializer<Device.BatteryStatus> {

        @Override
        public Device.BatteryStatus deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            try {
                return Device.BatteryStatus.valueOf(json.getAsJsonPrimitive().getAsString().toUpperCase());
            } catch (Exception e) {
                return null;
            }
        }

        @Override
        public JsonElement serialize(Device.BatteryStatus src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.name().toLowerCase());
        }
    }
}
