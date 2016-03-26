package com.bookvideo.library.domain;

import com.google.gson.annotations.SerializedName;

public class Welcome {
    @SerializedName("restId")
    private String restaurantId;
    private String title;
    private Style style;
    private Ads ads;
    private Link link;

    public String getRestaurantId() {
        return restaurantId;
    }

    public String getTitle() {
        return title;
    }

    public Style getStyle() {
        return style;
    }

    public Ads getAds() {
        return ads;
    }

    public Link getLink() {
        return link;
    }

    public static class Ads {
        private String catchPhrase;
        private Link link;

        public String getCatchPhrase() {
            return catchPhrase;
        }

        public Link getLink() {
            return link;
        }
    }
}
