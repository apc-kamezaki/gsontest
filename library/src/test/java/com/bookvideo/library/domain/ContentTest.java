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

public class ContentTest {
    private Gson gson;

    @Before
    public void setUp() throws Exception {
        gson = GsonCreator.getInstance().createGson();
    }

    @Test
    public void shouldDeserializeJson() throws Exception {
        Content content;
        try (InputStream is = getClass().getResourceAsStream("/content/content_simple.json");
             InputStreamReader reader = new InputStreamReader(is)) {
            content = gson.fromJson(reader, Content.class);
        }

        assertThat(content).isNotNull();
        assertThat(content.getRestaurantId()).isEqualTo("content_restaurant_id");
        assertThat(content.getTitle()).isEqualTo("title1");
        assertThat(content.getType()).isEqualTo(Content.ContentType.CONTENT);
        {
            Style style = content.getStyle();
            assertThat(style).isNotNull();
            assertThat(style.getBackground()).isNotNull();
            assertThat(style.getBackground().getType()).isEqualTo(Style.Type.WIDE);
            assertThat(style.getPadding()).isNotNull();
            assertThat(style.getPadding().getHorizontalPadding()).isEqualTo(10);
        }
        assertThat(content.getPages()).isNotNull().hasSize(2);

        {
            // page 1
            Content.ContentPage page = content.getPages().get(0);
            assertThat(page.getTitle()).isEqualTo("page1");
            assertThat(page.getOrder()).isEqualTo(10);
            assertThat(page.getDurations()).isNotNull().hasSize(2);
            assertThat(page.getChildren()).isNotNull().hasSize(1);

            {
                // child 1

                Content.ContentItem item = page.getChildren().get(0);
                assertThat(item.getType()).isEqualTo(Content.ContentItemType.TEXT);
                assertThat(item.getOrder()).isEqualTo(100);
                assertThat(item.getText()).isEqualTo("item 1-1");
                assertThat(item.getStyle()).isNotNull();
                assertThat(item.getStyle().getFont()).isNotNull();
                assertThat(item.getStyle().getFont().getName()).isEqualTo("font_name");
                assertThat(item.getStyle().getFont().getAlign())
                        .isNotNull()
                        .isEqualTo(Style.Align.RIGHT);
            }
        }

        {
            // page2
            Content.ContentPage page = content.getPages().get(1);
            assertThat(page.getTitle()).isEqualTo("page2");
            assertThat(page.getDurations()).isNotNull().hasSize(0);
            assertThat(page.getOrder()).isEqualTo(20);

            {
                // child 1
                Content.ContentItem item = page.getChildren().get(0);
                assertThat(item.getType()).isEqualTo(Content.ContentItemType.IMAGE);
                assertThat(item.getOrder()).isEqualTo(200);
                assertThat(item.getText()).isEqualTo("item 2-1");
                assertThat(item.getStyle()).isNotNull();
                assertThat(item.getStyle().getBackground()).isNotNull();
                assertThat(item.getStyle().getBackground().getImageId())
                        .isNotNull()
                        .isEqualTo("bg_id");
                assertThat(item.getStyle().getBackground().getType())
                        .isNotNull()
                        .isEqualTo(Style.Type.CROP);
            }

            {
                // child 2
                Content.ContentItem item = page.getChildren().get(1);
                assertThat(item.getType()).isEqualTo(Content.ContentItemType.CONTACT);
                assertThat(item.getOrder()).isEqualTo(210);
                assertThat(item.getText()).isEqualTo("item 2-2");
                assertThat(item.getStyle()).isNotNull();
                assertThat(item.getStyle().getFont()).isNotNull();
                assertThat(item.getStyle().getFont().getName()).isEqualTo("font_name");
            }
        }
    }

    @Test
    public void shouldDeserializeContentType() throws Exception {
        List<Content.ContentType> contentTypes;
        try (InputStream is = getClass().getResourceAsStream("/content/content_contenttype.json");
             InputStreamReader reader = new InputStreamReader(is)) {
            Type listType = new TypeToken<List<Content.ContentType>>(){}.getType();
            contentTypes = gson.fromJson(reader, listType);
        }

        assertThat(contentTypes)
                .isNotNull()
                .hasSize(4)
                .contains(
                        Content.ContentType.CONTENT,
                        Content.ContentType.AGENDA,
                        Content.ContentType.GALLERY,
                        null);
    }

    @Test
    public void shouldDeserializeContentItemType() throws Exception {
        List<Content.ContentItemType> itemTypes;
        try (InputStream is = getClass().getResourceAsStream("/content/content_contentitemtype.json");
             InputStreamReader reader = new InputStreamReader(is)) {
            Type listType = new TypeToken<List<Content.ContentItemType>>(){}.getType();
            itemTypes = gson.fromJson(reader, listType);
        }

        assertThat(itemTypes)
                .isNotNull()
                .hasSize(9)
                .contains(null,
                        Content.ContentItemType.LINE,
                        Content.ContentItemType.TEXT,
                        Content.ContentItemType.TITLE,
                        Content.ContentItemType.IMAGE,
                        Content.ContentItemType.SPACE,
                        Content.ContentItemType.MAP,
                        Content.ContentItemType.CONTACT,
                        Content.ContentItemType.SERVICE);
    }
}