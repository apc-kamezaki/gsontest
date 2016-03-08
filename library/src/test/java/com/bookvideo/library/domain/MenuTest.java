package com.bookvideo.library.domain;

import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

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

        assertThat(menu, is(notNullValue()));
        assertThat(menu.getRestaurantId(), is("menu_id"));
        {
            // style
            Style style = menu.getStyle();
            assertThat(style, is(notNullValue()));
            assertThat(style.getLabel().getBackgroundColor(), is(2));
            assertThat(style.getLabel().getColor(), is(3));
            assertThat(style.getLabel().getAlign(), is(Style.Align.CENTER));
            assertThat(style.getLabel().getStyle(), is(Style.LabelStyle.HEIGHT));
        }
        assertThat(menu.getPages().size(), is(2));
        {
            // page1
            Menu.Page page = menu.getPages().get(0);
            assertThat(page.getTitle(), is("page1"));
            assertThat(page.getOrder(), is(10));
            {
                // categories
                assertThat(page.getCategories().size(), is(1));
                {
                    // item 1
                    Menu.Item categoryItem = page.getCategories().get(0);
                    assertThat(categoryItem, is(notNullValue()));
                    assertThat(categoryItem.getCategoryId(), is("category 1-1"));
                    assertThat(categoryItem.getOrder(), is(1));

                }
            }
        }
        {
            // page 2
            Menu.Page page = menu.getPages().get(1);
            assertThat(page.getTitle(), is("page2"));
            assertThat(page.getOrder(), is(20));
            {
                // categories
                assertThat(page.getCategories().size(), is(2));

                {
                    // item 1
                    Menu.Item categoryItem = page.getCategories().get(0);
                    assertThat(categoryItem.getCategoryId(), is("category 2-1"));
                    assertThat(categoryItem.getOrder(), is(2));
                }
                {
                    // item 2
                    Menu.Item categoryItem = page.getCategories().get(1);
                    assertThat(categoryItem.getCategoryId(), is("category 2-2"));
                    assertThat(categoryItem.getOrder(), is(3));
                }
            }
        }

    }
}