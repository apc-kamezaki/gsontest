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


public class MediaTest {
    private Gson gson;

    @Before
    public void setUp() throws Exception {
        gson = GsonCreator.getInstance().createGson();
    }

    @Test
    public void shouldDeserializeJson() throws Exception {
        List<Media> mediaList;
        try (InputStream is = getClass().getResourceAsStream("/media/media_simple.json");
             InputStreamReader reader = new InputStreamReader(is)) {
            Type listType = new TypeToken<List<Media>>(){}.getType();
            mediaList = gson.fromJson(reader, listType);
        }

        assertThat(mediaList).isNotNull().hasSize(3);

        // media 1
        {
            Media media = mediaList.get(0);
            assertThat(media.getRestaurantId()).isEqualTo("restId1");
            assertThat(media.getType()).isEqualTo("type1");
            assertThat(media.getName()).isEqualTo("name1");
        }

        // media 3
        {
            Media media = mediaList.get(2);
            assertThat(media.getRestaurantId()).isEqualTo("restId3");
            assertThat(media.getType()).isEqualTo("type3");
            assertThat(media.getName()).isEqualTo("name3");
        }
    }

    @Test
    public void shouldDeserializeImageSize() throws Exception {
        List<ImageSize> sizeList;
        try (InputStream is = getClass().getResourceAsStream("/media/image_imagesize.json");
             InputStreamReader reader = new InputStreamReader(is)) {
            Type listType = new TypeToken<List<ImageSize>>(){}.getType();
            sizeList = gson.fromJson(reader, listType);
        }

        assertThat(sizeList)
                .isNotNull()
                .hasSize(6)
                .contains(
                        ImageSize.NONE,
                        ImageSize.QUARTER,
                        ImageSize.THIRD,
                        ImageSize.HALF,
                        ImageSize.FULL,
                        ImageSize.NONE);
    }
}