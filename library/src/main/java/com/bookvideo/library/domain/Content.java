package com.bookvideo.library.domain;

import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

public class Content {
    @SerializedName("restId")
    private String restaurantId;
    private String title;
    @SerializedName("desc")
    private String description;
    private ContentType type;
    private Style style;
    private boolean showPageTitle;
    private List<ContentPage> pages;

    public String getRestaurantId() {
        return restaurantId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public ContentType getType() {
        return type;
    }

    public Style getStyle() {
        return style;
    }

    public boolean isShowPageTitle() {
        return showPageTitle;
    }

    public List<ContentPage> getPages() {
        return pages != null ? Collections.unmodifiableList(pages) : Collections.<ContentPage>emptyList();
    }

    public static class ContentPage {
        private String title;
        private int order;
        private List<Duration> events;
        List<ContentItem> children;

        public String getTitle() {
            return title;
        }

        public int getOrder() {
            return order;
        }

        public List<Duration> getEvents() {
            return events != null ? Collections.unmodifiableList(events) : Collections.<Duration>emptyList();
        }

        public List<ContentItem> getChildren() {
            return children != null ? Collections.unmodifiableList(children) : Collections.<ContentItem>emptyList();
        }
    }

    public static class ContentItem {
        private ContentItemType type;
        private int order;
        private String text;
        private Style style;
        private boolean showArrow;

        public ContentItemType getType() {
            return type;
        }

        public int getOrder() {
            return order;
        }

        public String getText() {
            return text;
        }

        public Style getStyle() {
            return style;
        }

        public boolean isShowArrow() {
            return showArrow;
        }
    }

    public enum ContentType {
        CONTENT, AGENDA, GALLERY
    }

    public enum ContentItemType {
        LINE, TEXT, TITLE, IMAGE, SPACE, MAP, CONTACT, SERVICE
    }
}
