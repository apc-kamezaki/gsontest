package com.bookvideo.library.domain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class WelcomeTest {
    private Gson gson;

    @Before
    public void setUp() throws Exception {
        GsonBuilder gsonBuilder = new GsonBuilder();
        DocumentTypeConverterAdapter documentAdapter = new DocumentTypeConverterAdapter();
        documentAdapter.apply(gsonBuilder);
        WelcomeConverterAdapter welcomeAdapter = new WelcomeConverterAdapter();
        welcomeAdapter.apply(gsonBuilder);
        gson = gsonBuilder.create();
    }

    @Test
    public void shouldDeserializeJson() throws Exception {
        Welcome welcome;
        try (InputStream is = getClass().getResourceAsStream("/welcome/welcome_simple.json");
             InputStreamReader reader = new InputStreamReader(is)) {
            welcome = gson.fromJson(reader, Welcome.class);
        }

        assertThat(welcome, is(notNullValue()));
        assertThat(welcome.getRestaurantId(), is("welcome_restId"));
        // style
        {
            assertThat(welcome.getStyle(), is(notNullValue()));
            assertThat(welcome.getStyle().getBackground(), is(notNullValue()));
            assertThat(welcome.getStyle().getBackground().getImageId(), is("bg_id"));

            assertThat(welcome.getStyle().getFont(), is(notNullValue()));
            assertThat(welcome.getStyle().getFont().getName(), is("font_name"));
        }
        assertThat(welcome.getLanguages(), hasItems("en", "fr"));
        // ads
        assertThat(welcome.getAds(), is(notNullValue()));
        assertThat(welcome.getAds().getCatchPhrase(), is("ad phrase"));
        assertThat(welcome.getAds().getLink(), is(notNullValue()));
        assertThat(welcome.getAds().getLink().getType(), is(DocumentType.MENU));
    }

}