package com.bookvideo.library.domain;

import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;

import static org.assertj.core.api.Assertions.assertThat;

public class StyleTest {
    private Gson gson;
    @Before
    public void setUp() {
        gson = GsonCreator.getInstance().createGson();
    }

    @Test
    public void shouldDeserializeJson() throws Exception {
        Style style;
        try (InputStream is = getClass().getResourceAsStream("/style/style_simple.json");
             InputStreamReader reader = new InputStreamReader(is)) {
            style = gson.fromJson(reader, Style.class);
        }

        assertThat(style).isNotNull();

        // bg
        {
            assertThat(style.getBackground()).isNotNull();
            assertThat(style.getBackground().getImageId()).isEqualTo("bg_id");
            assertThat(style.getBackground().getImageSize()).isEqualTo(ImageSize.FULL);
            assertThat(style.getBackground().getColor()).isEqualTo(0x7f7f7f7f);
            assertThat(style.getBackground().getType()).isEqualTo(Style.Type.WIDE);
        }

        // padding
        {
            assertThat(style.getPadding()).isNotNull();;
            assertThat(style.getPadding().getHorizontalPadding()).isEqualTo(10);
            assertThat(style.getPadding().getVerticalPadding()).isEqualTo(20);
        }

        // label
        {
            assertThat(style.getLabel()).isNotNull();
            assertThat(style.getLabel().getBackgroundImageId())
                    .isNotNull()
                    .isEqualTo("label_bg_id");
            assertThat(style.getLabel().getWidth()).isEqualTo(100);
            assertThat(style.getLabel().getBackgroundColor()).isEqualTo(2);
            assertThat(style.getLabel().getColor()).isEqualTo(3);
            assertThat(style.getLabel().getAlign()).isEqualTo(Style.Align.CENTER);
            assertThat(style.getLabel().getStyle()).isEqualTo(Style.LabelStyle.HEIGHT);
            assertThat(style.getLabel().getBorderColor()).isEqualTo(4);
            assertThat(style.getLabel().getBorderSize()).isEqualTo(30);
        }

        // font
        {
            assertThat(style.getFont()).isNotNull();
            assertThat(style.getFont().getName()).isEqualTo("font_name");
            assertThat(style.getFont().getSize()).isEqualTo(40);
            assertThat(style.getFont().getColor()).isEqualTo(0xff000000);
            assertThat(style.getFont().getAlign()).isEqualTo(Style.Align.RIGHT);
            assertThat(style.getFont().getLetterSpacing()).isEqualTo(10);
        }

        // line
        {
            assertThat(style.getLine()).isNotNull();
            assertThat(style.getLine().getType()).isEqualTo(Style.LineType.DASHED);
        }
    }
}
