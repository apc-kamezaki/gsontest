package com.bookvideo.library.domain;

import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;

import static org.assertj.core.api.Assertions.assertThat;


public class TranslationTest {
    private Gson gson;

    @Before
    public void setUp() throws Exception {
        gson = GsonCreator.getInstance().createGson();
    }

    @Test
    public void shouldDeserializeJson() throws Exception {
        Translation translation;
        try (InputStream is = getClass().getResourceAsStream("/translation/translation_simple.json");
             InputStreamReader reader = new InputStreamReader(is)) {
            translation = gson.fromJson(reader, Translation.class);
        }

        assertThat(translation).isNotNull();
        assertThat(translation.getRestaurantId()).isEqualTo("translation_restaurant");
        assertThat(translation.getLocale()).isEqualTo("en");
        assertThat(translation.getType()).isEqualTo(DocumentType.DASHBOARD);
        assertThat(translation.getTranslation())
                .isNotNull()
                .hasSize(2);

        // translation 1
        {
            Translation.TranslationItem item = translation.getTranslation().get(0);
            assertThat(item.getKey()).isEqualTo("key_1");
            assertThat(item.getValue()).isEqualTo("value_1");
        }

        // translation 2
        {
            Translation.TranslationItem item = translation.getTranslation().get(1);
            assertThat(item.getKey()).isEqualTo("key_2");
            assertThat(item.getValue()).isEqualTo("value_2");
        }
    }

}