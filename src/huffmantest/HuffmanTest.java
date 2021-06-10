package huffmantest;

import huffmanencoding.HuffmanEncoding;

import java.util.*;

/**
 * @author Ekampreet Kaur
 * @version IT 334
 * Description: HuffmanEncoding Test file
 */
public class HuffmanTest {

    public static final int BIT = 16;
    private static String sourceText;
    private static HuffmanEncoding huffmanstore;
    private static HuffmanEncoding.HuffmanNode root;


    /**
     * @param args main method
     */
    public static void main(String[] args) {
        // write your code here
        welcomeUser();
        encodeAndDecodeString();
        showResults();

    }

    /**
     *  greet the user and enter a string value onto the console
     */
    public static void welcomeUser() {
        System.out.println("Welcome to my Huffman Encoding Program!");
        System.out.println("***************************************");
        Scanner welcome = new Scanner(System.in);
        System.out.println("Please enter a string: ");
        sourceText = welcome.nextLine();
        huffmanstore = new HuffmanEncoding(sourceText);

    }

    /**
     * A method to call printCharacterEncoding
     */
    public static void encodeAndDecodeString()
    {
       huffmanstore.printCharacterEncoding();

    }

    /**
     * A method that print everything to console
     */
    public static void showResults() {
        System.out.println("Original text: " + sourceText);
        System.out.println("Original text length: " + sourceText.length()
                * 8 + "-" + sourceText.length() * BIT + "bits.");
        String encode = huffmanstore.encode(sourceText);
        System.out.println("Encoded Text: " + encode);
        System.out.println("Encoded Text length:" + encode.length());

        String decode=huffmanstore.decode(encode);
        System.out.println("DecodedText:"+decode);
    }
    /**
     *
     *@param root setter method for root
     */
    public static void setRoot(HuffmanEncoding.HuffmanNode root){
        HuffmanTest.root=root;
    }

    /**
     * @return getter method
     */
    public static HuffmanEncoding.HuffmanNode getRoot() {
        return root;
    }
}