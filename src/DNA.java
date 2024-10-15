/**
 * DNA
 * <p>
 * A puzzle created by Zach Blick
 * for Adventures in Algorithms
 * at Menlo School in Atherton, CA
 *</p>
 * <p>
 * Completed by: Kieran Pichai
 *</p>
 */

public class DNA {

    /**
     * TODO: Complete this function, STRCount(), to return longest consecutive run of STR in sequence.
     */
    public static int createBinarySTR(String STR) {
        int binarySTR = 0b0;
        for (int i = 0; i < STR.length(); i++) {
            if (STR.charAt(i) == 'A' || STR.charAt(i) == 'a') {
                binarySTR = binarySTR << 2;
                binarySTR += 0b00;
            } else if (STR.charAt(i) == 'C' || STR.charAt(i) == 'c') {
                binarySTR = binarySTR << 2;
                binarySTR += 0b01;
            } else if (STR.charAt(i) == 'T' || STR.charAt(i) == 't') {
                binarySTR = binarySTR << 2;
                binarySTR += 0b10;
            } else if (STR.charAt(i) == 'G' || STR.charAt(i) == 'g') {
                binarySTR = binarySTR << 2;
                binarySTR += 0b11;
            }
        }
        return binarySTR;
    }

    public static int countMax(String sequence, String STR) {
        int max = 0;
        int temp = 0;
        int STRBinary = createBinarySTR(STR);
        int curSequence;

        for (int i = 0; i < sequence.length() - STR.length();) {
            curSequence = createBinarySTR(sequence.substring(i, i + STR.length()));
            if (curSequence == STRBinary) {
                temp += 1;
                i += STR.length();
            } else {
                if (temp > 0 && temp > max) {
                    max = temp;
                    temp = 0;
                }
                i++;
            }
        }

        return max;
    }

    public static int STRCount(String sequence, String STR) {
        return countMax(sequence, STR);
    }
}
