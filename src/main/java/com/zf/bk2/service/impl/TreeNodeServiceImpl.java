package com.zf.bk2.service.impl;

import com.zf.bk2.mapper.TreeNodeMapper;
import com.zf.bk2.model.TreeNode;
import com.zf.bk2.service.ITreeNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreeNodeServiceImpl implements ITreeNodeService {

    @Autowired
    private TreeNodeMapper treeNodeMapper;

    private TreeNode node = new TreeNode();

    @Override
    public int update(TreeNode treeNode) {
        return 0;
    }

    @Override
    public List<TreeNode> listTreeNodeByPid(TreeNode treeNode) {
        List<TreeNode> nodeList = treeNodeMapper.listTreeNodeByPid(treeNode);
        this.initChildren(nodeList);
        return nodeList;
    }

    void initChildren(List<TreeNode> nodeList) {
        List<TreeNode> children = null;
        for (TreeNode a : nodeList) {
            node.setParentId(a.getTreeId());
            children = this.listTreeNodeByPid(node);
            this.initChildren(children);
            a.setChildren(children);
            System.out.println("查询全部数据："+a);
        }
    }
}
