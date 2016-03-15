package com.bookvideo.library.domain;

import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GeographicTest {
    private Gson gson;

    @Before
    public void setUp() throws Exception {
        gson = GsonCreator.getInstance().createGson();
    }

    @Test
    public void shouldDeserializeJson() throws Exception {
        String str = "{ \"name\": \"geo name\"}";

        Geographic geo = gson.fromJson(str, Geographic.class);
        assertThat(geo).isNotNull();
        assertThat(geo.getName()).isEqualTo("geo name");
    }
}