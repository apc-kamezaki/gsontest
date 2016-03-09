package com.bookvideo.library.domain;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Device {
    private int location = 0;
    private List<Location> locations;
    @SerializedName("lastResto")
    private String lastRestaurant;
    private DeviceStatus status;
    private Battery battery;
    @SerializedName("bvUpdate")
    private List<ApplicationStatus> appStatusList;
    @SerializedName("updater")
    private List<ApplicationStatus> updaterStatusList;
    @SerializedName("bvData")
    private List<ApplicationStatus> dataStatusList;

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public List<Location> getLocations() {
        return locations != null ? Collections.unmodifiableList(locations) : Collections.<Location>emptyList();
    }

    public void addToLocations(Location location) {
        if (locations == null) {
            locations = new ArrayList<>();
        }
        locations.add(location);
    }

    public String getLastRestaurant() {
        return lastRestaurant;
    }

    public void setLastRestaurant(String lastRestaurant) {
        this.lastRestaurant = lastRestaurant;
    }

    public DeviceStatus getStatus() {
        return status;
    }

    public void setStatus(DeviceStatus status) {
        this.status = status;
    }

    public Battery getBattery() {
        return battery;
    }

    public void setBattery(Battery battery) {
        this.battery = battery;
    }

    public List<ApplicationStatus> getAppStatusList() {
        return appStatusList != null
                ? Collections.unmodifiableList(appStatusList) : Collections.<ApplicationStatus>emptyList();
    }

    public void addAppStatus(ApplicationStatus status) {
        if (appStatusList == null) {
            appStatusList = new ArrayList<>();
        }
        appStatusList.add(status);
    }


    public List<ApplicationStatus> getUpdaterStatusList() {
        return updaterStatusList != null
                ? Collections.unmodifiableList(updaterStatusList) : Collections.<ApplicationStatus>emptyList();
    }

    public void addUpdaterStatusList(ApplicationStatus status) {
        if (updaterStatusList == null) {
            updaterStatusList = new ArrayList<>();
        }
        updaterStatusList.add(status);
    }

    public List<ApplicationStatus> getDataStatusList() {
        return dataStatusList != null
                ? Collections.unmodifiableList(dataStatusList) : Collections.<ApplicationStatus>emptyList();
    }

    public void addDataStatusList(ApplicationStatus status) {
        if (dataStatusList == null) {
            dataStatusList = new ArrayList<>();
        }
        dataStatusList.add(status);
    }

    public static class Location {
        private LocationType type;
        private int settedAt;
        private String location;

        public Location() {

        }

        public Location(LocationType type, int settedAt, String location) {
            this.type = type;
            this.settedAt = settedAt;
            this.location = location;
        }

        public LocationType getType() {
            return type;
        }

        public int getSettedAt() {
            return settedAt;
        }

        public String getLocation() {
            return location;
        }
    }

    public static class Battery {
        private int level;
        private BatteryStatus status;
        private BatteryCharge charge;
        private BatteryHealth health;

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public BatteryStatus getStatus() {
            return status;
        }

        public void setStatus(BatteryStatus status) {
            this.status = status;
        }

        public BatteryCharge getCharge() {
            return charge;
        }

        public void setCharge(BatteryCharge charge) {
            this.charge = charge;
        }

        public BatteryHealth getHealth() {
            return health;
        }

        public void setHealth(BatteryHealth health) {
            this.health = health;
        }
    }

    public static class BatteryCharge {
        @SerializedName("pluggedTime")
        private List<Integer> pluggedTimes;
        @SerializedName("displuggedTime")
        private List<Integer> displuggedTimes;

        public List<Integer> getPluggedTimes() {
            return pluggedTimes != null
                    ? Collections.unmodifiableList(pluggedTimes) : Collections.<Integer>emptyList();
        }

        public void addPluggedTimes(int pluggedTime) {
            if (pluggedTimes == null) {
                pluggedTimes = new ArrayList<>();
            }
            pluggedTimes.add(pluggedTime);
        }

        public List<Integer> getDispluggedTimes() {
            return displuggedTimes != null
                    ? Collections.unmodifiableList(displuggedTimes) : Collections.<Integer>emptyList();
        }

        public void addDispluggedTime(int displuggedTime) {
            if (displuggedTimes == null) {
                displuggedTimes = new ArrayList<>();
            }
            displuggedTimes.add(displuggedTime);
        }
    }

    public static class ApplicationStatus {
        private int installedTime;
        private int version;
        private int openedTime;

        public ApplicationStatus() {
            this(0, 0, 0);
        }

        public ApplicationStatus(int version, int installedtime) {
            this(version, installedtime, 0);

        }

        public ApplicationStatus(int version, int installedTime, int openedTime) {
            this.version = version;
            this.installedTime = installedTime;
            this.openedTime = openedTime;
        }

        public int getInstalledTime() {
            return installedTime;
        }

        public void setInstalledTime(int installedTime) {
            this.installedTime = installedTime;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public int getOpenedTime() {
            return openedTime;
        }

        public void setOpenedTime(int openedTime) {
            this.openedTime = openedTime;
        }
    }

    public enum LocationType {
        RESTAURANT(0),
        OFFICE(1);

        private int typeValue;
        private LocationType(int typeValue) {
            this.typeValue = typeValue;
        }

        public int getLocationValue() {
            return typeValue;
        }

        public static LocationType valueOf(int typeValue) {
            for (LocationType location: LocationType.values()) {
                if (location.getLocationValue() == typeValue) {
                    return location;
                }
            }
            throw new IllegalArgumentException("unknown value " + typeValue);
        }
    }

    public enum DeviceStatus {
        OFFLINE, ONLINE
    }

    public enum BatteryStatus {
        UNKNOWN, CHARGING, DISCHARGING, NOT_CHARGING, FULL
    }

    public enum BatteryHealth {
        UNKNOWN, GOOD, OVERHEAT, DEAD, OVER_VOLTAGE, UNSPECIFIED_FAILURE, COLD
    }
}
