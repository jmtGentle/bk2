package com.zf.bk2.service;

import com.zf.bk2.model.Doc;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

@Slf4j
public class IDocServiceTest extends  BaseTestCase{

    @Autowired
    private IDocService docService;

    private Doc doc;

    @Before
    public void  Init(){
        doc = new Doc();
    }

    @Test
    public void insert() {
        doc.setId(System.currentTimeMillis());
        doc.setFileName("png");
        doc.setMime("img/png");
        docService.insert(doc);
    }

    @Test
    public void loadImage() {
        Doc d  = docService.loadImage(doc.getId());
        System.out.println(d);
    }

}