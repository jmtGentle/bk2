package com.zf.bk2.service;

import com.zf.bk2.model.TreeNode;
import org.springframework.stereotype.Service;

import java.util.List;
public interface ITreeNodeService {
    int update(TreeNode treeNode);

    List<TreeNode> listTreeNodeByPid(TreeNode treeNode);
}
