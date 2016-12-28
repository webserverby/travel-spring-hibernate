package com.travel.utility;

import java.util.ArrayList;
import java.util.List;
/**
 * Класс зарузки файлов
 *
 * @author Artem Faenko
 */
public class MultiFileBucket {

    List<FileBucket> files = new ArrayList<FileBucket>();

    public MultiFileBucket(){
        files.add(new FileBucket());
        files.add(new FileBucket());
        files.add(new FileBucket());
        files.add(new FileBucket());
        files.add(new FileBucket());
    }

    public List<FileBucket> getFiles() {
        return files;
    }

    public void setFiles(List<FileBucket> files) {
        this.files = files;
    }

}
