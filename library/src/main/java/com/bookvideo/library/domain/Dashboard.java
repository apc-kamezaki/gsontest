package com.bookvideo.library.domain;

import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

public class Dashboard {
    @SerializedName("restId")
    private String restrauntId;
    private Type type;
    private List<DashboardItem> children;

    public String getRestrauntId() {
        return restrauntId;
    }

    public Type getType() {
        return type;
    }

    public List<DashboardItem> getChildren() {
        return Collections.unmodifiableList(children);
    }

    public static class DashboardItem {
        private int order;
        @SerializedName("icon")
        private String iconId;
        private Style style;
        private int arrow;
        private String text;
        // TODO duration
        // TODO link

        public DashboardItem() {

        }

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
    }

    public enum Type {
        LINE, BLOCK, GRID, COL
    }
}
