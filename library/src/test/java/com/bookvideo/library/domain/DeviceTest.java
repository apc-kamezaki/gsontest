package com.bookvideo.library.domain;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

public class DeviceTest {
    private Gson gson;

    @Before
    public void setUp() throws Exception {
        gson = GsonCreator.getInstance().createGson();
    }

    @Test
    public void shouldDeserializeLocation() throws Exception {
        Device.Location location;
        try (InputStream is = getClass().getResourceAsStream("/device/device_location.json");
             InputStreamReader reader = new InputStreamReader(is)) {
            location = gson.fromJson(reader, Device.Location.class);
        }

        assertThat(location, is(notNullValue()));
        assertThat(location.getType(), is(Device.LocationType.OFFICE));
        assertThat(location.getSettedAt(), is(100));
        assertThat(location.getLocation(), is("location text"));
    }

    @Test
    public void shouldSerializeLocation() throws Exception {
        Device.Location location = new Device.Location(Device.LocationType.RESTAURANT, 200, "MONACO");

        String jsonString = gson.toJson(location, Device.Location.class);

        JsonElement element = gson.fromJson(jsonString, JsonElement.class);
        assertThat(element.isJsonObject(), is(true));
        JsonObject locationJson = element.getAsJsonObject();
        assertThat(locationJson.has("type"), is(true));
        assertThat(locationJson.get("type").getAsInt(), is(location.getType().getLocationValue()));
        assertThat(locationJson.has("settedAt"), is(true));
        assertThat(locationJson.get("settedAt").getAsInt(), is(location.getSettedAt()));
        assertThat(locationJson.has("location"), is(true));
        assertThat(locationJson.get("location").getAsString(), is(location.getLocation()));
    }

    @Test
    public void shouldDeserializeBattery() throws Exception {
        Device.Battery battery;
        try (InputStream is = getClass().getResourceAsStream("/device/device_battery.json");
             InputStreamReader reader = new InputStreamReader(is)) {
            battery = gson.fromJson(reader, Device.Battery.class);
        }

        assertThat(battery, is(notNullValue()));
        assertThat(battery.getLevel(), is(50));
        assertThat(battery.getStatus(), is(Device.BatteryStatus.CHARGING));
        assertThat(battery.getCharge(), is(notNullValue()));
        assertThat(battery.getCharge().getPluggedTimes().size(), is(1));
        assertThat(battery.getCharge().getDispluggedTimes().size(), is(1));
        assertThat(battery.getHealth(), is(Device.BatteryHealth.OVERHEAT));
    }

    @Test
    public void shouldSerializeBattery() throws Exception {
        Device.BatteryCharge charge = new Device.BatteryCharge();
        charge.addPluggedTimes(10);
        charge.addDispluggedTime(1);
        charge.addDispluggedTime(2);
        Device.Battery battery = new Device.Battery();
        battery.setLevel(20);
        battery.setStatus(Device.BatteryStatus.FULL);
        battery.setCharge(charge);
        battery.setHealth(Device.BatteryHealth.GOOD);

        String jsonString = gson.toJson(battery, Device.Battery.class);
        JsonElement element = gson.fromJson(jsonString, JsonElement.class);
        assertThat(element, is(notNullValue()));
        assertThat(element.isJsonObject(), is(true));
        JsonObject batteryJson = element.getAsJsonObject();
        assertThat(batteryJson.has("level"), is(true));
        assertThat(batteryJson.get("level").getAsInt(), is(battery.getLevel()));
        assertThat(batteryJson.has("status"), is(true));
        assertThat(batteryJson.get("status").getAsString(), is(battery.getStatus().name().toLowerCase()));
        assertThat(batteryJson.has("charge"), is(true));
        assertThat(batteryJson.get("charge").isJsonObject(), is(true));
        JsonObject chargeJson = batteryJson.get("charge").getAsJsonObject();
        assertThat(chargeJson.has("pluggedTime"), is(true));
        assertThat(chargeJson.get("pluggedTime").isJsonArray(), is(true));
        assertThat(chargeJson.has("displuggedTime"), is(true));
        assertThat(chargeJson.get("displuggedTime").isJsonArray(), is(true));
        assertThat(batteryJson.has("health"), is(true));
        assertThat(batteryJson.get("health").getAsString(), is(battery.getHealth().name().toLowerCase()));
    }

