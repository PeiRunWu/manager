package com.caroLe.manager.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author CaroLe
 * @Date 2023/7/24 22:23
 * @Description
 */
@Getter
@AllArgsConstructor
public enum ImageTypeEnum {
    /**
     * JPEG
     */
    JPEG("jpeg", ".jpeg"),
    /**
     * jpg
     */
    JPG("jpg", ".jpg"),
    /**
     * png
     */
    PNG("png", ".png"),
    /**
     * gif
     */
    GIF("gif", ".gif"),
    /**
     * bmp
     */
    BMP("bmp", ".bmp"),
    /**
     * svg
     */
    SVG("svg", ".svg");

    private final String fileType;

    private final String fileExtension;

    public static ImageTypeEnum getFileType(String fileExtension) {
        for (ImageTypeEnum value : ImageTypeEnum.values()) {
            if (value.getFileExtension().equals(fileExtension)) {
                return value;
            }
        }
        return null;
    }

}
