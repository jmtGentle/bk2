package com.zf.bk2.service.impl;

import com.zf.bk2.mapper.DocMapper;
import com.zf.bk2.model.Doc;
import com.zf.bk2.service.IDocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocServiceImpl implements IDocService {

    @Autowired
    private DocMapper docMapper;

    @Override
    public int insert(Doc record) {
        return docMapper.insertSelective(record);
    }

    @Override
    public Doc loadImage(Long id) {
        return docMapper.selectByPrimaryKey(id);
    }
}
