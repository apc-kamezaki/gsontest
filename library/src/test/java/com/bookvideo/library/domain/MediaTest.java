package com.bookvideo.library.domain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;


public class MediaTest {
    private Gson gson;

    @Before
    public void setUp() throws Exception {
        GsonBuilder gsonBuilder = new GsonBuilder();
        MediaConverterAdapter mediaAdapter = new MediaConverterAdapter();
        mediaAdapter.apply(gsonBuilder);
        gson = gsonBuilder.create();
    }

    @Test
    public void shouldDeserializeJson() throws Exception {
        List<Media> mediaList;
        try (InputStream is = getClass().getResourceAsStream("/media/media_simple.json");
             InputStreamReader reader = new InputStreamReader(is)) {
            Type listType = new TypeToken<List<Media>>(){}.getType();
            mediaList = gson.fromJson(reader, listType);
        }

        assertThat(mediaList, is(notNullValue()));
        assertThat(mediaList.size(), is(3));

        // media 1
        {
            Media media = mediaList.get(0);
            assertThat(media.getRestaurantId(), is("restId1"));
            assertThat(media.getType(), is("type1"));
            assertThat(media.getName(), is("name1"));
        }

        // media 3
        {
            Media media = mediaList.get(2);
            assertThat(media.getRestaurantId(), is("restId3"));
            assertThat(media.getType(), is("type3"));
            assertThat(media.getName(), is("name3"));
        }
    }

}