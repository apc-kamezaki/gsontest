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

public class InfoTest {
    private Gson gson;

    @Before
    public void setUp() throws Exception {
        gson = GsonCreator.getInstance().createGson();
    }

    @Test
    public void shouldDeserializeJson() throws Exception {
        List<MenuItem.Info> infoList;
        try (InputStream is = getClass().getResourceAsStream("/menu/info_simple.json");
             InputStreamReader reader = new InputStreamReader(is)) {
            Type listType = new TypeToken<List<MenuItem.Info>>(){}.getType();
            infoList = gson.fromJson(reader, listType);
        }

        assertThat(infoList).isNotNull().hasSize(6);

        // type in page
        {
            MenuItem.Info info = infoList.get(0);
            assertThat(info.getType()).isEqualTo(MenuItem.InfoType.INPAGE);
            assertThat(info.getDescription()).isEqualTo("description");
        }

        // type full and quarter size
        {
            MenuItem.Info info = infoList.get(1);
            assertThat(info.getType()).isEqualTo(MenuItem.InfoType.FULL);
            assertThat(info.getSize()).isEqualTo(ImageSize.QUARTER);
            assertThat(info.getDescription()).isNull();

        }

        // type full and third size
        {
            MenuItem.Info info = infoList.get(2);
            assertThat(info.getType()).isEqualTo(MenuItem.InfoType.FULL);
            assertThat(info.getSize()).isEqualTo(ImageSize.THIRD);
            assertThat(info.getDescription()).isNull();

        }

        // type full and half size
        {
            MenuItem.Info info = infoList.get(3);
            assertThat(info.getType()).isEqualTo(MenuItem.InfoType.FULL);
            assertThat(info.getSize()).isEqualTo(ImageSize.HALF);
            assertThat(info.getDescription()).isEqualTo("description for half");

        }

        // type full and full size
        {
            MenuItem.Info info = infoList.get(4);
            assertThat(info.getType()).isEqualTo(MenuItem.InfoType.FULL);
            assertThat(info.getSize()).isEqualTo(ImageSize.FULL);
            assertThat(info.getDescription()).isEqualTo("description for full");
        }

        // type null
        {
            MenuItem.Info info = infoList.get(5);
            assertThat(info.getType()).isEqualTo(MenuItem.InfoType.NONE);
            assertThat(info.getSize()).isEqualTo(ImageSize.NONE);
            assertThat(info.getDescription()).isEqualTo("description for none");

        }
    }
}
