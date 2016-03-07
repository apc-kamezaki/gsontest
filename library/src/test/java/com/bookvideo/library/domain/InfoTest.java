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

import static org.hamcrest.CoreMatchers.*;

import static org.junit.Assert.assertThat;

public class InfoTest {
    private Gson gson;

    @Before
    public void setUp() throws Exception {
        GsonBuilder gsonBuilder = new GsonBuilder();
        new ImageSizeConverterAdapter().apply(gsonBuilder);
        new MenuItemConverterAdapter().apply(gsonBuilder);
        gson = gsonBuilder.create();
    }

    @Test
    public void shouldDeserializeJson() throws Exception {
        List<MenuItem.Info> infoList;
        try (InputStream is = getClass().getResourceAsStream("/menu/info_simple.json");
             InputStreamReader reader = new InputStreamReader(is)) {
            Type listType = new TypeToken<List<MenuItem.Info>>(){}.getType();
            infoList = gson.fromJson(reader, listType);
        }

        assertThat(infoList, is(notNullValue()));
        assertThat(infoList.size(), is(6));

        // type in page
        {
            MenuItem.Info info = infoList.get(0);
            assertThat(info.getType(), is(MenuItem.InfoType.INPAGE));
            assertThat(info.getDescription(), is("description"));
        }

        // type full and quarter size
        {
            MenuItem.Info info = infoList.get(1);
            assertThat(info.getType(), is(MenuItem.InfoType.FULL));
            assertThat(info.getSize(), is(ImageSize.QUARTER));
            assertThat(info.getDescription(), is(nullValue()));

        }

        // type full and third size
        {
            MenuItem.Info info = infoList.get(2);
            assertThat(info.getType(), is(MenuItem.InfoType.FULL));
            assertThat(info.getSize(), is(ImageSize.THIRD));
            assertThat(info.getDescription(), is(nullValue()));

        }

        // type full and half size
        {
            MenuItem.Info info = infoList.get(3);
            assertThat(info.getType(), is(MenuItem.InfoType.FULL));
            assertThat(info.getSize(), is(ImageSize.HALF));
            assertThat(info.getDescription(), is("description for half"));

        }

        // type full and full size
        {
            MenuItem.Info info = infoList.get(4);
            assertThat(info.getType(), is(MenuItem.InfoType.FULL));
            assertThat(info.getSize(), is(ImageSize.FULL));
            assertThat(info.getDescription(), is("description for full"));
        }

        // type null
        {
            MenuItem.Info info = infoList.get(5);
            assertThat(info.getType(), is(MenuItem.InfoType.NONE));
            assertThat(info.getSize(), is(ImageSize.NONE));
            assertThat(info.getDescription(), is("description for none"));

        }
    }
}
