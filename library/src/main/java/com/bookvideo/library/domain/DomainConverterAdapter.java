package com.bookvideo.library.domain;

import com.google.gson.GsonBuilder;

public interface DomainConverterAdapter {
    void apply(GsonBuilder gsonBuilder);
}
