package com.zf.bk2.controller;

import com.zf.bk2.model.TreeNode;
import com.zf.bk2.service.ITreeNodeService;
import com.zf.bk2.util.JsonData;
import com.zf.bk2.util.JsonUtils;
import com.zf.bk2.util.PageBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static java.lang.System.out;

@Controller
@Slf4j
@RequestMapping("/treeNode")
public class TreeNodeController {

    @Autowired
    private ITreeNodeService treeNodeService;

    private TreeNode treeNode;

    private PageBean pageBean = new PageBean();

    @RequestMapping("/toList")
    @ResponseBody//返回json数据给前端的注释代码
    public String list(){
        out.println("进入toList接口");
        List<TreeNode> nodeList = treeNodeService.listTreeNodeByPid(treeNode);
        for (TreeNode t:nodeList){
            out.println("Controller层查看:"+t);
        }
        JsonData jsonData = new JsonData();
        jsonData.setCode(0);
        jsonData.setMessage("书籍查询成功");
        jsonData.setResult(nodeList);
        jsonData.setPage(pageBean.getPage());
        jsonData.setRows(pageBean.getRows());
        jsonData.setTotal(pageBean.getTotal());
        System.out.println("书籍查询成功:" + jsonData);
        String JsonData = JsonUtils.getJson(jsonData);

        return JsonData;
    }
}
