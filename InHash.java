import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.lang.*;

/**

 This hash map accepts a char as the key and then gets the
 position of the char in the UTF-8 charset as the index of the value

 **/


// cool vim commands:

// :%le - remove all indent
// gg=G - apply indent to all lines

// compile this: javac -cp '.:javax.xml.bind.jar' InHash.java

public class InHash {

    // 256 = 2 ^ 8, so we can have 8 bits, or a byte key with perfect hashing
    // UTF-8 set of characters has 256 entries, so I think I could match any UTF-8 character
    // to an index
    private final static int HASH_LENGTH = 256;

    private String[] mValues;

    public InHash() {
        mValues = new String[HASH_LENGTH];
    }

    public void moveBits() {
        int a = 60;	/* 60 = 0011 1100 */
        int b = 13;	/* 13 = 0000 1101 */
        int c = 0;

        c = a & b;       /* 12 = 0000 1100 */
        System.out.println("a & b = " + c );

        c = a | b;       /* 61 = 0011 1101 */
        System.out.println("a | b = " + c );

        c = a ^ b;       /* 49 = 0011 0001 */
        System.out.println("a ^ b = " + c );

        c = ~a;          /*-61 = 1100 0011 */
        System.out.println("~a = " + c );

        c = a << 2;     /* 240 = 1111 0000 */
        System.out.println("a << 2 = " + c );

        c = a >> 2;     /* 215 = 1111 */
        System.out.println("a >> 2  = " + c );

        c = a >>> 2;     /* 215 = 0000 1111 */
        System.out.println("a >>> 2 = " + c );
    }

    public void testIntParsing() {
        char[] charArr = {'a', 'b', 'c'};
        byte b2 = (byte)charArr[0];
        char c0 = (char)((b2 & 0x00FF) << 8);
        int binaryLiteral255 = Integer.parseInt("0000000011111111", 2);
        char c1 = (char)((b2 & binaryLiteral255) << 8);

        print("char 0: " + c0);
        print("char 1: " + c1);

        // how to read binary:
        // [x * n^2] where x is either 1 or 0 and n is number of digits to the left in binary literal
        int binaryLiteralSampleA = Integer.parseInt("0001", 2); //1
        int binaryLiteralSampleB = Integer.parseInt("0010", 2); //2
        int binaryLiteralSampleC = Integer.parseInt("0101", 2); //5
        int binaryLiteralSampleD = Integer.parseInt("10101", 2); //4^2 + 2^2 + 0^2 == 16 + 4 + 1 == 21

        print("binary number: " + binaryLiteralSampleA);
        print("binary number: " + binaryLiteralSampleB);
        print("binary number: " + binaryLiteralSampleC);
        print("binary number: " + binaryLiteralSampleD);

        // hexadecimal, or base 16, literal parsing
        // hex - binary
        // 0 - 0000
        // 1 - 0001
        // 2 - 0010
        // 3 - 0011
        // ...
        // E - 1110
        // F - 1111
        int hexLiteralSampleA = Integer.parseInt("00FF", 16);
        print("hex number: " + hexLiteralSampleA);

    }

    public void testKey(String key) {
        try {
            byte[] b = key.getBytes("UTF-8");
            int hashCodeAtZero = b.hashCode();
            print("length of byte array: " + b.length + ", hash code at 0: " + hashCodeAtZero);
            String backToString = DatatypeConverter.printBase64Binary(b);
            print("hashed key: " + backToString);

            Byte b0 = new Byte("127");
            Byte b1 = new Byte("0");

            print("b0 as int: " + b0.intValue());
            print("b1 as int: " + b1.intValue());

        } catch (UnsupportedEncodingException e) {
            print("Error: " + e.toString());
        }
    }

    public void add(String key, String value) {
        print("key: " + key + ", value: " + value);
        char[] charArr = key.toCharArray();
        int indexInUTF8Set = (int)charArr[0];
        mValues[indexInUTF8Set] = value;
        print("key resolved to index: " + indexInUTF8Set);

    }

    public String get(char key) {
        return mValues[(int)key];
    }

    private void print(String text) {
        System.out.println(text);
    }

}
