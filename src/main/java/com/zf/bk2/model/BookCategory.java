package com.zf.bk2.model;

import lombok.Data;

@Data
public class BookCategory {
    private Long bookCategoryId;

    private String bookCategoryName;

    public BookCategory(Long bookCategoryId, String bookCategoryName) {
        this.bookCategoryId = bookCategoryId;
        this.bookCategoryName = bookCategoryName;
    }

    public BookCategory() {
        super();
    }

    public Long getBookCategoryId() {
        return bookCategoryId;
    }

    public void setBookCategoryId(Long bookCategoryId) {
        this.bookCategoryId = bookCategoryId;
    }

    public String getBookCategoryName() {
        return bookCategoryName;
    }

    public void setBookCategoryName(String bookCategoryName) {
        this.bookCategoryName = bookCategoryName;
    }
}