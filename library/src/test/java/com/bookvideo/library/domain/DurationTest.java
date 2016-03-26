package com.bookvideo.library.domain;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class DurationTest {
    private Gson gson;

    @Before
    public void setUp() throws Exception {
        gson = GsonCreator.getInstance().createGson();
    }

    @Test
    public void shouldDeserializeJson() throws Exception {
        Duration duration;
        try (InputStream is = getClass().getResourceAsStream("/duration/duration_simple.json");
             InputStreamReader reader = new InputStreamReader(is)) {
            duration = gson.fromJson(reader, Duration.class);
        }

        assertThat(duration).isNotNull();
        assertThat(duration.isActivate()).isTrue();
        assertThat(duration.getTimeslot()).isEqualTo(Duration.TimeSlot.ALLDAY);
        assertThat(duration.getStart()).isEqualTo("Start day");
        assertThat(duration.getEnd()).isEqualTo("End day");
        assertThat(duration.getDays())
                .isNotNull()
                .hasSize(2)
                .contains(Duration.DayOfWeek.MON, Duration.DayOfWeek.FRI);
    }

    @Test
    public void shouldDeserializeTimeSlot() throws Exception {
        List<Duration.TimeSlot> timeSlotList;
        try (InputStream is = getClass().getResourceAsStream("/duration/duration_timeslot.json");
             InputStreamReader reader = new InputStreamReader(is)) {
            Type listType = new TypeToken<List<Duration.TimeSlot>>(){}.getType();
            timeSlotList = gson.fromJson(reader, listType);
        }

        assertThat(timeSlotList)
                .isNotNull()
                .hasSize(7)
                .contains(
                        null,
                        Duration.TimeSlot.ALLDAY,
                        Duration.TimeSlot.MORNING,
                        Duration.TimeSlot.LUNCH,
                        Duration.TimeSlot.DINNER,
                        Duration.TimeSlot.AFTERNOON,
                        Duration.TimeSlot.NIGHT);
    }

    @Test
    public void shouldDeserializeDayOfWeek() throws Exception {
        Duration duration;
        try (InputStream is = getClass().getResourceAsStream("/duration/duration_dayofweek.json");
             InputStreamReader reader = new InputStreamReader(is)) {
            duration = gson.fromJson(reader, Duration.class);
        }

        assertThat(duration).isNotNull();
        assertThat(duration.getDays())
                .isNotNull()
                .hasSize(7)
                .contains(
                        Duration.DayOfWeek.SUN,
                        Duration.DayOfWeek.MON,
                        Duration.DayOfWeek.TUE,
                        Duration.DayOfWeek.WED,
                        Duration.DayOfWeek.THU,
                        Duration.DayOfWeek.FRI,
                        Duration.DayOfWeek.SAT);
    }

    @Test
    public void shouldDeserializeIllegalDayOfWeekString() throws Exception {
        String illegalString = "{ \"days\": [ \"mo\", \"xxx\", \"su\"]}";

        Duration duration = gson.fromJson(illegalString, Duration.class);
        assertThat(duration).isNotNull();
        assertThat(duration.getDays())
                .isNotNull()
                .hasSize(3)
                .contains(Duration.DayOfWeek.MON, null, Duration.DayOfWeek.SUN);
    }
}