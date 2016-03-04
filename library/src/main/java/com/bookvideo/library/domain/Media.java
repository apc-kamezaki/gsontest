package com.bookvideo.library.domain;

import com.google.gson.annotations.SerializedName;

public class Media {
    @SerializedName("restId")
    private String restaurantId;
    private String type;
    private String name;

    public String getRestaurantId() {
        return restaurantId;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
