package com.me.menu;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * @author zs
 * @date 2021/11/9
 * 使用hutools
 */
public class MenuTest {
    public static void main(String[] args) {
// 构建node列表
        List<TreeNode<String>> nodeList = CollUtil.newArrayList();

        nodeList.add(new TreeNode<>("1", "0", "系统管理", 5));
        nodeList.add(new TreeNode<>("221", "2", "商品管理2", 2));
        nodeList.add(new TreeNode<>("2", "0", "店铺管理", 1));
        nodeList.add(new TreeNode<>("11", "1", "用户管理", 222222));
        nodeList.add(new TreeNode<>("21", "2", "商品管理", 44));
        nodeList.add(new TreeNode<>("111", "11", "用户添加", 0));

        //配置
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        // 自定义属性名 都要默认值的
        treeNodeConfig.setWeightKey("order");
        treeNodeConfig.setParentIdKey("pid");
        treeNodeConfig.setIdKey("rid");
        treeNodeConfig.setNameKey("menu_name");
        treeNodeConfig.setWeightKey("sort");
        // 最大递归深度
//        treeNodeConfig.setDeep(3);

        //转换器，可以属性的名字改掉
        List<Tree<String>> treeNodes = TreeUtil.build(nodeList, "0", treeNodeConfig,
                (treeNode, tree) -> {
                    tree.setId(treeNode.getId());
                    tree.setParentId(treeNode.getParentId());
                    tree.setWeight(treeNode.getWeight());
                    tree.setName(treeNode.getName());
                    // 扩展属性 ...
                    tree.putExtra("extraField", 666);
                    tree.putExtra("other", new Object());
                });

//        treeNodes.forEach(e -> System.out.println(e.getName()));
        String json = JSON.toJSONString(treeNodes);
        System.out.println(json);

//        List<Tree<String>> build = TreeUtil.build(nodeList, "0");
//        System.out.println(build);


    }
}
