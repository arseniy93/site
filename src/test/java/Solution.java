import java.util.Arrays;

public class Solution {

    private final static int[] charCodeDigital = new int[10];

    static {
        getCharsOfDigital(charCodeDigital);
    }

    public static void main(String[] args) {
        System.out.println(getMaxLetterSequenceLength(6817L) + " в числе 1АА1");
        System.out.println(getMaxLetterSequenceLength(447355553L) + " в числе 1AAA1AA1");
        System.out.println(getMaxLetterSequenceLength(2826957L) + " в числе 2B22CD");
        System.out.println(getMaxLetterSequenceLength(4660L) + " в числе 1234");
        System.out.println(getMaxLetterSequenceLength(240589L) + " в числе 3ABCD");
        System.out.println(getMaxLetterSequenceLength(56695L) + " в числе DD77");
        System.out.println(getMaxLetterSequenceLength(160L) + " в числе A0");

    }

    public static int getMaxLetterSequenceLength(long number) {
        var toHex = Long.toHexString(number);
        System.out.println(toHex);
        for (int i = 0; i < toHex.toCharArray().length; i++) {
            for (int i1 = 0; i1 < charCodeDigital.length; i1++) {
//                if((int)c ==  )
            }
        }

        return 0;
    }

    private boolean isDigital(int digital) {
        return Arrays.stream(charCodeDigital).anyMatch(x -> x == digital);
    }


    private static int[] getCharsOfDigital(int[] charCodeDigital) {
        for (int i = 0; i < 10; i++) {
            int number = i;
            String numberString = String.valueOf(number);
            char characterNine = numberString.charAt(0);
            int asciiCode = (int) characterNine;
            charCodeDigital[i] = asciiCode;
        }
        return charCodeDigital;
    }

    private  int getCodeOfLetter(String number) {
        char characterNine = number.charAt(0);
        int asciiCode = (int) characterNine;
        return asciiCode;
    }
}
