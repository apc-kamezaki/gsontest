package com.bookvideo.library.domain;

import android.graphics.Color;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorUtil {

    private static Pattern rgbaPattern = Pattern.compile(
            "rgba\\(\\s*(\\d+)\\s*,\\s*(\\d+)\\s*,\\s*(\\d+)\\s*,\\s*([01]\\.\\d+|\\d+)\\s*\\)");

    private ColorUtil() {

    }

    private static int fromRGBA(String rgba) {

        Matcher m = rgbaPattern.matcher(rgba);
        if (m.find()) {
            return (int) (0xff * Float.valueOf(m.group(4))) << 24
                    | Integer.valueOf(m.group(1)) << 16
                    | Integer.valueOf(m.group(2)) << 8
                    | Integer.valueOf(m.group(3));
        } else {
            return Color.TRANSPARENT;
        }
    }

    private static int fromHex(String hex) {
        // Integer.parseInt(hex, 16) has number format exception
        // when I convert #ffffffff, so I made custom converter

        if (hex.startsWith("#")) {
            hex = hex.substring(1);
        } else if (hex.startsWith("#") || hex.startsWith("0x") || hex.startsWith("0X")) {
            hex = hex.substring(2);
        }

        return Integer.valueOf(hex.substring(0, 2), 16) << 24
                | Integer.valueOf(hex.substring(2, 4), 16) << 16
                | Integer.valueOf(hex.substring(4, 6), 16) << 8
                | Integer.valueOf(hex.substring(6, 8), 16);
    }

    public static int fromString(String str) {
        if (str.startsWith("rgba")) {
            return fromRGBA(str);
        } else if (str.startsWith("#")) {
            return fromHex(str.substring(1));
        } else if (str.startsWith("0x") || str.startsWith("0X")) {
            return fromHex(str.substring(2));

        } else {
            return Integer.valueOf(str);
        }
    }
}
