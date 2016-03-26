package com.bookvideo.library.domain;

import com.google.gson.annotations.SerializedName;

public class Style {
    public static final Type defaultType = Type.CROP;
    public static final Align defaultAlign = Align.LEFT;
    public static final LabelStyle defaultLabelStyle = LabelStyle.WRAP;
    public static final LineType defaultLineType = LineType.PLAIN;

    @SerializedName("bg")
    private Background background;
    private Padding padding;
    private Label label;
    private Font font;
    private Line line;

    public Background getBackground() {
        return background;
    }

    public Padding getPadding() {
        return padding;
    }

    public Label getLabel() {
        return label;
    }

    public Font getFont() {
        return font;
    }

    public Line getLine() {
        return line;
    }

    public static class Background {
        @SerializedName("image")
        private String imageId;
        private ImageSize imageSize;
        private ColorValue color;
        private Type type = defaultType;

        public String getImageId() {
            return imageId;
        }

        public ImageSize getImageSize() {
            return imageSize;
        }

        public int getColor() {
            return color != null ? color.getColor() : 0;
        }

        public Type getType() {
            return type;
        }

    }

    public static class Padding {
        private int h;
        private int v;

        public int getHorizontalPadding() {
            return h;
        }

        public int getVerticalPadding() {
            return v;
        }
    }

    public static class Label {
        @SerializedName("bgImg")
        private String backgroundImageId;
        @SerializedName("bgColor")
        private ColorValue backgroundColor;
        private int width;
        private ColorValue color;
        private Align align = defaultAlign;
        private LabelStyle style = defaultLabelStyle;
        private ColorValue borderColor;
        private int borderSize;

        public String getBackgroundImageId() {
            return backgroundImageId;
        }

        public int getBackgroundColor() {
            return backgroundColor != null ? backgroundColor.getColor() : 0;
        }

        public int getWidth() {
            return width;
        }

        public int getColor() {
            return color != null ? color.getColor() : 0;
        }

        public Align getAlign() {
            return align;
        }

        public LabelStyle getStyle() {
            return style;
        }

        public int getBorderColor() {
            return borderColor != null ? borderColor.getColor() : 0;
        }

        public int getBorderSize() {
            return borderSize;
        }
    }

    public static class Font {
        private String name;
        private int size;
        private ColorValue color;
        private int letterSpacing;
        private Align align = defaultAlign;

        public String getName() {
            return name;
        }

        public int getSize() {
            return size;
        }

        public int getColor() {
            return color != null ? color.getColor() : 0;
        }

        public int getLetterSpacing() {
            return letterSpacing;
        }

        public Align getAlign() {
            return align;
        }
    }

    public static class Line {
        private LineType type = defaultLineType;

        public LineType getType() {
            return type;
        }
    }

    public enum Type {
        WIDE, CROP
    }

    public enum Align {
        LEFT, CENTER, RIGHT
    }

    public enum LabelStyle {
        WRAP, WIDTH, HEIGHT
    }

    public enum LineType {
        PLAIN, DASHED
    }

}
