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

        assertThat(category).isNotNull();
        assertThat(category.getRestaurantId()).isEqualTo("category id");
        {
            Style style = category.getStyle();
            assertThat(style).isNotNull();
            assertThat(style.getBackground()).isNotNull();
            assertThat(style.getBackground().getImageId()).isEqualTo("bg_id");
            assertThat(style.getBackground().getColor()).isEqualTo(1);
            assertThat(style.getBackground().getType()).isEqualTo(Style.Type.CROP);
        }
        assertThat(category.getTitle()).isEqualTo("Category title");
        assertThat(category.getDescription()).isEqualTo("Category description");
        assertThat(category.getType()).isEqualTo(MenuCategory.CategoryType.WINE);
        assertThat(category.getTypeCols())
                .isNotNull()
                .hasSize(3)
                .contains("col-1", "col-2", "col-3");
        {
            assertThat(category.getItems()).isNotNull().hasSize(3);
            {
                MenuCategory.Item item = category.getItems().get(0);
                assertThat(item.getItemId()).isEqualTo("item-1");
                assertThat(item.getOrder()).isEqualTo(1);
            }
            {
                MenuCategory.Item item = category.getItems().get(1);
                assertThat(item.getItemId()).isEqualTo("item-2");
                assertThat(item.getOrder()).isEqualTo(3);

            }
            {
                MenuCategory.Item item = category.getItems().get(2);
                assertThat(item.getItemId()).isEqualTo("item-3");
                assertThat(item.getOrder()).isEqualTo(2);

            }
        }
    }

    @Test
    public void shouldDeserializeCategoryType() throws Exception {
        List<MenuCategory.CategoryType> categoryTypes;
        try (InputStream is = getClass().getResourceAsStream("/menu/category_type.json");
             InputStreamReader reader = new InputStreamReader(is)) {
            Type listType = new TypeToken<List<MenuCategory.CategoryType>>(){}.getType();
            categoryTypes = gson.fromJson(reader, listType);
        }

        assertThat(categoryTypes)
                .isNotNull()
                .hasSize(4)
                .contains(
                        MenuCategory.CategoryType.MAIN,
                        MenuCategory.CategoryType.WINE,
                        MenuCategory.CategoryType.ALCOHOL,
                        null);
    }
}