package com.bookvideo.library.domain;

import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;

import static org.assertj.core.api.Assertions.assertThat;

public class MenuItemTest {
    private Gson gson;

    @Before
    public void setUp() throws Exception {
        gson = GsonCreator.getInstance().createGson();
    }

    @Test
    public void shouldDeserializeJson() throws Exception {
        MenuItem menuItem;
        try (InputStream is = getClass().getResourceAsStream("/menu/menuitem_simple.json");
             InputStreamReader reader = new InputStreamReader(is)) {
            menuItem = gson.fromJson(reader, MenuItem.class);
        }

        assertThat(menuItem).isNotNull();
        assertThat(menuItem.getRestaurantId()).isEqualTo("menuitem_restaurant_id");
        assertThat(menuItem.getCategoryId()).isEqualTo("category_id");
        {
            // style
            Style style = menuItem.getStyle();
            assertThat(style).isNotNull();
            assertThat(style.getFont()).isNotNull();
            assertThat(style.getFont().getName()).isEqualTo("font_name");
            assertThat(style.getFont().getAlign()).isEqualTo(Style.Align.RIGHT);
        }
        assertThat(menuItem.getTitle()).isEqualTo("Title");
        assertThat(menuItem.getDescription()).isEqualTo("Description");
        {
            // info
            MenuItem.Info info = menuItem.getInfo();
            assertThat(info).isNotNull();
            assertThat(info.getType()).isEqualTo(MenuItem.InfoType.FULL);
            assertThat(info.getSize()).isEqualTo(ImageSize.HALF);
            assertThat(info.getDescription()).isEqualTo("Info description");
        }
        assertThat(menuItem.getDuration()).isNotNull();
        assertThat(menuItem.getDuration().getDays())
                .isNotNull()
                .hasSize(2)
                .contains(Duration.DayOfWeek.SAT, Duration.DayOfWeek.SUN);
        assertThat(menuItem.getPrices())
                .isNotNull()
                .hasSize(3)
                .contains(3.0f, 0.0f, 5.0f);
        assertThat(menuItem.getSuggestions())
                .isNotNull()
                .hasSize(2)
                .contains("suggest1_id", "suggest2_id");
        assertThat(menuItem.getCalories()).isEqualTo(321.0f);
        assertThat(menuItem.getAlcohol()).isEqualTo("Alcohol string");
        assertThat(menuItem.getAlg()).isNotNull().hasSize(1).contains(Allergen.NUTS);
        assertThat(menuItem.getSemiotics())
                .isNotNull()
                .hasSize(2)
                .contains(Semiotic.HALAL, Semiotic.VEG);
        assertThat(menuItem.getMillesime()).isEqualTo("text");
        assertThat(menuItem.getMeasure()).isEqualTo("measure text");
        assertThat(menuItem.getCountry()).isNotNull();
        assertThat(menuItem.getCountry().getName()).isEqualTo("us");
        assertThat(menuItem.getRegion()).isNotNull();
        assertThat(menuItem.getRegion().getName()).isEqualTo("Texas");
        assertThat(menuItem.getRegion().getCountry()).isEqualTo("us");
        assertThat(menuItem.getCepage()).isNotNull();
        assertThat(menuItem.getCepage().getName()).isEqualTo("Name of cepage");
        assertThat(menuItem.getCulture()).isEqualTo("Text of culture");
    }

    @Test
    public void shouldDeserializeAllergen() throws Exception {
        MenuItem menuItem;
        try (InputStream is = getClass().getResourceAsStream("/menu/menuitem_allergen.json");
             InputStreamReader reader = new InputStreamReader(is)) {
            menuItem = gson.fromJson(reader, MenuItem.class);
        }

        assertThat(menuItem).isNotNull();
        assertThat(menuItem.getAlg())
                .isNotNull()
                .hasSize(Allergen.values().length + 1)
                .contains(null,
                        Allergen.NUTS,
                        Allergen.CELERY,
                        Allergen.MILK,
                        Allergen.MUSTARD,
                        Allergen.SO2,
                        Allergen.OLIVEOIL,
                        Allergen.EGGS,
                        Allergen.SESAME,
                        Allergen.FISH,
                        Allergen.PEANUTS,
                        Allergen.GLUTEN,
                        Allergen.CRUSTACEANS,
                        Allergen.SOYA);

    }

    @Test
    public void shouldDeserializeSemiotic() throws Exception {
        MenuItem menuItem;
        try (InputStream is = getClass().getResourceAsStream("/menu/menuitem_semiotic.json");
             InputStreamReader reader = new InputStreamReader(is)) {
            menuItem = gson.fromJson(reader, MenuItem.class);
        }

        assertThat(menuItem).isNotNull();
        assertThat(menuItem.getSemiotics())
                .isNotNull()
                .hasSize(Semiotic.values().length + 1)
                .contains(
                        Semiotic.HALAL,
                        Semiotic.KOSHER,
                        Semiotic.VEG,
                        Semiotic.VEGAN,
                        Semiotic.BIO,
                        Semiotic.FAVORIS,
                        null);
    }
}