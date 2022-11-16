package com.zf.bk2.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookVo implements Serializable {
    private Long bookId;

    private String bookName;

    private String bookNamePinyin;

    private Long bookCategoryId;

    private String bookCategoryName;

    private String bookAuthor;

    private Float bookPrice;

    private Long bookImage;

    private String publishing;

    private String bookDesc;

    private Integer bookState;

    private Date deployDatetime;

    private Integer salesVolume;


}
