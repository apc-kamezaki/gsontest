package com.bookvideo.library.domain;

import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

public class Welcome {
    @SerializedName("restId")
    private String restaurantId;
    private Style style;
    @SerializedName("lang")
    private List<String> languages;
    private Ads ads;
    private Link link;

    public String getRestaurantId() {
        return restaurantId;
    }

    public Style getStyle() {
        return style;
    }

    public List<String> getLanguages() {
        return languages != null ? Collections.unmodifiableList(languages) : Collections.<String>emptyList();
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
