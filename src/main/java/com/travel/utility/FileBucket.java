package com.travel.utility;

import org.springframework.web.multipart.MultipartFile;
/**
 * Класс зарузки файла
 *
 * @author Artem Faenko
 */
public class FileBucket {

    MultipartFile file;

    String description;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
