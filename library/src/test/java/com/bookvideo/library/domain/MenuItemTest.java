package com.bookvideo.library.domain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class MenuItemTest {
    private Gson gson;

    @Before
    public void setUp() throws Exception {
        GsonBuilder gsonBuilder = new GsonBuilder();
        new ImageSizeConverterAdapter().apply(gsonBuilder);
        new AllergenConverterAdapter().apply(gsonBuilder);
        new SemioticConverterAdapter().apply(gsonBuilder);
        new StyleConverterAdapter().apply(gsonBuilder);
        new MenuItemConverterAdapter().apply(gsonBuilder);
        gson = gsonBuilder.create();
    }

    @Test
    public void shouldDeserializeJson() throws Exception {
        MenuItem menuItem;
        try (InputStream is = getClass().getResourceAsStream("/menu/menuitem_simple.json");
             InputStreamReader reader = new InputStreamReader(is)) {
            menuItem = gson.fromJson(reader, MenuItem.class);
        }

        assertThat(menuItem, is(notNullValue()));
        assertThat(menuItem.getRestaurantId(), is("menuitem_restaurant_id"));
        assertThat(menuItem.getCategoryId(), is("category_id"));
        {
            // style
            Style style = menuItem.getStyle();
            assertThat(style, is(notNullValue()));
            assertThat(style.getFont().getName(), is("font_name"));
            assertThat(style.getFont().getAlign(), is(Style.Align.RIGHT));
        }
        assertThat(menuItem.getTitle(), is("Title"));
        assertThat(menuItem.getDescription(), is("Description"));
        {
            // info
            MenuItem.Info info = menuItem.getInfo();
            assertThat(info, is(notNullValue()));
            assertThat(info.getType(), is(MenuItem.InfoType.FULL));
            assertThat(info.getSize(), is(ImageSize.HALF));
            assertThat(info.getDescription(), is("Info description"));
        }
        assertThat(menuItem.getPrices().size(), is(3));
        assertThat(menuItem.getPrices(), contains(3.0f, 0.0f, 5.0f));
        assertThat(menuItem.getSuggestions().size(), is(2));
        assertThat(menuItem.getSuggestions(), contains("suggest1_id", "suggest2_id"));
        assertThat(menuItem.getCalories(), is(321.0f));
        assertThat(menuItem.getAlcohol(), is("Alcohol string"));
        assertThat(menuItem.getAlg().size(), is(1));
        assertThat(menuItem.getAlg(), contains(Allergen.NUTS));
        assertThat(menuItem.getSemiotics().size(), is(2));
        assertThat(menuItem.getSemiotics(), contains(Semiotic.HALAL, Semiotic.VEG));
        assertThat(menuItem.getMillesime(), is("text"));
        assertThat(menuItem.getMeasure(), is("measure text"));
        assertThat(menuItem.getCountry().getName(), is("us"));
        assertThat(menuItem.getRegion().getName(), is("Texas"));
        assertThat(menuItem.getRegion().getCountry(), is("us"));
        assertThat(menuItem.getCepage().getName(), is("Name of cepage"));
        assertThat(menuItem.getCulture(), is("Text of culture"));
    }

    @Test
    public void shouldDeserializeAllergen() throws Exception {
        MenuItem menuItem;
        try (InputStream is = getClass().getResourceAsStream("/menu/menuitem_allergen.json");
             InputStreamReader reader = new InputStreamReader(is)) {
            menuItem = gson.fromJson(reader, MenuItem.class);
        }

        assertThat(menuItem, is(notNullValue()));
        assertThat(menuItem.getAlg(), hasSize(4));
        assertThat(menuItem.getAlg(),
                contains(Allergen.NUTS,
                        Allergen.CELERY,
                        Allergen.MILK,
                        Allergen.MUSTARD));

    }
}