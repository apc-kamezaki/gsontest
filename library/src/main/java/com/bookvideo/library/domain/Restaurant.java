package com.bookvideo.library.domain;

import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

public class Restaurant {
    public static LogoStyleType defaultLogoStyleType = LogoStyleType.ICON;

    private String clientId;
    private String name;
    private String url;
    private String address;
    private String postCode;
    private String phoneNumber;
    private String email;
    @SerializedName("loc")
    private Location location;
    private String currency;
    @SerializedName("lang")
    private List<String> languages;
    private List<Duration> services;
    @SerializedName("hStyle")
    private HeaderStyle headerStyle;
    @SerializedName("lStyle")
    private LogoStyle logoStyle;
    private ColorValue themeColor;
    private boolean useDuration;
    private boolean useBasket;
    private boolean showPrice;
    private Link entryPoint;
    private Style style;
    private List<SideMenu> sideMenu;

    public String getClientId() {
        return clientId;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getAddress() {
        return address;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public Location getLocation() {
        return location;
    }

    public String getCurrency() {
        return currency;
    }

    public List<String> getLanguages() {
        return languages != null ? Collections.unmodifiableList(languages) : Collections.<String>emptyList();
    }

    public List<Duration> getServices() {
        return services != null ? Collections.unmodifiableList(services) : Collections.<Duration>emptyList();
    }

    public HeaderStyle getHeaderStyle() {
        return headerStyle;
    }

    public LogoStyle getLogoStyle() {
        return logoStyle;
    }

    public int getThemeColor() {
        return themeColor != null ? themeColor.getColor() : 0;
    }

    public boolean isUseDuration() {
        return useDuration;
    }

    public boolean isUseBasket() {
        return useBasket;
    }

    public boolean isShowPrice() {
        return showPrice;
    }

    public Link getEntryPoint() {
        return entryPoint;
    }

    public Style getStyle() {
        return style;
    }

    public List<SideMenu> getSideMenu() {
        return sideMenu != null ? Collections.unmodifiableList(sideMenu) : Collections.<SideMenu>emptyList();
    }

    public static class HeaderStyle {
        @SerializedName("img")
        private String imageId;
        private ColorValue color;
        @SerializedName("bgColor")
        private ColorValue backgroundColor;
        private String backLabel;
        private String homeLabel;

        public String getImageId() {
            return imageId;
        }

        public int getColor() {
            return color != null ? color.getColor() : 0;
        }

        public int getBackgroundColor() {
            return backgroundColor != null ? backgroundColor.getColor() : 0;
        }

        public String getBackLabel() {
            return backLabel;
        }

        public String getHomeLabel() {
            return homeLabel;
        }
    }
    public static  class LogoStyle {
        @SerializedName("img")
        private String imageId;
        @SerializedName("bgColor")
        private ColorValue backgroundColor;
        private LogoStyleType type;

        public String getImageId() {
            return imageId;
        }

        public int getBackgroundColor() {
            return backgroundColor!= null ? backgroundColor.getColor() : 0;
        }

        public LogoStyleType getType() {
            return type;
        }
    }

    public enum LogoStyleType {
        WIDE, ICON
    }

    public static class SideMenu {
        private int order;
        private Link link;

        public int getOrder() {
            return order;
        }

        public Link getLink() {
            return link;
        }
    }
}
