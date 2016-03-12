package com.bookvideo.library.domain;

import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;

import static org.assertj.core.api.Assertions.assertThat;

public class MenuTest {
    private Gson gson;

    @Before
    public void setUp() throws Exception {
        gson = GsonCreator.getInstance().createGson();
    }

    @Test
    public void shouldDeserializeJson() throws Exception {
        Menu menu;
        try (InputStream is = getClass().getResourceAsStream("/menu/menu_simple.json");
             InputStreamReader reader = new InputStreamReader(is)) {
            menu = gson.fromJson(reader, Menu.class);
        }

        assertThat(menu).isNotNull();
        assertThat(menu.getRestaurantId()).isEqualTo("menu_id");
        {
            // style
            Style style = menu.getStyle();
            assertThat(style).isNotNull();
            assertThat(style.getLabel()).isNotNull();
            assertThat(style.getLabel().getBackgroundColor()).isEqualTo(2);
            assertThat(style.getLabel().getColor()).isEqualTo(3);
            assertThat(style.getLabel().getAlign()).isEqualTo(Style.Align.CENTER);
            assertThat(style.getLabel().getStyle()).isEqualTo(Style.LabelStyle.HEIGHT);
        }
        assertThat(menu.getPages()).isNotNull().hasSize(2);
        {
            // page1
            Menu.Page page = menu.getPages().get(0);
            assertThat(page.getTitle()).isEqualTo("page1");
            assertThat(page.getOrder()).isEqualTo(10);
            // duration
            assertThat(page.getDurations()).isNotNull().hasSize(2);
            {
                // categories
                assertThat(page.getCategories()).isNotNull().hasSize(1);
                {
                    // item 1
                    Menu.Item categoryItem = page.getCategories().get(0);
                    assertThat(categoryItem).isNotNull();
                    assertThat(categoryItem.getCategoryId()).isEqualTo("category 1-1");
                    assertThat(categoryItem.getOrder()).isEqualTo(1);

                }
            }
        }
        {
            // page 2
            Menu.Page page = menu.getPages().get(1);
            assertThat(page.getTitle()).isEqualTo("page2");
            assertThat(page.getOrder()).isEqualTo(20);
            // duration
            assertThat(page.getDurations()).isNotNull().hasSize(0);
            {
                // categories
                assertThat(page.getCategories()).isNotNull().hasSize(2);

                {
                    // item 1
                    Menu.Item categoryItem = page.getCategories().get(0);
                    assertThat(categoryItem.getCategoryId()).isEqualTo("category 2-1");
                    assertThat(categoryItem.getOrder()).isEqualTo(2);
                }
                {
                    // item 2
                    Menu.Item categoryItem = page.getCategories().get(1);
                    assertThat(categoryItem.getCategoryId()).isEqualTo("category 2-2");
                    assertThat(categoryItem.getOrder()).isEqualTo(3);
                }
            }
        }

    }
}