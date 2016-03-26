package com.bookvideo.library.domain;

import java.util.Collections;
import java.util.List;

public class Duration {
    private boolean activate;
    private TimeSlot timeslot;
    private String start;
    private String end;
    private List<DayOfWeek> days;

    public boolean isActivate() {
        return activate;
    }

    public TimeSlot getTimeslot() {
        return timeslot;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public List<DayOfWeek> getDays() {
        return days != null ? Collections.unmodifiableList(days) : Collections.<DayOfWeek>emptyList();
    }

    public enum DayOfWeek {
        MON("mo"),
        TUE("tu"),
        WED("we"),
        THU("th"),
        FRI("fi"),
        SAT("sa"),
        SUN("su");

        private String key;

        private DayOfWeek(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }

        public static DayOfWeek valueByKey(String key) {
            if (key == null || key.isEmpty()) {
                throw new IllegalArgumentException("key is null or empty");
            }
            for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
                if (dayOfWeek.getKey().equalsIgnoreCase(key)) {
                    return dayOfWeek;
                }
            }
            throw new IllegalArgumentException("unknown key for DayOfWeek : " + key);
        }
    }

    public enum TimeSlot {
        ALLDAY, MORNING, LUNCH, DINNER, AFTERNOON, NIGHT
    }
}