    @Test
    public void shouldDeserializeApplicationStatus() throws Exception {
        List<Device.ApplicationStatus> statusList;
        try (InputStream is = getClass().getResourceAsStream("/device/device_applicationstatus.json");
             InputStreamReader reader = new InputStreamReader(is)) {
            Type listType = new TypeToken<List<Device.ApplicationStatus>>(){}.getType();
            statusList = gson.fromJson(reader, listType);
        }

        assertThat(statusList, is(notNullValue()));
        assertThat(statusList, hasSize(2));
        {
            // status 1
            Device.ApplicationStatus status = statusList.get(0);
            assertThat(status.getInstalledTime(), is(10));
            assertThat(status.getVersion(), is(11));
            assertThat(status.getOpenedTime(), is(12));
        }
        {
            // status 2
            Device.ApplicationStatus status = statusList.get(1);
            assertThat(status.getInstalledTime(), is(20));
            assertThat(status.getVersion(), is(21));
            assertThat(status.getOpenedTime(), is(0));
        }

    }

    @Test
    public void shouldSerializeApplicationStatus() throws Exception {
        List<Device.ApplicationStatus> statusList =
                Arrays.asList(new Device.ApplicationStatus(11, 10, 12), new Device.ApplicationStatus(21, 20));
        Type listType = new TypeToken<List<Device.ApplicationStatus>>(){}.getType();
        String jsonString = gson.toJson(statusList, listType);

        JsonElement element = gson.fromJson(jsonString, JsonElement.class);
        assertThat(element.isJsonArray(), is(true));
        JsonArray array = element.getAsJsonArray();
        assertThat(array.size(), is(2));
        {
            // status 1
            JsonElement item = array.get(0);
            assertThat(item.isJsonObject(), is(true));
            JsonObject object = item.getAsJsonObject();
            assertThat(object.has("installedTime"), is(true));
            assertThat(object.get("installedTime").getAsInt(), is(10));
            assertThat(object.has("version"), is(true));
            assertThat(object.get("version").getAsInt(), is(11));
            assertThat(object.has("openedTime"), is(true));
            assertThat(object.get("openedTime").getAsInt(), is(12));
        }
        {
            // status 2
            JsonElement item = array.get(1);
            assertThat(item.isJsonObject(), is(true));
            JsonObject object = item.getAsJsonObject();
            assertThat(object.has("installedTime"), is(true));
            assertThat(object.get("installedTime").getAsInt(), is(20));
            assertThat(object.has("version"), is(true));
            assertThat(object.get("version").getAsInt(), is(21));
            assertThat(object.has("openedTime"), is(false));

        }
    }

    @Test
    public void shouldDeserializeLocationType() throws Exception {
        List<Device.LocationType> locationTypeList;
        try (InputStream is = getClass().getResourceAsStream("/device/device_locationtype.json");
             InputStreamReader reader = new InputStreamReader(is)) {
            Type listType = new TypeToken<List<Device.LocationType>>(){}.getType();
            locationTypeList = gson.fromJson(reader, listType);
        }

        assertThat(locationTypeList, is(notNullValue()));
        assertThat(locationTypeList, hasSize(8));
        assertThat(locationTypeList, contains(
                Device.LocationType.RESTAURANT,
                Device.LocationType.OFFICE,
                null,
                Device.LocationType.RESTAURANT,
                Device.LocationType.OFFICE,
                null,
                null,
                null));
    }

    @Test
    public void shouldSerializeLocationType() throws Exception {
        List<Device.LocationType> locationTypeList =
                Arrays.asList(Device.LocationType.OFFICE, Device.LocationType.RESTAURANT);
        Type listType = new TypeToken<List<Device.LocationType>>(){}.getType();
        String jsonString = gson.toJson(locationTypeList, listType);

        JsonElement element = gson.fromJson(jsonString, JsonElement.class);
        assertThat(element.isJsonArray(), is(true));
        JsonArray array = element.getAsJsonArray();
        assertThat(array.size(), is(2));
        for (int i = 0; i < array.size(); i++) {
            JsonElement item = array.get(i);
            assertThat(item.isJsonPrimitive(), is(true));
            assertThat(item.getAsJsonPrimitive().isNumber(), is(true));
            assertThat(item.getAsInt(), is(locationTypeList.get(i).getLocationValue()));
        }
    }

