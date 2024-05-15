import java.util.*;

class TreeNode {
    public String data;
    public TreeNode leftChild;
    public TreeNode rightChild;

    public TreeNode(String data) {
        this.data = data;
        this.leftChild = null;
        this.rightChild = null;
    }
}

class Tree {
    private TreeNode root;

    public Tree() {
        root = null;
    }

    // Method to build a binary tree from a string of letters
    public void buildTree(String letters) {
        if (letters == null || letters.isEmpty()) {
            System.out.println("Empty input. Tree cannot be built.");
            return;
        }

        // Split the input string into characters
        char[] chars = letters.toCharArray();

        // Create a queue to store the nodes
        Queue<TreeNode> queue = new LinkedList<>();

        // Create a root node
        root = new TreeNode("+");
        queue.add(root);

        // Iterate over each character in the input string
        for (char ch : chars) {
            // Create a new node for the current character
            TreeNode newNode = new TreeNode(String.valueOf(ch));

            // Remove the front node from the queue
            TreeNode parent = queue.poll();

            // Set the new node as the left child of the parent
            parent.leftChild = newNode;
            queue.add(newNode); // Add the new node to the queue

            // Create a placeholder node for the right child
            TreeNode placeholder = new TreeNode("-");
            parent.rightChild = placeholder;
            queue.add(placeholder); // Add the placeholder node to the queue
        }
    }

    // Method to display the binary tree
    public void displayTree() {
        displayTree(root, 0);
    }

    // Recursive method to display the binary tree
    private void displayTree(TreeNode node, int level) {
        if (node != null) {
            displayTree(node.rightChild, level + 1);
            for (int i = 0; i < level; i++) {
                System.out.print("     ");
            }
            System.out.println(node.data);
            displayTree(node.leftChild, level + 1);
        }
    }
}

public class TreeApp1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string of letters: ");
        String letters = scanner.nextLine();

        Tree tree = new Tree();
        tree.buildTree(letters);

        System.out.println("\nResulting Tree:");
        tree.displayTree();
    }
}
