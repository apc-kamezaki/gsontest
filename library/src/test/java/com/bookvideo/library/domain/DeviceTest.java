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

import static org.assertj.core.api.Assertions.assertThat;

public class DeviceTest {
    private Gson gson;

    @Before
    public void setUp() throws Exception {
        gson = GsonCreator.getInstance().createGson();
    }

    @Test
    public void shouldDeserializeJson() throws Exception {
        Device device;
        try (InputStream is = getClass().getResourceAsStream("/device/device_simple.json");
             InputStreamReader reader = new InputStreamReader(is)) {
            device = gson.fromJson(reader, Device.class);
        }

        assertThat(device).isNotNull();
        assertThat(device.getLocations()).isNotNull().hasSize(2);
        assertThat(device.getLastRestaurant()).isEqualTo("last restaurant");
        assertThat(device.getStatus()).isEqualTo(Device.DeviceStatus.ONLINE);
        assertThat(device.getBattery()).isNotNull();
        assertThat(device.getBattery().getLevel()).isEqualTo(20);
        assertThat(device.getAppStatusList()).isNotNull().hasSize(2);
        assertThat(device.getUpdaterStatusList()).isNotNull().hasSize(1);
        assertThat(device.getDataStatusList()).isNotNull().hasSize(3);
    }

    @Test
    public void shouldSerializeJson() throws Exception {
        Device device = new Device();
        device.addToLocations(
                new Device.Location(Device.LocationType.RESTAURANT, 1, "new location 1"));
        device.addToLocations(
                new Device.Location(Device.LocationType.OFFICE, 2, "new location 2"));
        device.setLastRestaurant("last restaurant");
        device.setStatus(Device.DeviceStatus.OFFLINE);
        Device.Battery battery = new Device.Battery();
        battery.setLevel(100);
        battery.setStatus(Device.BatteryStatus.NOT_CHARGING);
        Device.BatteryCharge charge = new Device.BatteryCharge();
        charge.addPluggedTimes(100);
        charge.addDispluggedTime(101);
        battery.setCharge(charge);
        device.setBattery(battery);
        device.addAppStatus(new Device.ApplicationStatus(1, 10, 100));
        device.addUpdaterStatusList(new Device.ApplicationStatus(2, 20));
        device.addUpdaterStatusList(new Device.ApplicationStatus(3, 30));
        device.addDataStatusList(new Device.ApplicationStatus(4, 40));
        device.addDataStatusList(new Device.ApplicationStatus(5, 50));
        device.addDataStatusList(new Device.ApplicationStatus(6, 60));
        device.setLocation(1000);

        String jsonString = gson.toJson(device, Device.class);
        JsonElement element = gson.fromJson(jsonString, JsonElement.class);
        assertThat(element.isJsonObject()).isTrue();
        JsonObject deviceJson = element.getAsJsonObject();
        assertThat(deviceJson.has("locations")).isTrue();
        assertThat(deviceJson.get("locations").isJsonArray()).isTrue();
        JsonArray locationsArray = deviceJson.getAsJsonArray("locations");
        assertThat(locationsArray.size()).isEqualTo(device.getLocations().size());
        assertThat(deviceJson.has("lastResto")).isTrue();
        assertThat(deviceJson.get("lastResto").getAsString())
                .isEqualTo(device.getLastRestaurant());
        assertThat(deviceJson.has("status")).isTrue();
        assertThat(deviceJson.get("status").getAsString())
                .isEqualTo(device.getStatus().name().toLowerCase());
        assertThat(deviceJson.has("battery")).isTrue();
        assertThat(deviceJson.get("battery").isJsonObject()).isTrue();
        assertThat(deviceJson.has("bvUpdate")).isTrue();
        assertThat(deviceJson.get("bvUpdate").isJsonArray()).isTrue();
        assertThat(deviceJson.getAsJsonArray("bvUpdate").size())
                .isEqualTo(device.getAppStatusList().size());
        assertThat(deviceJson.has("updater")).isTrue();
        assertThat(deviceJson.get("updater").isJsonArray()).isTrue();
        assertThat(deviceJson.getAsJsonArray("updater").size())
                .isEqualTo(device.getUpdaterStatusList().size());
        assertThat(deviceJson.has("bvData")).isTrue();
        assertThat(deviceJson.get("bvData").isJsonArray()).isTrue();
        assertThat(deviceJson.getAsJsonArray("bvData").size())
                .isEqualTo(device.getDataStatusList().size());

    }

