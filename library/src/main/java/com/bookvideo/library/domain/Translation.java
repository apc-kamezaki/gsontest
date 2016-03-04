package com.bookvideo.library.domain;

import java.util.Collections;
import java.util.List;

public class Translation {
    private String restaurantId;
    private String locale;
    private DocumentType type;
    private List<TranslationItem> translation;

    public String getRestaurantId() {
        return restaurantId;
    }

    public String getLocale() {
        return locale;
    }

    public DocumentType getType() {
        return type;
    }

    public List<TranslationItem> getTranslation() {
        return translation != null
                ? Collections.unmodifiableList(translation) : Collections.<TranslationItem>emptyList();
    }

    public static class TranslationItem {
        private String key;
        private String value;

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }
    }
}
