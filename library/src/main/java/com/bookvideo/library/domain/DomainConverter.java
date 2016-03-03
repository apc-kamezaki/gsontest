package com.bookvideo.library.domain;

import com.google.gson.GsonBuilder;

public interface DomainConverter {
    void apply(GsonBuilder gsonBuilder);
}