    @Test
    public void shouldDeserializeDeviceStatus() throws Exception {
        List<Device.DeviceStatus> statusList;
        try (InputStream is = getClass().getResourceAsStream("/device/device_status.json");
             InputStreamReader reader = new InputStreamReader(is)) {
            Type listType = new TypeToken<List<Device.DeviceStatus>>(){}.getType();
            statusList = gson.fromJson(reader, listType);
        }

        assertThat(statusList, is(notNullValue()));
        assertThat(statusList, hasSize(3));
        assertThat(statusList, contains(
                Device.DeviceStatus.OFFLINE,
                Device.DeviceStatus.ONLINE,
                null));
    }

    @Test
    public void shouldSerializeDeviceStatus() throws Exception {
        List<Device.DeviceStatus> statusList =
                Arrays.asList(Device.DeviceStatus.OFFLINE, Device.DeviceStatus.ONLINE);

        Type listType = new TypeToken<List<Device.DeviceStatus>>(){}.getType();
        String jsonString = gson.toJson(statusList, listType);

        JsonElement element = gson.fromJson(jsonString, JsonElement.class);
        assertThat(element.isJsonArray(), is(true));
        JsonArray array = element.getAsJsonArray();
        assertThat(array.size(), is(2));
        for (int i = 0; i < array.size(); i++) {
            JsonElement item = array.get(i);
            assertThat(item.isJsonPrimitive(), is(true));
            assertThat(item.getAsJsonPrimitive().isString(), is(true));
            assertThat(item.getAsString(), is(statusList.get(i).name().toLowerCase()));
        }
    }

    @Test
    public void shouldDeserializeBatteryStatus() throws Exception {
        List<Device.BatteryStatus> statusList;
        try (InputStream is = getClass().getResourceAsStream("/device/device_batterystatus.json");
             InputStreamReader reader = new InputStreamReader(is)) {
            Type listType = new TypeToken<List<Device.BatteryStatus>>(){}.getType();
            statusList = gson.fromJson(reader, listType);
        }

        assertThat(statusList, is(notNullValue()));
        assertThat(statusList, hasSize(6));
        assertThat(statusList, contains(
                Device.BatteryStatus.UNKNOWN,
                Device.BatteryStatus.CHARGING,
                Device.BatteryStatus.DISCHARGING,
                Device.BatteryStatus.NOT_CHARGING,
                Device.BatteryStatus.FULL,
                null));

    }

    @Test
    public void shouldSerializeBatteryStatus() throws Exception {
        List<Device.BatteryStatus> statusList =
                Arrays.asList(Device.BatteryStatus.CHARGING, Device.BatteryStatus.FULL);
        Type listType = new TypeToken<List<Device.BatteryStatus>>(){}.getType();
        String jsonString = gson.toJson(statusList, listType);

        JsonElement element = gson.fromJson(jsonString, JsonElement.class);
        assertThat(element.isJsonArray(), is(true));
        JsonArray array = element.getAsJsonArray();
        assertThat(array.size(), is(2));
        for (int i = 0; i < array.size(); i++) {
            JsonElement item = array.get(i);
            assertThat(item.isJsonPrimitive(), is(true));
            assertThat(item.getAsJsonPrimitive().isString(), is(true));
            assertThat(item.getAsString(), is(statusList.get(i).name().toLowerCase()));
        }
    }