    @Test
    public void shouldDeserializeLocation() throws Exception {
        Device.Location location;
        try (InputStream is = getClass().getResourceAsStream("/device/device_location.json");
             InputStreamReader reader = new InputStreamReader(is)) {
            location = gson.fromJson(reader, Device.Location.class);
        }

        assertThat(location).isNotNull();
        assertThat(location.getType()).isEqualTo(Device.LocationType.OFFICE);
        assertThat(location.getSettedAt()).isEqualTo(100);
        assertThat(location.getLocation()).isEqualTo("location text");
    }

    @Test
    public void shouldSerializeLocation() throws Exception {
        Device.Location location =
                new Device.Location(Device.LocationType.RESTAURANT, 200, "MONACO");

        String jsonString = gson.toJson(location, Device.Location.class);

        JsonElement element = gson.fromJson(jsonString, JsonElement.class);
        assertThat(element.isJsonObject()).isTrue();
        JsonObject locationJson = element.getAsJsonObject();
        assertThat(locationJson.has("type")).isTrue();
        assertThat(locationJson.get("type").getAsInt())
                .isEqualTo(location.getType().getLocationValue());
        assertThat(locationJson.has("settedAt")).isTrue();
        assertThat(locationJson.get("settedAt").getAsInt()).isEqualTo(location.getSettedAt());
        assertThat(locationJson.has("location")).isTrue();
        assertThat(locationJson.get("location").getAsString()).isEqualTo(location.getLocation());
    }

    @Test
    public void shouldDeserializeBattery() throws Exception {
        Device.Battery battery;
        try (InputStream is = getClass().getResourceAsStream("/device/device_battery.json");
             InputStreamReader reader = new InputStreamReader(is)) {
            battery = gson.fromJson(reader, Device.Battery.class);
        }

        assertThat(battery).isNotNull();;
        assertThat(battery.getLevel()).isEqualTo(50);
        assertThat(battery.getStatus()).isEqualTo(Device.BatteryStatus.CHARGING);
        assertThat(battery.getCharge()).isNotNull();
        assertThat(battery.getCharge().getPluggedTimes()).isNotNull().hasSize(1);
        assertThat(battery.getCharge().getDispluggedTimes()).isNotNull().hasSize(1);
        assertThat(battery.getHealth()).isEqualTo(Device.BatteryHealth.OVERHEAT);
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
        assertThat(element).isNotNull();
        assertThat(element.isJsonObject()).isTrue();
        JsonObject batteryJson = element.getAsJsonObject();
        assertThat(batteryJson.has("level")).isTrue();
        assertThat(batteryJson.get("level").getAsInt()).isEqualTo(battery.getLevel());
        assertThat(batteryJson.has("status")).isTrue();
        assertThat(batteryJson.get("status").getAsString())
                .isEqualTo(battery.getStatus().name().toLowerCase());
        assertThat(batteryJson.has("charge")).isTrue();
        assertThat(batteryJson.get("charge").isJsonObject()).isTrue();
        JsonObject chargeJson = batteryJson.get("charge").getAsJsonObject();
        assertThat(chargeJson.has("pluggedTime")).isTrue();
        assertThat(chargeJson.get("pluggedTime").isJsonArray()).isTrue();
        assertThat(chargeJson.has("displuggedTime")).isTrue();
        assertThat(chargeJson.get("displuggedTime").isJsonArray()).isTrue();
        assertThat(batteryJson.has("health")).isTrue();
        assertThat(batteryJson.get("health").getAsString())
                .isEqualTo(battery.getHealth().name().toLowerCase());
    }

    @Test
    public void shouldDeserializeApplicationStatus() throws Exception {
        List<Device.ApplicationStatus> statusList;
        try (InputStream is = getClass().getResourceAsStream("/device/device_applicationstatus.json");
             InputStreamReader reader = new InputStreamReader(is)) {
            Type listType = new TypeToken<List<Device.ApplicationStatus>>(){}.getType();
            statusList = gson.fromJson(reader, listType);
        }

        assertThat(statusList).isNotNull().hasSize(2);
        {
            // status 1
            Device.ApplicationStatus status = statusList.get(0);
            assertThat(status.getInstalledTime()).isEqualTo(10);
            assertThat(status.getVersion()).isEqualTo(11);
            assertThat(status.getOpenedTime()).isEqualTo(12);
        }
        {
            // status 2
            Device.ApplicationStatus status = statusList.get(1);
            assertThat(status.getInstalledTime()).isEqualTo(20);
            assertThat(status.getVersion()).isEqualTo(21);
            assertThat(status.getOpenedTime()).isEqualTo(0);
        }

    }

