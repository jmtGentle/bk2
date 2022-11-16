package com.zf.bk2.mapper;

import com.zf.bk2.model.TreeNode;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreeNodeMapper {
    int update(TreeNode treeNode);

    List<TreeNode> listTreeNodeByPid(TreeNode treeNode);
}