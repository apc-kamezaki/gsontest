package com.bookvideo.library.domain;

import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

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

        assertThat(duration, is(notNullValue()));
        assertThat(duration.getTimeslot(), is("Timeslot string"));
        assertThat(duration.getStart(), is("Start day"));
        assertThat(duration.getEnd(), is("End day"));
        assertThat(duration.getDays(), hasSize(2));
        assertThat(duration.getDays(), contains(Duration.DayOfWeek.MON, Duration.DayOfWeek.FRI));
    }

    @Test
    public void shouldDeserializeDayOfWeek() throws Exception {
        Duration duration;
        try (InputStream is = getClass().getResourceAsStream("/duration/duration_dayofweek.json");
             InputStreamReader reader = new InputStreamReader(is)) {
            duration = gson.fromJson(reader, Duration.class);
        }

        assertThat(duration, is(notNullValue()));
        assertThat(duration.getDays(), hasSize(7));
        assertThat(duration.getDays(),
                contains(Duration.DayOfWeek.SUN,
                        Duration.DayOfWeek.MON,
                        Duration.DayOfWeek.TUE,
                        Duration.DayOfWeek.WED,
                        Duration.DayOfWeek.THU,
                        Duration.DayOfWeek.FRI,
                        Duration.DayOfWeek.SAT));

    }

    @Test
    public void shouldDeserializeIllegalDayOfWeekString() throws Exception {
        String illegalString = "{ \"days\": [ \"mo\", \"xxx\", \"su\"]}";

        Duration duration = gson.fromJson(illegalString, Duration.class);
        assertThat(duration, is(notNullValue()));
        assertThat(duration.getDays(), hasSize(3));
        assertThat(duration.getDays(), contains(Duration.DayOfWeek.MON, null, Duration.DayOfWeek.SUN));
    }
}