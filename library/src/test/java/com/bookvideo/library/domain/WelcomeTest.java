package com.bookvideo.library.domain;

import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;

import static org.assertj.core.api.Assertions.assertThat;


public class WelcomeTest {
    private Gson gson;

    @Before
    public void setUp() throws Exception {
        gson = GsonCreator.getInstance().createGson();
    }

    @Test
    public void shouldDeserializeJson() throws Exception {
        Welcome welcome;
        try (InputStream is = getClass().getResourceAsStream("/welcome/welcome_simple.json");
             InputStreamReader reader = new InputStreamReader(is)) {
            welcome = gson.fromJson(reader, Welcome.class);
        }

        assertThat(welcome).isNotNull();
        assertThat(welcome.getRestaurantId())
                .isNotNull()
                .isEqualTo("welcome_restId");
        // style
        {
            assertThat(welcome.getStyle()).isNotNull();
            assertThat(welcome.getStyle().getBackground()).isNotNull();
            assertThat(welcome.getStyle().getBackground().getImageId()).isEqualTo("bg_id");

            assertThat(welcome.getStyle().getFont()).isNotNull();
            assertThat(welcome.getStyle().getFont().getName()).isEqualTo("font_name");
        }
        assertThat(welcome.getLanguages())
                .isNotNull()
                .contains("en", "fr");
        // ads
        assertThat(welcome.getAds()).isNotNull();
        assertThat(welcome.getAds().getCatchPhrase())
                .isNotNull()
                .isEqualTo("ad phrase");
        assertThat(welcome.getAds().getLink()).isNotNull();
        assertThat(welcome.getAds().getLink().getType())
                .isNotNull()
                .isEqualTo(DocumentType.MENU);
    }

}