    @Test
    public void shouldDeserializeBatteryHealth() throws Exception {
        List<Device.BatteryHealth> batteryHealths;
        try (InputStream is = getClass().getResourceAsStream("/device/device_batteryhealth.json");
             InputStreamReader reader = new InputStreamReader(is)) {
            Type listType = new TypeToken<List<Device.BatteryHealth>>(){}.getType();
            batteryHealths = gson.fromJson(reader, listType);
        }

        assertThat(batteryHealths, is(notNullValue()));
        assertThat(batteryHealths, hasSize(8));
        assertThat(batteryHealths, contains(
                Device.BatteryHealth.UNKNOWN,
                Device.BatteryHealth.GOOD,
                Device.BatteryHealth.OVERHEAT,
                Device.BatteryHealth.DEAD,
                Device.BatteryHealth.OVER_VOLTAGE,
                Device.BatteryHealth.UNSPECIFIED_FAILURE,
                Device.BatteryHealth.COLD,
                null));
    }

    @Test
    public void shouldSerializeBatteryHealth() throws Exception {
        List<Device.BatteryHealth> healths =
                Arrays.asList(Device.BatteryHealth.GOOD, Device.BatteryHealth.DEAD, Device.BatteryHealth.OVERHEAT);

        Type listType = new TypeToken<List<Device.BatteryHealth>>(){}.getType();
        String jsonString = gson.toJson(healths, listType);
        assertThat(jsonString, is(notNullValue()));

        JsonElement element = gson.fromJson(jsonString, JsonElement.class);
        assertThat(element.isJsonArray(), is(true));
        JsonArray array = element.getAsJsonArray();
        assertThat(array.size(), is(3));
        for (int i = 0; i < array.size(); i++) {
            JsonElement item = array.get(i);
            assertThat(item.isJsonPrimitive(), is(true));
            assertThat(item.getAsJsonPrimitive().isString(), is(true));
            assertThat(item.getAsJsonPrimitive().getAsString(), is(healths.get(i).name().toLowerCase()));
        }
    }

    @Test
    public void shouldDeserializeBatteryCharge() throws Exception {
        Device.BatteryCharge charge;
        try (InputStream is = getClass().getResourceAsStream("/device/device_batterycharge.json");
             InputStreamReader reader = new InputStreamReader(is)) {
            charge = gson.fromJson(reader, Device.BatteryCharge.class);
        }

        assertThat(charge, is(notNullValue()));
        assertThat(charge.getPluggedTimes(), hasSize(3));
        assertThat(charge.getPluggedTimes(), contains(1, 2, 3));
        assertThat(charge.getDispluggedTimes(), hasSize(2));
        assertThat(charge.getDispluggedTimes(), contains(10, 20));
    }

    @Test
    public void shouldSerializeBatteryCharge() throws Exception {
        Device.BatteryCharge charge = new Device.BatteryCharge();
        charge.addPluggedTimes(10);
        charge.addPluggedTimes(20);
        charge.addDispluggedTime(100);

        String jsonString = gson.toJson(charge, Device.BatteryCharge.class);

        JsonElement element = gson.fromJson(jsonString, JsonElement.class);
        assertThat(element.isJsonObject(), is(true));
        JsonObject chargeJson = element.getAsJsonObject();
        assertThat(chargeJson.has("pluggedTime"), is(true));
        assertThat(chargeJson.get("pluggedTime").isJsonArray(), is(true));
        JsonArray pluggedArray = chargeJson.getAsJsonArray("pluggedTime");
        assertThat(pluggedArray.size(), is(charge.getPluggedTimes().size()));
        for (int i = 0; i < pluggedArray.size(); i++) {
            JsonElement item = pluggedArray.get(i);
            assertThat(item.isJsonPrimitive(), is(true));
            assertThat(item.getAsJsonPrimitive().isNumber(),is(true));
            assertThat(item.getAsInt(), is(charge.getPluggedTimes().get(i)));
        }
        assertThat(chargeJson.has("displuggedTime"), is(true));
        assertThat(chargeJson.get("displuggedTime").isJsonArray(), is(true));
        JsonArray displuggedArray = chargeJson.getAsJsonArray("displuggedTime");
        assertThat(displuggedArray.size(), is(charge.getDispluggedTimes().size()));
        for (int i = 0; i < displuggedArray.size(); i++) {
            JsonElement item = displuggedArray.get(i);
            assertThat(item.isJsonPrimitive(), is(true));
            assertThat(item.getAsJsonPrimitive().isNumber(),is(true));
            assertThat(item.getAsInt(), is(charge.getDispluggedTimes().get(i)));
        }
    }

}