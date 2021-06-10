package huffmanencoding;

import java.util.*;

/**
 * Class Huffman Encoding
 * @author Ekampreet KAUR
 * @version IT 334
 * Description: HuffmanEncoding with all methods
 */
public class HuffmanEncoding {
    private Map<Character, Double> frequencyMap = new TreeMap<>();
    private Map<Character, String> characterEncodings = new HashMap<>();
    private Queue<HuffmanNode> tree = new PriorityQueue<>();
    private String sourceText;
    private HuffmanNode root;


    /**
     * @return tree
     */
    public Queue<HuffmanNode> getTree() {
        return tree;
    }

    /**
     * @param tree setter method for set tree
     */
    public void setTree(Queue<HuffmanNode> tree) {
        this.tree = tree;
    }

    public static class  HuffmanNode implements Comparable<HuffmanNode> {
        private char data;
        private double probability;
        private HuffmanNode left;
        private HuffmanNode right;


        /**
         * // Function to allocate a  tree node
         * @param data tree data
         * @param probability probability
         * @param left  left of tree
         * @param right right side of right
         */
        public HuffmanNode(char data, double probability, HuffmanNode left, HuffmanNode right) {
            this.data = data;
            this.probability = probability;
            this.left = left;
            this.right = right;

        }

        @Override
        public int compareTo(HuffmanNode count) {

            return Double.compare(this.probability, count.probability);
        }


        @Override
        public String toString() {
            return "HuffmanNode{" +
                    "data=" + data +
                    ", probability=" + probability +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    /**
     * A method with one parameter of the initial string
     * @param sourceText the input string that used in code
     */
    public HuffmanEncoding(String sourceText) {
        this.sourceText = sourceText;
        for (int i = 0; i < sourceText.length(); i++) {
            if (!frequencyMap.containsKey(sourceText.charAt(i))) {
                frequencyMap.put(sourceText.charAt(i), 1.0 / sourceText.length());
            } else {
                frequencyMap.put(sourceText.charAt(i),
                        frequencyMap.get(sourceText.charAt(i)) + 1.0 / sourceText.length());
            }
        }
        printCharacterFrequencies();
        analyzeText(tree);
        encodingHelper(root, "");
    }

    /**
     * A method print the frequencies of the character in input string.
     */
    public void printCharacterFrequencies() {
        System.out.println("Character frequencies from text:");
        for (Map.Entry<Character, Double> entry : frequencyMap.entrySet()) {
            System.out.print(entry.getKey() + " -> ");
            System.out.printf("%.10f", entry.getValue());
            System.out.println();


        }
    }

    /**
     * printCharacterEncoding Huffman encoding for each character
     */
        public void printCharacterEncoding()
        {
            for (char charac :characterEncodings.keySet())
            {

            System.out.println(charac + "->"+characterEncodings.get(charac));
            }
        }

    /**
     * A method enocdes the input string using huffman encoding
     *  @param sourceText the input string entered by a user.
     *  @return return string of encoding
     */
    public String encode(String sourceText) {
        this.sourceText = sourceText;
        StringBuilder encoded = new StringBuilder();
        for (Character text : sourceText.toCharArray()) {

            encoded.append(characterEncodings.get(text));
        }
        return encoded.toString();

    }

    private void analyzeText(Queue<HuffmanNode> tree ) {
       this.tree = tree;
        for (Map.Entry<Character, Double> entry : frequencyMap.entrySet()) {
            tree.add(new HuffmanNode(entry.getKey(), entry.getValue(), null, null));
        }

        while (tree.size() > 1) {
            HuffmanNode first = tree.poll();
            HuffmanNode second  = tree.poll();

            assert second != null;
            tree.add(new HuffmanNode('\u0000', first.probability + second.probability, first, second));
        }
            root = tree.poll();
    }

    private void encodingHelper(HuffmanNode node, String text) {
            if (node != null) {
                characterEncodings.put(node.data, text);

                if (node.right != null) {
                    encodingHelper(node.right, text + "1");
                }

                if (node.left != null) {
                    encodingHelper(node.left, text + "0");
                }
            }
    }

    /**
     * A method that takes the encoded string and decodes it
     * @param encoded string
     * @return a decode huffman encoded characters.
     */
    public String decode(String encoded) {
        StringBuilder decodedText = new StringBuilder();
        char[] encodedToChar = encoded.toCharArray();
        HuffmanNode current = root;
        for (char c : encodedToChar) {
            if (c == '0') {
                current = current.left;
                assert current != null;
                if (current.left == null && current.right == null) {
                    decodedText.append(current.data);
                    current = root;
                }
            }
            if (c == '1') {
                current = current.right;
                //reached leaf node
                if (current.left == null && current.right == null) {
                    decodedText.append(current.data);
                    current = root;
                }
            }
        }
        return decodedText.toString();
    }

    @Override
    public String toString() {
        return "HuffmanEncoding{" +
                "frequencyMap=" + frequencyMap +
                ", characterEncodings=" + characterEncodings +
                ", root=" + root +
                 ", sourceText='" + sourceText + '\'' +
                ", tree=" + getTree() +
                '}';
    }
}
