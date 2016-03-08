package com.bookvideo.library.domain;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class RestaurantTest {
    private Gson gson;

    @Before
    public void setUp() throws Exception {
        gson = GsonCreator.getInstance().createGson();
    }

    @Test
    public void shouldDeserializeJson() throws Exception {
        Restaurant restaurant;
        try (InputStream is = getClass().getResourceAsStream("/restaurant/restaurant_simple.json");
             InputStreamReader reader = new InputStreamReader(is)) {
            restaurant = gson.fromJson(reader, Restaurant.class);
        }

        assertThat(restaurant, is(notNullValue()));
        assertThat(restaurant.getName(), is("Restaurant ZERO"));
        assertThat(restaurant.getUrl(), is("http://www.zero.example.com"));
        assertThat(restaurant.getAddress(), is("TOKYO, BERLIN"));
        assertThat(restaurant.getPostCode(), is("xxx-000"));
        assertThat(restaurant.getPhoneNumber(), is("000-123-456"));
        assertThat(restaurant.getEmail(), is("info@zero.example.com"));
        assertThat(restaurant.getGeo(), is("Geo string"));
        assertThat(restaurant.getCurrency(), is("USD"));
        assertThat(restaurant.getServices(), hasSize(2));
        {
            // service 1
            Duration duration = restaurant.getServices().get(0);
            assertThat(duration.getTimeslot(), is("Timeslot string 1"));
            assertThat(duration.getDays(), hasSize(2));
            assertThat(duration.getDays(), contains(Duration.DayOfWeek.MON, Duration.DayOfWeek.FRI));
        }
        {
            // service 2
            Duration duration = restaurant.getServices().get(1);
            assertThat(duration.getTimeslot(), is("Timeslot string 2"));
            assertThat(duration.getDays(), hasSize(0));
        }
        assertThat(restaurant.getHeaderStyle(), is(notNullValue()));
        assertThat(restaurant.getHeaderStyle().getBackLabel(), is("BACK"));
        assertThat(restaurant.getLogoStyle(), is(notNullValue()));
        assertThat(restaurant.getLogoStyle().getType(), is(Restaurant.LogoStyleType.ICON));
        assertThat(restaurant.getThemeColor(), is(100));
        assertThat(restaurant.isUseBasket(), is(true));
        assertThat(restaurant.isShowPrice(), is(false));
        assertThat(restaurant.getEntryPoint(), is(notNullValue()));
        assertThat(restaurant.getEntryPoint().getTarget(), is("target0"));
        assertThat(restaurant.getStyle(), is(notNullValue()));
        assertThat(restaurant.getStyle().getFont().getName(), is("font_name"));
        assertThat(restaurant.getSideMenu(), hasSize(2));
        {
            // side menu 1
            Restaurant.SideMenu sideMenu = restaurant.getSideMenu().get(0);
            assertThat(sideMenu.getOrder(), is(1));
            assertThat(sideMenu.getLink(), is(notNullValue()));
            assertThat(sideMenu.getLink().getType(), is(DocumentType.MENU));
        }
        {
            // side menu 2
            Restaurant.SideMenu sideMenu = restaurant.getSideMenu().get(1);
            assertThat(sideMenu.getOrder(), is(3));
            assertThat(sideMenu.getLink(), is(notNullValue()));
            assertThat(sideMenu.getLink().getType(), is(DocumentType.DASHBOARD));

        }
    }

    @Test
    public void shouldDeserializeHeaderStyle() throws Exception {
        Restaurant.HeaderStyle headerStyle;
        try (InputStream is = getClass().getResourceAsStream("/restaurant/restaurant_headerstyle.json");
             InputStreamReader reader = new InputStreamReader(is)) {
            headerStyle = gson.fromJson(reader, Restaurant.HeaderStyle.class);
        }

        assertThat(headerStyle, is(notNullValue()));
        assertThat(headerStyle.getImageId(), is("Header image id"));
        assertThat(headerStyle.getColor(), is(10));
        assertThat(headerStyle.getBackgroundColor(), is(20));
        assertThat(headerStyle.getBackLabel(), is("BACK"));
        assertThat(headerStyle.getHomeLabel(), is("HOME"));
    }

    @Test
    public void shouldDeserializeLogoStyle() throws Exception {
        Restaurant.LogoStyle logoStyle;
        try (InputStream is = getClass().getResourceAsStream("/restaurant/restaurant_logostyle.json");
             InputStreamReader reader = new InputStreamReader(is)) {
            logoStyle = gson.fromJson(reader, Restaurant.LogoStyle.class);
        }

        assertThat(logoStyle, is(notNullValue()));
        assertThat(logoStyle.getImageId(), is("logo image id"));
        assertThat(logoStyle.getBackgroundColor(), is(10));
        assertThat(logoStyle.getType(), is(Restaurant.LogoStyleType.WIDE));
    }

    @Test
    public void shouldDeserializeLogStyleType() throws Exception {
        List<Restaurant.LogoStyleType> list;
        try (InputStream is = getClass().getResourceAsStream("/restaurant/restaurant_logostyletype.json");
             InputStreamReader reader = new InputStreamReader(is)) {
            Type listType = new TypeToken<List<Restaurant.LogoStyleType>>(){}.getType();
            list = gson.fromJson(reader, listType);
        }

        assertThat(list, hasSize(3));
        assertThat(list, contains(Restaurant.LogoStyleType.ICON, Restaurant.defaultLogoStyleType, Restaurant.LogoStyleType.WIDE));

    }
}