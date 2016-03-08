package com.bookvideo.library.domain;

import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class MenuCategoryTest {
    private Gson gson;

    @Before
    public void setUp() throws Exception {
        gson = GsonCreator.getInstance().createGson();
    }

    @Test
    public void shouldDeserializeJson() throws Exception {
        MenuCategory category;
        try (InputStream is = getClass().getResourceAsStream("/menu/category_simple.json");
             InputStreamReader reader = new InputStreamReader(is)) {
            category = gson.fromJson(reader, MenuCategory.class);
        }

        assertThat(category, is(notNullValue()));
        assertThat(category.getRestaurantId(), is("category id"));
        {
            Style style = category.getStyle();
            assertThat(style, is(notNullValue()));
            assertThat(style.getBackground().getImageId(), is("bg_id"));
            assertThat(style.getBackground().getColor(), is(1));
            assertThat(style.getBackground().getType(), is(Style.Type.CROP));
        }
        assertThat(category.getTitle(), is("Category title"));
        assertThat(category.getDescription(), is("Category description"));
        assertThat(category.getType(), is(MenuCategory.CategoryType.WINE));
        assertThat(category.getTypeCols().size(), is(3));
        assertThat(category.getTypeCols(), contains("col-1", "col-2", "col-3"));
        {
            assertThat(category.getItems().size(), is(3));
            {
                MenuCategory.Item item = category.getItems().get(0);
                assertThat(item.getItemId(), is("item-1"));
                assertThat(item.getOrder(), is(1));
            }
            {
                MenuCategory.Item item = category.getItems().get(1);
                assertThat(item.getItemId(), is("item-2"));
                assertThat(item.getOrder(), is(3));

            }
            {
                MenuCategory.Item item = category.getItems().get(2);
                assertThat(item.getItemId(), is("item-3"));
                assertThat(item.getOrder(), is(2));

            }
        }
    }
}