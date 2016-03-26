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

public class DashboardTest {
    private Gson gson;

    @Before
    public void setUp() throws Exception {
        gson = GsonCreator.getInstance().createGson();
    }

    @Test
    public void shouldDeserializeJson() throws Exception {
        Dashboard dashboard;
        try (InputStream is = getClass().getResourceAsStream("/dashboard/dashboard_simple.json");
             InputStreamReader reader = new InputStreamReader(is)) {
            dashboard = gson.fromJson(reader, Dashboard.class);
        }

        assertThat(dashboard).isNotNull();
        assertThat(dashboard.getRestaurantId()).isEqualTo("dashboard_id");
        assertThat(dashboard.getTitle()).isEqualTo("dashboard title");
        assertThat(dashboard.getDescription()).isEqualTo("dashboard description");
        assertThat(dashboard.getType()).isEqualTo(Dashboard.Type.GRID);

        assertThat(dashboard.getChildren()).isNotNull().hasSize(2);

        // child 1
        {
            Dashboard.DashboardItem item = dashboard.getChildren().get(0);

            assertThat(item.getOrder()).isEqualTo(0);
            assertThat(item.getIconId()).isEqualTo("icon-id-0");
            assertThat(item.isShowArrow()).isFalse();

            // style
            Style style = item.getStyle();
            assertThat(style).isNotNull();
            assertThat(style.getBackground()).isNotNull();
            assertThat(style.getBackground().getColor()).isEqualTo(0);
            assertThat(style.getFont()).isNotNull();
            assertThat(style.getFont().getName()).isEqualTo("font0");
            assertThat(style.getFont().getSize()).isEqualTo(10);

            // duration
            assertThat(item.getDurations()).isNotNull().hasSize(0);

            // link
            Link link = item.getLink();
            assertThat(link).isNotNull();
            assertThat(link.getType()).isEqualTo(DocumentType.DASHBOARD);
            assertThat(link.getTarget()).isEqualTo("target0");
            assertThat(link.getData()).isEqualTo("data0");

            assertThat(style.getBackground()).isNotNull();
            assertThat(style.getBackground().getType()).isEqualTo(Style.defaultType);
            assertThat(style.getLabel()).isNull();
            assertThat(style.getFont()).isNotNull();
            assertThat(style.getFont().getAlign()).isEqualTo(Style.defaultAlign);
        }

        // child 2
        {
            Dashboard.DashboardItem item = dashboard.getChildren().get(1);

            assertThat(item.getOrder()).isEqualTo(1);
            assertThat(item.getIconId()).isNull();
            assertThat(item.isShowArrow()).isTrue();

            Style style = item.getStyle();
            assertThat(style).isNotNull();
            assertThat(style.getBackground()).isNull();
            assertThat(style.getLabel()).isNull();
            assertThat(style.getPadding()).isNull();
            assertThat(style.getFont()).isNull();;

            // duration
            assertThat(item.getDurations()).isNotNull().hasSize(1);

            // link
            Link link = item.getLink();
            assertThat(link).isNotNull();
            assertThat(link.getType()).isEqualTo(DocumentType.CONTENT);
            assertThat(link.getTarget()).isEqualTo("target1");
            assertThat(link.getData()).isNull();
        }
    }

    @Test
    public void shouldDeserializeDashboardType() throws Exception {
        List<Dashboard.Type> types;
        try (InputStream is = getClass().getResourceAsStream("/dashboard/dashboard_type.json");
             InputStreamReader reader = new InputStreamReader(is)) {
            Type listType = new TypeToken<List<Dashboard.Type>>(){}.getType();
            types = gson.fromJson(reader, listType);
        }

        assertThat(types)
                .isNotNull()
                .hasSize(5)
                .contains(
                        Dashboard.Type.COL,
                        Dashboard.Type.GRID,
                        Dashboard.Type.BLOCK,
                        Dashboard.Type.LINE,
                        null);
    }
}