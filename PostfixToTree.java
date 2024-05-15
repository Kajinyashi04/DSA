import java.io.*;

class TreeNode {
    public String data;
    public TreeNode leftChild;
    public TreeNode rightChild;

    public TreeNode(String data) {
        this.data = data;
        this.leftChild = null;
        this.rightChild = null;
    }

    // Method to display the node
    public void displayNode() {
        System.out.print(data);
    }
}

class Tree {
    private TreeNode root;

    public Tree() {
        root = null;
    }

    // Method to build the tree from a postfix expression
    public void buildTreeFromPostfix(String postfix) {
        ParsePost parser = new ParsePost(postfix);
        root = buildTree(parser);
    }

    // Helper method to recursively build the tree
    private TreeNode buildTree(ParsePost parser) {
        String token = parser.getNextToken();
        TreeNode newNode = new TreeNode(token);

        if (isOperator(token)) {
            newNode.rightChild = buildTree(parser);
            newNode.leftChild = buildTree(parser);
        }

        return newNode;
    }

    // Method to check if a token is an operator
    private boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    // Method to display the tree in infix form
    public void displayInfix() {
        System.out.print("Infix expression: ");
        inOrder(root);
        System.out.println();
    }

    // Recursive method to display the tree in infix form
    private void inOrder(TreeNode node) {
        if (node != null) {
            boolean isOperator = isOperator(node.data);
            if (isOperator) {
                System.out.print("(");
            }

            inOrder(node.leftChild);
            System.out.print(node.data);
            inOrder(node.rightChild);

            if (isOperator) {
                System.out.print(")");
            }
        }
    }
}

class ParsePost {
    private String input;
    private int current;

    public ParsePost(String input) {
        this.input = input;
        this.current = 0;
    }

    public String getNextToken() {
        char ch = input.charAt(current++);
        return String.valueOf(ch);
    }
}

public class PostfixToTree {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter postfix expression: ");
        String postfix = br.readLine();

        Tree tree = new Tree();
        tree.buildTreeFromPostfix(postfix);

        System.out.println("Postfix expression transformed into a tree:");
        tree.displayInfix();
    }
}
