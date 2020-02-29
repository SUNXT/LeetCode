package problems.medium.tree;

import common.BinaryTreeNode;
import utils.Logger;
import utils.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 102. 二叉树的层次遍历
 *
 * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
 *
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层次遍历结果：
 *
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal
 */
public class BinaryTreeLevelOrderTraversal {

    private static Logger logger = LoggerFactory.getLogger(BinaryTreeLevelOrderTraversal.class);

    public static void main(String[] args){
        BinaryTreeNode root = BinaryTreeNode.build(new int[]{1, 2, 4, 5, 3}, new int[]{4, 2, 5, 1, 3});
        BinaryTreeLevelOrderTraversal solution = new BinaryTreeLevelOrderTraversal();
        List<List<Integer>> lists = solution.levelOrder(root);
        for (List<Integer> list: lists){
            logger.print("[");
            for (int i = 0; i < list.size(); ++ i){
                if (i == list.size() - 1){
                    logger.println(list.get(i) + "]");
                }else {
                    logger.print(list.get(i) + ",");
                }
            }
        }
    }

    /**
     * 层序遍历
     * 用队列Queue 配合实现，从树根开始入队列，然后取出遍历，判断结束条件，队列不为空，然后再将子女节点按左到右的顺序入队列，然后，再出队列遍历，且判断是否有子女节点可以入队列，遍历完树
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(BinaryTreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null){
            return result;
        }
        Queue<BinaryTreeNode> queue = new LinkedBlockingQueue<>();
        // 先将数根入队列
        queue.offer(root);
        // 循环条件，队列不为空
        while (!queue.isEmpty()){
            // 再用一个循环，遍历完这一层有的节点
            int count = queue.size();
            List<Integer> list = new ArrayList<>();
            result.add(list);
            while (count > 0){
                BinaryTreeNode node = queue.poll();
                count --;
                if (node != null) {
                    // 将出队列的点的值添加到list中
                    list.add(node.val);
                    // 如果左右子树不为空，则入队列
                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                }
            }
        }
        return result;
    }
}
