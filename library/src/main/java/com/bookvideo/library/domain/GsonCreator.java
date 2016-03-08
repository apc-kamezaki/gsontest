package com.bookvideo.library.domain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonCreator {
    private static GsonCreator instance = new GsonCreator();
    private GsonBuilder gsonBuilder = new GsonBuilder();

    private GsonCreator() {
        new AllergenConverterAdapter().apply(gsonBuilder);
        new ContentConverterAdapter().apply(gsonBuilder);
        new DashboardConverterAdapter().apply(gsonBuilder);
        new DocumentTypeConverterAdapter().apply(gsonBuilder);
        new DurationConverterAdapter().apply(gsonBuilder);
        new ImageSizeConverterAdapter().apply(gsonBuilder);
        new LinkConverterAdapter().apply(gsonBuilder);
        new MediaConverterAdapter().apply(gsonBuilder);
        new MenuCategoryConverterAdapter().apply(gsonBuilder);
        new MenuItemConverterAdapter().apply(gsonBuilder);
        new MenuConverterAdapter().apply(gsonBuilder);
        new RestaurantConverterAdapter().apply(gsonBuilder);
        new SemioticConverterAdapter().apply(gsonBuilder);
        new StyleConverterAdapter().apply(gsonBuilder);
        new TranslationConverterAdapter().apply(gsonBuilder);
        new WelcomeConverterAdapter().apply(gsonBuilder);
    }

    public static GsonCreator getInstance() {
        return instance;
    }

    public Gson createGson() {
        return gsonBuilder.create();
    }

}
