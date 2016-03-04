package com.bookvideo.library.domain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

public class TranslationTest {
    private Gson gson;

    @Before
    public void setUp() throws Exception {
        GsonBuilder gsonBuilder = new GsonBuilder();
        DocumentTypeConverterAdapter dtAdapter = new DocumentTypeConverterAdapter();
        dtAdapter.apply(gsonBuilder);
        TranslationConverterAdapter translationAdapter = new TranslationConverterAdapter();
        translationAdapter.apply(gsonBuilder);
        gson = gsonBuilder.create();
    }

    @Test
    public void shouldDeserializeJson() throws Exception {
        Translation translation;
        try (InputStream is = getClass().getResourceAsStream("/translation/translation_simple.json");
             InputStreamReader reader = new InputStreamReader(is)) {
            translation = gson.fromJson(reader, Translation.class);
        }

        assertThat(translation, is(notNullValue()));
        assertThat(translation.getRestaurantId(), is("translation_restaurant"));
        assertThat(translation.getLocale(), is("en"));
        assertThat(translation.getType(), is(DocumentType.DASHBOARD));
        assertThat(translation.getTranslation(), is(notNullValue()));
        assertThat(translation.getTranslation().size(), is(2));

        // translation 1
        {
            Translation.TranslationItem item = translation.getTranslation().get(0);
            assertThat(item.getKey(), is("key_1"));
            assertThat(item.getValue(), is("value_1"));
        }

        // translation 2
        {
            Translation.TranslationItem item = translation.getTranslation().get(1);
            assertThat(item.getKey(), is("key_2"));
            assertThat(item.getValue(), is("value_2"));
        }
    }

}