    @Test
    public void shouldSerializeApplicationStatus() throws Exception {
        List<Device.ApplicationStatus> statusList =
                Arrays.asList(new Device.ApplicationStatus(11, 10, 12), new Device.ApplicationStatus(21, 20));
        Type listType = new TypeToken<List<Device.ApplicationStatus>>(){}.getType();
        String jsonString = gson.toJson(statusList, listType);

        JsonElement element = gson.fromJson(jsonString, JsonElement.class);
        assertThat(element.isJsonArray()).isTrue();
        JsonArray array = element.getAsJsonArray();
        assertThat(array.size()).isEqualTo(2);
        {
            // status 1
            JsonElement item = array.get(0);
            assertThat(item.isJsonObject()).isTrue();
            JsonObject object = item.getAsJsonObject();
            assertThat(object.has("installedTime")).isTrue();
            assertThat(object.get("installedTime").getAsInt()).isEqualTo(10);
            assertThat(object.has("version")).isTrue();
            assertThat(object.get("version").getAsInt()).isEqualTo(11);
            assertThat(object.has("openedTime")).isTrue();
            assertThat(object.get("openedTime").getAsInt()).isEqualTo(12);
        }
        {
            // status 2
            JsonElement item = array.get(1);
            assertThat(item.isJsonObject()).isTrue();
            JsonObject object = item.getAsJsonObject();
            assertThat(object.has("installedTime")).isTrue();
            assertThat(object.get("installedTime").getAsInt()).isEqualTo(20);
            assertThat(object.has("version")).isTrue();
            assertThat(object.get("version").getAsInt()).isEqualTo(21);
            assertThat(object.has("openedTime")).isFalse();

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

        assertThat(locationTypeList)
                .isNotNull()
                .hasSize(8)
                .contains(
                        Device.LocationType.RESTAURANT,
                        Device.LocationType.OFFICE,
                        null,
                        Device.LocationType.RESTAURANT,
                        Device.LocationType.OFFICE,
                        null,
                        null,
                        null);
    }

    @Test
    public void shouldSerializeLocationType() throws Exception {
        List<Device.LocationType> locationTypeList =
                Arrays.asList(Device.LocationType.OFFICE, Device.LocationType.RESTAURANT);
        Type listType = new TypeToken<List<Device.LocationType>>(){}.getType();
        String jsonString = gson.toJson(locationTypeList, listType);

        JsonElement element = gson.fromJson(jsonString, JsonElement.class);
        assertThat(element.isJsonArray()).isTrue();
        JsonArray array = element.getAsJsonArray();
        assertThat(array.size()).isEqualTo(2);
        for (int i = 0; i < array.size(); i++) {
            JsonElement item = array.get(i);
            assertThat(item.isJsonPrimitive()).isTrue();
            assertThat(item.getAsJsonPrimitive().isNumber()).isTrue();
            assertThat(item.getAsInt()).isEqualTo(locationTypeList.get(i).getLocationValue());
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

        assertThat(statusList)
                .isNotNull()
                .hasSize(3)
                .contains(
                        Device.DeviceStatus.OFFLINE,
                        Device.DeviceStatus.ONLINE,
                        null);
    }

    @Test
    public void shouldSerializeDeviceStatus() throws Exception {
        List<Device.DeviceStatus> statusList =
                Arrays.asList(Device.DeviceStatus.OFFLINE, Device.DeviceStatus.ONLINE);

        Type listType = new TypeToken<List<Device.DeviceStatus>>(){}.getType();
        String jsonString = gson.toJson(statusList, listType);

        JsonElement element = gson.fromJson(jsonString, JsonElement.class);
        assertThat(element.isJsonArray()).isTrue();
        JsonArray array = element.getAsJsonArray();
        assertThat(array.size()).isEqualTo(2);
        for (int i = 0; i < array.size(); i++) {
            JsonElement item = array.get(i);
            assertThat(item.isJsonPrimitive()).isTrue();
            assertThat(item.getAsJsonPrimitive().isString()).isTrue();
            assertThat(item.getAsString()).isEqualTo(statusList.get(i).name().toLowerCase());
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

        assertThat(statusList)
                .isNotNull()
                .hasSize(6)
                .contains(
                        Device.BatteryStatus.UNKNOWN,
                        Device.BatteryStatus.CHARGING,
                        Device.BatteryStatus.DISCHARGING,
                        Device.BatteryStatus.NOT_CHARGING,
                        Device.BatteryStatus.FULL,
                        null);
    }

    @Test
    public void shouldSerializeBatteryStatus() throws Exception {
        List<Device.BatteryStatus> statusList =
                Arrays.asList(Device.BatteryStatus.CHARGING, Device.BatteryStatus.FULL);
        Type listType = new TypeToken<List<Device.BatteryStatus>>(){}.getType();
        String jsonString = gson.toJson(statusList, listType);

        JsonElement element = gson.fromJson(jsonString, JsonElement.class);
        assertThat(element.isJsonArray()).isTrue();
        JsonArray array = element.getAsJsonArray();
        assertThat(array.size()).isEqualTo(2);
        for (int i = 0; i < array.size(); i++) {
            JsonElement item = array.get(i);
            assertThat(item.isJsonPrimitive()).isTrue();
            assertThat(item.getAsJsonPrimitive().isString()).isTrue();
            assertThat(item.getAsString()).isEqualTo(statusList.get(i).name().toLowerCase());
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

        assertThat(batteryHealths)
                .isNotNull()
                .hasSize(8)
                .contains(
                        Device.BatteryHealth.UNKNOWN,
                        Device.BatteryHealth.GOOD,
                        Device.BatteryHealth.OVERHEAT,
                        Device.BatteryHealth.DEAD,
                        Device.BatteryHealth.OVER_VOLTAGE,
                        Device.BatteryHealth.UNSPECIFIED_FAILURE,
                        Device.BatteryHealth.COLD,
                        null);
    }

    @Test
    public void shouldSerializeBatteryHealth() throws Exception {
        List<Device.BatteryHealth> healths =
                Arrays.asList(Device.BatteryHealth.GOOD, Device.BatteryHealth.DEAD, Device.BatteryHealth.OVERHEAT);

        Type listType = new TypeToken<List<Device.BatteryHealth>>(){}.getType();
        String jsonString = gson.toJson(healths, listType);
        assertThat(jsonString).isNotNull();

        JsonElement element = gson.fromJson(jsonString, JsonElement.class);
        assertThat(element.isJsonArray()).isTrue();
        JsonArray array = element.getAsJsonArray();
        assertThat(array.size()).isEqualTo(3);
        for (int i = 0; i < array.size(); i++) {
            JsonElement item = array.get(i);
            assertThat(item.isJsonPrimitive()).isTrue();
            assertThat(item.getAsJsonPrimitive().isString()).isTrue();
            assertThat(item.getAsJsonPrimitive().getAsString())
                    .isEqualTo(healths.get(i).name().toLowerCase());
        }
    }

    @Test
    public void shouldDeserializeBatteryCharge() throws Exception {
        Device.BatteryCharge charge;
        try (InputStream is = getClass().getResourceAsStream("/device/device_batterycharge.json");
             InputStreamReader reader = new InputStreamReader(is)) {
            charge = gson.fromJson(reader, Device.BatteryCharge.class);
        }

        assertThat(charge).isNotNull();
        assertThat(charge.getPluggedTimes()).isNotNull().hasSize(3).contains(1, 2, 3);
        assertThat(charge.getDispluggedTimes()).isNotNull().hasSize(2).contains(10, 20);
    }

    @Test
    public void shouldSerializeBatteryCharge() throws Exception {
        Device.BatteryCharge charge = new Device.BatteryCharge();
        charge.addPluggedTimes(10);
        charge.addPluggedTimes(20);
        charge.addDispluggedTime(100);

        String jsonString = gson.toJson(charge, Device.BatteryCharge.class);

        JsonElement element = gson.fromJson(jsonString, JsonElement.class);
        assertThat(element.isJsonObject()).isTrue();
        JsonObject chargeJson = element.getAsJsonObject();
        assertThat(chargeJson.has("pluggedTime")).isTrue();
        assertThat(chargeJson.get("pluggedTime").isJsonArray()).isTrue();
        JsonArray pluggedArray = chargeJson.getAsJsonArray("pluggedTime");
        assertThat(pluggedArray.size()).isEqualTo(charge.getPluggedTimes().size());
        for (int i = 0; i < pluggedArray.size(); i++) {
            JsonElement item = pluggedArray.get(i);
            assertThat(item.isJsonPrimitive()).isTrue();
            assertThat(item.getAsJsonPrimitive().isNumber()).isTrue();
            assertThat(item.getAsInt()).isEqualTo(charge.getPluggedTimes().get(i));
        }
        assertThat(chargeJson.has("displuggedTime")).isTrue();
        assertThat(chargeJson.get("displuggedTime").isJsonArray()).isTrue();
        JsonArray displuggedArray = chargeJson.getAsJsonArray("displuggedTime");
        assertThat(displuggedArray.size()).isEqualTo(charge.getDispluggedTimes().size());
        for (int i = 0; i < displuggedArray.size(); i++) {
            JsonElement item = displuggedArray.get(i);
            assertThat(item.isJsonPrimitive()).isTrue();
            assertThat(item.getAsJsonPrimitive().isNumber()).isTrue();
            assertThat(item.getAsInt()).isEqualTo(charge.getDispluggedTimes().get(i));
        }
    }

}