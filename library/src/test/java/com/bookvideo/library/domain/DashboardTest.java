package com.bookvideo.library.domain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

public class DashboardTest {
    private Gson gson;

    @Before
    public void setUp() throws Exception {
        GsonBuilder gsonBuilder = new GsonBuilder();
        DashboardConverter dashboardConverter = new DashboardConverter();
        dashboardConverter.apply(gsonBuilder);
        LinkConverter linkConverter = new LinkConverter();
        linkConverter.apply(gsonBuilder);
        gson = gsonBuilder.create();

    }

    @Test
    public void shouldDeserializeJson() throws Exception {
        Dashboard dashboard;
        try (InputStream is = getClass().getResourceAsStream("/style/dashboard_simple.json");
             InputStreamReader reader = new InputStreamReader(is)) {
            dashboard = gson.fromJson(reader, Dashboard.class);
        }

        assertThat(dashboard, is(notNullValue()));
        assertThat(dashboard.getRestrauntId(), is("dashboard_id"));
        assertThat(dashboard.getType(), is(Dashboard.Type.GRID));

        assertThat(dashboard.getChildren(), is(notNullValue()));
        assertThat(dashboard.getChildren().size(), is(2));

        // child 1
        {
            Dashboard.DashboardItem item = dashboard.getChildren().get(0);

            assertThat(item.getOrder(), is(0));
            assertThat(item.getIconId(), is(equalTo("icon-id-0")));
            assertThat(item.getArrow(), is(10));
            assertThat(item.getText(), is("text0"));

            // style
            Style style = item.getStyle();
            assertThat(style, is(notNullValue()));
            assertThat(style.getBackground().getColor(), is(0));
            assertThat(style.getFont().getName(), is("font0"));
            assertThat(style.getFont().getSize(), is(10));

            // link
            Link link = item.getLink();
            assertThat(link, is(notNullValue()));
            assertThat(link.getType(), is(Link.Type.DASHBOARD));
            assertThat(link.getTarget(), is("target0"));
            assertThat(link.getData(), is("data0"));

            assertThat(style.getBackground().getType(), is(Style.defaultType));
            assertThat(style.getLabel(), is(nullValue()));
            assertThat(style.getFont().getAlign(), is(Style.defaultAlign));
        }

        // child 2
        {
            Dashboard.DashboardItem item = dashboard.getChildren().get(1);

            assertThat(item.getOrder(), is(1));
            assertThat(item.getIconId(), is(nullValue()));
            assertThat(item.getArrow(), is(20));
            assertThat(item.getText(), is("text1"));

            Style style = item.getStyle();
            assertThat(style, is(notNullValue()));
            assertThat(style.getBackground(), is(nullValue()));
            assertThat(style.getLabel(), is(nullValue()));
            assertThat(style.getPadding(), is(nullValue()));
            assertThat(style.getFont(), is(nullValue()));

            // link
            Link link = item.getLink();
            assertThat(link, is(notNullValue()));
            assertThat(link.getType(), is(Link.Type.CONTENT));
            assertThat(link.getTarget(), is("target1"));
            assertThat(link.getData(), is(nullValue()));
        }
    }
}