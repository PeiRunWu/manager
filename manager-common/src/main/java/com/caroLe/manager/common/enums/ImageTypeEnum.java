package com.caroLe.manager.common.enums;

import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;

/**
 * @author CaroLe
 * @Date 2023/7/24 22:23
 * @Description
 */
@AllArgsConstructor
public enum ImageTypeEnum {
    JPEG("jpeg", ".jpeg"), JPG("jpg", ".jpg"), PNG("png", ".png"), GIF("gif", ".gif"), BMP("bmp", ".bmp"),
    SVG("svg", ".svg");

    private String fileType;

    private String fileExtension;

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public static ImageTypeEnum getFileType(String fileExtension) {
        for (ImageTypeEnum value : ImageTypeEnum.values()) {
            if (value.getFileExtension().equals(fileExtension)) {
                return value;
            }
        }
        return null;
    }

}
