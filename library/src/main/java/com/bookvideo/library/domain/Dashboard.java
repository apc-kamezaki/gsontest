package com.bookvideo.library.domain;

import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

public class Dashboard {
    @SerializedName("restId")
    private String restaurantId;
    private Type type;
    private List<DashboardItem> children;

    public String getRestaurantId() {
        return restaurantId;
    }

    public Type getType() {
        return type;
    }

    public List<DashboardItem> getChildren() {
        return children != null ? Collections.unmodifiableList(children) : Collections.<DashboardItem>emptyList();
    }

    public static class DashboardItem {
        private int order;
        @SerializedName("icon")
        private String iconId;
        private Style style;
        private int arrow;
        private String text;
        private Duration duration;
        private Link link;

        public int getOrder() {
            return order;
        }

        public String getIconId() {
            return iconId;
        }

        public Style getStyle() {
            return style;
        }

        public int getArrow() {
            return arrow;
        }

        public String getText() {
            return text;
        }

        public Duration getDuration() {
            return duration;
        }

        public Link getLink() {
            return link;
        }

    }

    public enum Type {
        LINE, BLOCK, GRID, COL
    }
}
