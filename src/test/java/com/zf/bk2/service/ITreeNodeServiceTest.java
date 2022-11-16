package com.zf.bk2.service;

import com.zf.bk2.model.TreeNode;
import com.zf.bk2.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;


@Slf4j
public class ITreeNodeServiceTest extends  BaseTestCase{

    @Autowired
    private ITreeNodeService treeNodeService;

    private TreeNode treeNode;

    @Before
    public void before() {
       treeNode = new TreeNode();
    }

    @Test
    public void update() {
    }

    @Test
    public void listTreeNodeByPid() {

        List<TreeNode> treeNodeList = treeNodeService.listTreeNodeByPid(treeNode);
        for (TreeNode t:treeNodeList){
            System.out.println(t);
        }

    }
}