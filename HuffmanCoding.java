import java.util.*;

// Node class for Huffman Tree
class HuffmanNode implements Comparable<HuffmanNode> {
    char data;
    int frequency;
    HuffmanNode left, right;

    public HuffmanNode(char data, int frequency) {
        this.data = data;
        this.frequency = frequency;
        left = right = null;
    }

    @Override
    public int compareTo(HuffmanNode node) {
        return this.frequency - node.frequency;
    }
}

// Comparator for priority queue
class MyComparator implements Comparator<HuffmanNode> {
    public int compare(HuffmanNode x, HuffmanNode y) {
        return x.frequency - y.frequency;
    }
}

public class HuffmanCoding {
    // Function to build Huffman Tree
    public static HuffmanNode buildHuffmanTree(Map<Character, Integer> freqMap) {
        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>(new MyComparator());

        // Create leaf nodes for each character and add them to the priority queue
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            priorityQueue.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        // Combine nodes until there is only one node left (root)
        while (priorityQueue.size() > 1) {
            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();

            HuffmanNode newNode = new HuffmanNode('$', left.frequency + right.frequency);
            newNode.left = left;
            newNode.right = right;

            priorityQueue.add(newNode);
        }

        // The remaining node in the priority queue is the root of the Huffman Tree
        return priorityQueue.poll();
    }

    // Function to generate code table
    public static void generateCodeTable(HuffmanNode root, String code, Map<Character, String> codeTable) {
        if (root == null) {
            return;
        }

        if (root.data != '$') {
            codeTable.put(root.data, code);
        }

        generateCodeTable(root.left, code + "0", codeTable);
        generateCodeTable(root.right, code + "1", codeTable);
    }

    // Function to encode message into binary
    public static String encodeMessage(String message, Map<Character, String> codeTable) {
        StringBuilder encodedMessage = new StringBuilder();

        for (char c : message.toCharArray()) {
            encodedMessage.append(codeTable.get(c));
        }

        return encodedMessage.toString();
    }

    // Function to decode binary back to text
    public static String decodeMessage(String encodedMessage, HuffmanNode root) {
        StringBuilder decodedMessage = new StringBuilder();
        HuffmanNode current = root;

        for (char bit : encodedMessage.toCharArray()) {
            if (bit == '0') {
                current = current.left;
            } else {
                current = current.right;
            }

            if (current.left == null && current.right == null) {
                decodedMessage.append(current.data);
                current = root;
            }
        }

        return decodedMessage.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the text message:");
        String message = scanner.nextLine();

        // Count frequency of each character in the message
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : message.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        // Build Huffman Tree
        HuffmanNode root = buildHuffmanTree(freqMap);

        // Generate code table
        Map<Character, String> codeTable = new HashMap<>();
        generateCodeTable(root, "", codeTable);

        // Encode message into binary
        String encodedMessage = encodeMessage(message, codeTable);
        System.out.println("Encoded message: " + encodedMessage);

        // Decode binary back to text
        String decodedMessage = decodeMessage(encodedMessage, root);
        System.out.println("Decoded message: " + decodedMessage);
    }
}
