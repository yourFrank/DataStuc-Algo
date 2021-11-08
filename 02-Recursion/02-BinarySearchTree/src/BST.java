import java.util.Deque;
import java.util.LinkedList;

/**
 * 所有二分搜索树，都要求在可以比较的基础上，因此都需要实现比较接口
 */
class BST<E extends Comparable<E>> {

    private class Node { //Node类只有当前BST类需要使用，对外部屏蔽，因此设置成private
        E val; //当前节点的值
        Node left; //左节点
        Node right; //右节点

        Node() {
        }

        Node(E val) {
            this.val = val;
        }
    }

    Node root; //每一个树都有它的根节点
    int size; //树的节点个数

    public BST() { //构造函数
        root = null;
        size = 0;
    }


    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E val) { //添加节点
        root = add(root, val);
    }

    /*
    private void add(Node root, E val) {

        if (root==null){
            root=new Node(val);
        }
        if (root.left==null&&root.val.compareTo(val)>0){
            root.left=new Node(val);
        } else if (root.right==null&&root.val.compareTo(val)<0){
            root.left=new Node(val);
        }else {
            return ;
        }
        if (root.val.compareTo(val)>0){
           add(root.left,val);
        }else if (root.val.compareTo(val)<0){
            add(root.right,val);
        }
    }
    */
    //递归添加
    // 上面那种方式递归基本条件和下面递归判断重复，因此可以再往下递归一层
    private Node add(Node root, E val) {
        if (root == null) {
            size++;
            return new Node(val);
        }
        //这里如果等于val的话就不添加了，也就是这里实现的是不包含重复的元素
        if (root.val.compareTo(val) > 0) {
            root.left = add(root.left, val);
        } else if (root.val.compareTo(val) < 0) {
            root.right = add(root.right, val);
        }
        return root;
    }

    public boolean contains(E val) { //判断树中是否包含某节点
        return contains(root, val);
    }

    private boolean contains(Node root, E val) {
        if (root == null) {
            return false;
        }
        if (root.val.compareTo(val) > 0) {
            return contains(root.left, val);
        } else if (root.val.compareTo(val) < 0) {
            return contains(root.right, val);
        } else {
            return true;
        }
    }

    public Node removeMin(E val) {
        return removeMin(root, val);
    }

    //删除树中的最小值，并且返删除元素后树的根节点
    private Node removeMin(Node root, E val) {
        if (root.left == null) {  //如果left为null的话，返回right作为新的根节点
            Node node = root.right;
            root.right = null;
            size--;
            return node;
        }
        root.left = removeMin(root.left, val);
        return root;

    }

    //删除树中的最大值，并且返删除元素后树的根节点
    private Node removeMax(Node root, E val) {
        if (root.right == null) {
            Node node = root.left;
            root.left = null;
            size--;

            return node;
        }
        root.right = removeMin(root.right, val);
        return root;

    }

     //注意这里的前中后都是指的根节点相对左右的位置
    //递归的先序遍历
    public void preOrderR(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val+" ");
        preOrderR(root.left);
        preOrderR(root.right);
    }

    //递归的中序遍历
    public void midOrderR(Node root) {
        if (root == null) {
            return;
        }
        midOrderR(root.left);
        System.out.print(root.val+" ");
        midOrderR(root.right);
    }

    //递归的后序遍历
    public void inOrderR(Node root) {
        if (root == null) {
            return;
        }
        inOrderR(root.left);
        inOrderR(root.right);
        System.out.print(root.val+" ");
    }

    //树的层序遍历
    public void traverseByLevel(Node root) {
        //使用队列（先入先出）来保存
        Deque<Node> deque = new LinkedList<>();
        deque.add(root);
        while (!deque.isEmpty()) {
            Node node = deque.remove();
            System.out.print(node.val+" ");
            if (node.left != null) {
                deque.add(node.left);

            }
            if (node.right != null) {
                deque.add(node.right);
            }

        }
    }
    //非递归实现栈
    public void midOrderNR() {
        Node current = root;
        //把LinkedList作为栈使用
        LinkedList<Node> s = new LinkedList<>();
        while (current != null || !s.isEmpty()) {
            while (current != null) {  //每次都将左侧的全入栈
                s.push(current);
                current = current.left;
            }
            if (!s.isEmpty()) {
                current = s.pop();
                System.out.print(current.val + " -> ");
                current = current.right;
            }
        }
    }


    //树的前序遍历，非递归实现（手动模拟栈）
    public void preOrderNR(Node root) {
        Deque<Node> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            System.out.print(node.val+" ");
            if (node.right != null) {
                stack.push(node.right); //栈中先放入右边的节点，这样就会先访问左边的节点

            }
            if (node.left != null) {
                stack.push(node.left);
            }

        }
    }
    //树的后序遍历，非递归实现（手动模拟栈）
    //后序遍历其实就是先序遍历调换左右顺序（先右再左）之后再倒序输出，因此我们可以将其先保存到栈中
    public void inOrderNR(Node root) {
        Deque<Node> stack = new LinkedList<>();
        Deque<Node> stack2 = new LinkedList<>();

        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            stack2.push(node);

            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right); //栈中先放入右边的节点，这样就会先访问左边的节点

            }
        }
        while (!stack2.isEmpty()){
            System.out.print(stack2.pop().val+" ");
        }
    }

}