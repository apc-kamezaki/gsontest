package com.bookvideo.library.domain;

import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

public class Dashboard {
    @SerializedName("restId")
    private String restaurantId;
    private String title;
    @SerializedName("desc")
    private String description;
    private Type type;
    private List<DashboardItem> children;

    public String getRestaurantId() {
        return restaurantId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
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
        private boolean showArrow;
        private List<Duration> durations;
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

        public boolean isShowArrow() {
            return showArrow;
        }

        public List<Duration> getDurations() {
            return durations != null ? Collections.unmodifiableList(durations) : Collections.<Duration>emptyList();
        }

        public Link getLink() {
            return link;
        }

    }

    public enum Type {
        LINE, BLOCK, GRID, COL
    }
}
