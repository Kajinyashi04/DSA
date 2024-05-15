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

    // Method to build a complete binary tree from a string of letters
    public void buildCompleteTree(String letters) {
        if (letters == null || letters.isEmpty()) {
            System.out.println("Empty input. Tree cannot be built.");
            return;
        }

        // Split the input string into characters
        char[] chars = letters.toCharArray();

        // Create a queue to store the nodes
        Queue<TreeNode> queue = new LinkedList<>();

        // Create a root node for the first character
        root = new TreeNode(String.valueOf(chars[0]));
        queue.add(root);

        int index = 1;
        // Process each level of the tree
        while (!queue.isEmpty() && index < chars.length) {
            int levelSize = queue.size();
            // Process each node in the current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();

                // Create left child if available
                if (index < chars.length) {
                    current.leftChild = new TreeNode(String.valueOf(chars[index]));
                    queue.add(current.leftChild);
                    index++;
                }

                // Create right child if available
                if (index < chars.length) {
                    current.rightChild = new TreeNode(String.valueOf(chars[index]));
                    queue.add(current.rightChild);
                    index++;
                }
            }
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

public class CompleteTreeBuilder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string of letters: ");
        String letters = scanner.nextLine();

        Tree tree = new Tree();
        tree.buildCompleteTree(letters);

        System.out.println("\nResulting Complete Tree:");
        tree.displayTree();
    }
}
