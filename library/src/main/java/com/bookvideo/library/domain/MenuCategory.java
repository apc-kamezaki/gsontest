package com.bookvideo.library.domain;

import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

public class MenuCategory {
    public static final CategoryType defaultType = CategoryType.MAIN;

    @SerializedName("restId")
    private String restaurantId;
    private Style style;
    private String title;
    @SerializedName("desc")
    private String description;
    private CategoryType type;
    private List<String> typeCols;
    private List<Item> items;

    public String getRestaurantId() {
        return restaurantId;
    }

    public Style getStyle() {
        return style;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public CategoryType getType() {
        return type;
    }

    public List<String> getTypeCols() {
        return typeCols != null ? Collections.unmodifiableList(typeCols) : Collections.<String>emptyList();
    }

    public List<Item> getItems() {
        return items != null ? Collections.unmodifiableList(items) : Collections.<Item>emptyList();
    }

    public static class Item {
        @SerializedName("id")
        private String itemId;
        private int order;

        public String getItemId() {
            return itemId;
        }

        public int getOrder() {
            return order;
        }
    }
    public enum CategoryType {
        MAIN, WINE, ALCOHOL
    }
}
