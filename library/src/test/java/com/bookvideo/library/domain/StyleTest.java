package com.bookvideo.library.domain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class StyleTest {
    private Gson gson;
    @Before
    public void setUp() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        StyleDeserializer styleDeserializer = new StyleDeserializer();
        styleDeserializer.apply(gsonBuilder);
        gson = gsonBuilder.create();
    }

    @Test
    public void shouldDeserializeJson() throws Exception {
        Style style;
        try (InputStream is = getClass().getResourceAsStream("/style/style_simple.json");
             InputStreamReader reader = new InputStreamReader(is)) {
            style = gson.fromJson(reader, Style.class);
        }

        assertThat(style, is(notNullValue()));

        // bg
        {
            assertThat(style.getBackground(), is(notNullValue()));
            assertThat(style.getBackground().getImageId(), is(equalTo("bg_id")));
            assertThat(style.getBackground().getColor(), is(equalTo(1)));
            assertThat(style.getBackground().getType(), is(equalTo(Style.Type.WIDE)));
        }

        // padding
        {
            assertThat(style.getPadding(), is(notNullValue()));
            assertThat(style.getPadding().getHorizontalPadding(), is(equalTo(10)));
            assertThat(style.getPadding().getVerticalPadding(), is(equalTo(20)));
        }

        // label
        {
            assertThat(style.getLabel(), is(notNullValue()));
            assertThat(style.getLabel().getBackgroundImageId(), is(equalTo("label_bg_id")));
            assertThat(style.getLabel().getBackgroundColor(), is(equalTo(2)));
            assertThat(style.getLabel().getColor(), is(equalTo(3)));
            assertThat(style.getLabel().getAlign(), is(equalTo(Style.Align.CENTER)));
            assertThat(style.getLabel().getStyle(), is(equalTo(Style.LabelStyle.HEIGHT)));
            assertThat(style.getLabel().getBorderColor(), is(equalTo(4)));
            assertThat(style.getLabel().getBorderSize(), is(equalTo(30)));
        }

        // font
        {
            assertThat(style.getFont(), is(notNullValue()));
            assertThat(style.getFont().getName(), is(equalTo("font_name")));
            assertThat(style.getFont().getSize(), is(equalTo(40)));
            assertThat(style.getFont().getColor(), is(equalTo(5)));
            assertThat(style.getFont().getAlign(), is(equalTo(Style.Align.RIGHT)));
        }
    }
}
