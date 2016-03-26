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

        assertThat(restaurant).isNotNull();
        assertThat(restaurant.getName()).isEqualTo("Restaurant ZERO");
        assertThat(restaurant.getUrl()).isEqualTo("http://www.zero.example.com");
        assertThat(restaurant.getAddress()).isEqualTo("TOKYO, BERLIN");
        assertThat(restaurant.getPostCode()).isEqualTo("xxx-000");
        assertThat(restaurant.getPhoneNumber()).isEqualTo("000-123-456");
        assertThat(restaurant.getEmail()).isEqualTo("info@zero.example.com");
        assertThat(restaurant.getLocation()).isNotNull();
        assertThat(restaurant.getLocation().getName()).isEqualTo("location name");
        assertThat(restaurant.getCurrency()).isEqualTo("USD");
        assertThat(restaurant.getLanguages()).isNotNull().hasSize(2).contains("en", "fr");
        assertThat(restaurant.getServices()).isNotNull().hasSize(2);
        {
            // service 1
            Duration duration = restaurant.getServices().get(0);
            assertThat(duration.getTimeslot()).isEqualTo(Duration.TimeSlot.MORNING);
            assertThat(duration.getDays())
                    .isNotNull()
                    .hasSize(2)
                    .contains(Duration.DayOfWeek.MON, Duration.DayOfWeek.FRI);
        }
        {
            // service 2
            Duration duration = restaurant.getServices().get(1);
            assertThat(duration.getTimeslot()).isEqualTo(Duration.TimeSlot.LUNCH);
            assertThat(duration.getDays()).isNotNull().hasSize(0);
        }
        assertThat(restaurant.getHeaderStyle()).isNotNull();
        assertThat(restaurant.getHeaderStyle().getBackLabel()).isEqualTo("BACK");
        assertThat(restaurant.getLogoStyle()).isNotNull();
        assertThat(restaurant.getLogoStyle().getType()).isEqualTo(Restaurant.LogoStyleType.ICON);
        assertThat(restaurant.getThemeColor()).isEqualTo(0xffffff00);
        assertThat(restaurant.isUseDuration()).isTrue();
        assertThat(restaurant.isUseBasket()).isTrue();
        assertThat(restaurant.isShowPrice()).isFalse();
        assertThat(restaurant.getEntryPoint()).isNotNull();
        assertThat(restaurant.getEntryPoint().getTarget()).isEqualTo("target0");
        assertThat(restaurant.getStyle()).isNotNull();
        assertThat(restaurant.getStyle().getFont()).isNotNull();
        assertThat(restaurant.getStyle().getFont().getName()).isEqualTo("font_name");
        assertThat(restaurant.getSideMenu()).isNotNull().hasSize(2);
        {
            // side menu 1
            Restaurant.SideMenu sideMenu = restaurant.getSideMenu().get(0);
            assertThat(sideMenu.getOrder()).isEqualTo(1);
            assertThat(sideMenu.getLink()).isNotNull();
            assertThat(sideMenu.getLink().getType()).isEqualTo(DocumentType.MENU);
        }
        {
            // side menu 2
            Restaurant.SideMenu sideMenu = restaurant.getSideMenu().get(1);
            assertThat(sideMenu.getOrder()).isEqualTo(3);
            assertThat(sideMenu.getLink()).isNotNull();
            assertThat(sideMenu.getLink().getType()).isEqualTo(DocumentType.DASHBOARD);

        }
    }

    @Test
    public void shouldDeserializeHeaderStyle() throws Exception {
        Restaurant.HeaderStyle headerStyle;
        try (InputStream is = getClass().getResourceAsStream("/restaurant/restaurant_headerstyle.json");
             InputStreamReader reader = new InputStreamReader(is)) {
            headerStyle = gson.fromJson(reader, Restaurant.HeaderStyle.class);
        }

        assertThat(headerStyle).isNotNull();
        assertThat(headerStyle.getImageId()).isEqualTo("Header image id");
        assertThat(headerStyle.getColor()).isEqualTo(0xffff0001);
        assertThat(headerStyle.getBackgroundColor()).isEqualTo(0xffffffff);
        assertThat(headerStyle.getBackLabel()).isEqualTo("BACK");
        assertThat(headerStyle.getHomeLabel()).isEqualTo("HOME");
    }

    @Test
    public void shouldDeserializeLogoStyle() throws Exception {
        Restaurant.LogoStyle logoStyle;
        try (InputStream is = getClass().getResourceAsStream("/restaurant/restaurant_logostyle.json");
             InputStreamReader reader = new InputStreamReader(is)) {
            logoStyle = gson.fromJson(reader, Restaurant.LogoStyle.class);
        }

        assertThat(logoStyle).isNotNull();
        assertThat(logoStyle.getImageId()).isEqualTo("logo image id");
        assertThat(logoStyle.getBackgroundColor()).isEqualTo(0x7f007f7f);
        assertThat(logoStyle.getType()).isEqualTo(Restaurant.LogoStyleType.WIDE);
    }

    @Test
    public void shouldDeserializeLogStyleType() throws Exception {
        List<Restaurant.LogoStyleType> list;
        try (InputStream is = getClass().getResourceAsStream("/restaurant/restaurant_logostyletype.json");
             InputStreamReader reader = new InputStreamReader(is)) {
            Type listType = new TypeToken<List<Restaurant.LogoStyleType>>(){}.getType();
            list = gson.fromJson(reader, listType);
        }

        assertThat(list)
                .isNotNull()
                .hasSize(3)
                .contains(
                        Restaurant.LogoStyleType.ICON,
                        Restaurant.defaultLogoStyleType,
                        Restaurant.LogoStyleType.WIDE);
    }
}