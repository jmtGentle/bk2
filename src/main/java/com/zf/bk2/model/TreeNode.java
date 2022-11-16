package com.zf.bk2.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TreeNode {
    private Integer treeId;

    private String treeName;

    private Integer treeType;

    private Integer parentId;

    private String url;

    private Integer position;

    private String icon;

    private List<TreeNode> children = new ArrayList();


    public TreeNode(Integer treeId, String treeName, Integer treeType, Integer parentId, String url, Integer position, String icon) {
        this.treeId = treeId;
        this.treeName = treeName;
        this.treeType = treeType;
        this.parentId = parentId;
        this.url = url;
        this.position = position;
        this.icon = icon;
    }

    public TreeNode() {
        super();
    }

    public Integer getTreeId() {
        return treeId;
    }

    public void setTreeId(Integer treeId) {
        this.treeId = treeId;
    }

    public String getTreeName() {
        return treeName;
    }

    public void setTreeName(String treeName) {
        this.treeName = treeName;
    }

    public Integer getTreeType() {
        return treeType;
    }

    public void setTreeType(Integer treeType) {
        this.treeType = treeType;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }
}