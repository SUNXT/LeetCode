package common;

import utils.Logger;
import utils.LoggerFactory;

/**
 * 二叉树结构
 */
public class BinaryTreeNode {

    private Logger logger = LoggerFactory.getLogger(this);

    public int val;
    public BinaryTreeNode left;
    public BinaryTreeNode right;

    public BinaryTreeNode(int val){
        this.val = val;
    }

    public static BinaryTreeNode build(int[] preArray, int[] midArray){
        if (preArray == null || midArray == null || preArray.length <= 0 || preArray.length != midArray.length){
            return null;
        }
        BinaryTreeNode root = new BinaryTreeNode(preArray[0]);
        int index = indexOfArray(preArray[0], midArray);
        root.left = build(subArray(1, index, preArray), subArray(0, index - 1, midArray));
        root.right = build(subArray(index + 1, preArray.length - 1, preArray), subArray(index + 1, midArray.length - 1, midArray));
        return root;
    }

    private static int indexOfArray(int value, int[] array){
        for (int i = 0; i < array.length; i ++){
            if (value == array[i]){
                return i;
            }
        }
        return -1;
    }

    private static int[] subArray(int start, int end, int[] source){
        if (source == null || start < 0 || end >= source.length){
            return null;
        }
        int[] result = new int[end - start + 1];
        for (int i = 0; start <= end; i ++, start ++){
            result[i] = source[start];
        }
        return result;
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        logger.println("二叉树前序遍历");
        preOrder(this);
        logger.println("");
    }

    private void preOrder(BinaryTreeNode node) {
        if (node != null) {
            logger.print(node.val + " ,");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public void midOrder() {
        logger.println("二叉树中序遍历");
        midOrder(this);
        logger.println("");
    }

    private void midOrder(BinaryTreeNode node) {
        if (node != null){
            midOrder(node.left);
            logger.print(node.val + " ,");
            midOrder(node.right);
        }
    }

    public void postOrder(){
        logger.println("二叉树后序遍历");
        postOrder(this);
        logger.println("");
    }

    private void postOrder(BinaryTreeNode node){
        if (node != null){
            postOrder(node.left);
            postOrder(node.right);
            logger.print(node.val + " ,");
        }
    }
}
