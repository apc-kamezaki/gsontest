package com.bookvideo.library.domain;

import com.google.gson.GsonBuilder;

public interface DomainDeserializer {
    void apply(GsonBuilder gsonBuilder);
}
