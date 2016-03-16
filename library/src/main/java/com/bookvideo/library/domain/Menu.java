package com.bookvideo.library.domain;

import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

public class Menu {
    @SerializedName("restId")
    private String restaurantId;
    private String title;
    private Style style;
    private List<Page> pages;

    public String getRestaurantId() {
        return restaurantId;
    }

    public String getTitle() {
        return title;
    }

    public Style getStyle() {
        return style;
    }

    public List<Page> getPages() {
        return pages != null ? Collections.unmodifiableList(pages) : Collections.<Page>emptyList();
    }

    public static class Page {
        private String title;
        private int order;
        private List<Duration> durations;
        private List<Item> categories;

        public String getTitle() {
            return title;
        }

        public int getOrder() {
            return order;
        }

        public List<Duration> getDurations() {
            return durations != null ? Collections.unmodifiableList(durations) : Collections.<Duration>emptyList();
        }

        public List<Item> getCategories() {
            return categories != null ? Collections.unmodifiableList(categories) : Collections.<Item>emptyList();
        }
    }

    public static class Item {
        @SerializedName("id")
        private String categoryId;
        private int order;

        public String getCategoryId() {
            return categoryId;
        }

        public int getOrder() {
            return order;
        }
    }
}
