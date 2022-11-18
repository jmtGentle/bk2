package com.zf.bk2.model;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Doc {
    private Long id;

    private String fileName;

    private String mime;

    public Doc(Long id, String fileName, String mime) {
        this.id = id;
        this.fileName = fileName;
        this.mime = mime;
    }

    public Doc() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }
}