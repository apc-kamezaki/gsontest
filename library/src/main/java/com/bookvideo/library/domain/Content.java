package com.bookvideo.library.domain;

import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

public class Content {
    @SerializedName("restId")
    private String restaurantId;
    private ContentType type;
    private Style style;
    private List<ContentPage> pages;

    public String getRestaurantId() {
        return restaurantId;
    }

    public ContentType getType() {
        return type;
    }

    public Style getStyle() {
        return style;
    }

    public List<ContentPage> getPages() {
        return pages != null ? Collections.unmodifiableList(pages) : Collections.<ContentPage>emptyList();
    }

    public static class ContentPage {
        private String title;
        private int order;
        private List<Duration> durations;
        List<ContentItem> children;

        public String getTitle() {
            return title;
        }

        public int getOrder() {
            return order;
        }

        public List<Duration> getDurations() {
            return durations != null ? Collections.unmodifiableList(durations) : Collections.<Duration>emptyList();
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
        private Link link;

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

        public com.bookvideo.library.domain.Link getLink() {
            return link;
        }
    }

    public enum ContentType {
        CONTENT, AGENDA, GALLERY
    }

    public enum ContentItemType {
        LINE, TEXT, TITLE, IMAGE, SPACE, MAP, CONTACT, SERVICE
    }
}
