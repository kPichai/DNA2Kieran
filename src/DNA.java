import java.util.LinkedList;
import java.util.Queue;

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
        int binarySTR = 0;
        for (int i = 0; i < STR.length(); i++) {
            binarySTR = binarySTR << 2;
            switch(STR.charAt(i)) {
                case 'C', 'c' -> binarySTR += 0b01;
                case 'T', 't' -> binarySTR += 0b10;
                case 'G', 'g' -> binarySTR += 0b11;
            }
        }
        return binarySTR;
    }

    public static int generateMask(int lenOfSTR) {
        return (1 << (2*lenOfSTR)) - 1;
    }

    public static int incrementBinary(int binary, char toAdd, int mask) {
        binary = (binary << 2) & mask;

        switch(toAdd) {
            case 'C', 'c' -> binary += 0b01;
            case 'T', 't' -> binary += 0b10;
            case 'G', 'g' -> binary += 0b11;
        }

        return binary;
    }

    public static int countMax(String sequence, String STR) {
        int max = 0;
        int STRBinary = createBinarySTR(STR);
        int[] repetitions = new int[STR.length()];
        int curSequence = createBinarySTR(sequence.substring(0, STR.length()));
        int mask = generateMask(STR.length());
        int len = sequence.length();
        int lenSTR = STR.length();

        for (int i = STR.length(); i < len; i++) {
            if (curSequence == STRBinary) {
                repetitions[i % lenSTR] += 1;
            } else {
                if (repetitions[i % lenSTR] > max) {
                    max = repetitions[i % lenSTR];
                }
                repetitions[i % lenSTR] = 0;

                if (max * lenSTR > len - i) {
                    for (int repetitionCount : repetitions) {
                        if (repetitionCount > 0) {
                            break;
                        }
                    }
                    return max;
                }

            }
            curSequence = incrementBinary(curSequence, sequence.charAt(i), mask);
        }

        if (curSequence == STRBinary) {
            repetitions[sequence.length() % STR.length()] += 1;
        }

        for (int repetitionCount : repetitions) {
            if (repetitionCount > max) {
                max = repetitionCount;
            }
        }

        return max;
    }

    public static int STRCount(String sequence, String STR) {
        return countMax(sequence, STR);
    }
}