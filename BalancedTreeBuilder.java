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

    // Method to build a balanced binary tree from a string of letters
    public void buildBalancedTree(String letters) {
        if (letters == null || letters.isEmpty()) {
            System.out.println("Empty input. Tree cannot be built.");
            return;
        }

        // Split the input string into characters
        char[] chars = letters.toCharArray();

        // Create a list to store the nodes
        List<TreeNode> forest = new ArrayList<>();

        // Create one-node trees for each letter and add them to the forest
        for (char ch : chars) {
            forest.add(new TreeNode(String.valueOf(ch)));
        }

        // Build a balanced tree from the forest
        while (forest.size() > 1) {
            List<TreeNode> newForest = new ArrayList<>();
            for (int i = 0; i < forest.size(); i += 2) {
                TreeNode left = forest.get(i);
                TreeNode right = (i + 1 < forest.size()) ? forest.get(i + 1) : null;
                TreeNode newNode = new TreeNode("+");
                newNode.leftChild = left;
                newNode.rightChild = right;
                newForest.add(newNode);
            }
            forest = newForest;
        }

        root = forest.get(0);
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

public class BalancedTreeBuilder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string of letters: ");
        String letters = scanner.nextLine();

        Tree tree = new Tree();
        tree.buildBalancedTree(letters);

        System.out.println("\nResulting Balanced Tree:");
        tree.displayTree();
    }
}
