package com.bookvideo.library.domain;

import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

public class MenuItem {
    public static final InfoType defaultInfoType = InfoType.NONE;

    @SerializedName("restId")
    private String restaurantId;
    private String categoryId;
    private Style style;
    private String title;
    @SerializedName("desc")
    private String description;
    private Info info;
    private List<Duration> durations;
    private List<Float> prices;
    private List<String> suggestions;
    private List<String> ingredients;
    private float calories;
    private float alcohol;
    private List<Allergen> alg;
    @SerializedName("semiotic")
    private List<Semiotic> semiotics;
    private String millesime;
    private String measure;
    private Country country;
    private Region region;
    private Cepage cepage;
    private String culture;

    public String getRestaurantId() {
        return restaurantId;
    }

    public String getCategoryId() {
        return categoryId;
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

    public Info getInfo() {
        return info;
    }

    public List<Duration> getDurations() {
        return durations != null
                ? Collections.unmodifiableList(durations) : Collections.<Duration>emptyList();
    }

    public List<Float> getPrices() {
        return prices != null ? Collections.unmodifiableList(prices) : Collections.<Float>emptyList();
    }

    public List<String> getSuggestions() {
        return suggestions != null
                ? Collections.unmodifiableList(suggestions) : Collections.<String>emptyList();
    }

    public List<String> getIngredients() {
        return ingredients != null
                ? Collections.unmodifiableList(ingredients) : Collections.<String>emptyList();
    }

    public float getCalories() {
        return calories;
    }

    public float getAlcohol() {
        return alcohol;
    }

    public List<Allergen> getAlg() {
        return alg != null ?  Collections.unmodifiableList(alg) : Collections.<Allergen>emptyList();
    }

    public List<Semiotic> getSemiotics() {
        return semiotics != null
                ? Collections.unmodifiableList(semiotics) : Collections.<Semiotic>emptyList();
    }

    public String getMillesime() {
        return millesime;
    }

    public String getMeasure() {
        return measure;
    }

    public Country getCountry() {
        return country;
    }

    public Region getRegion() {
        return region;
    }

    public Cepage getCepage() {
        return cepage;
    }

    public String getCulture() {
        return culture;
    }

    public static class Info {
        private InfoType type;
        private ImageSize size;
        @SerializedName("desc")
        private String description;

        public InfoType getType() {
            return type;
        }

        public ImageSize getSize() {
            return size;
        }

        public String getDescription() {
            return description;
        }
    }

    public enum InfoType {
        NONE, INPAGE, FULL
    }
}
