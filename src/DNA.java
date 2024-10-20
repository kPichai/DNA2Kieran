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

    public static int incrementBinary(int binary, char toAdd) {
        binary = binary << 2;
        if (toAdd == 'A' || toAdd == 'a') {
            binary += 0b00;
        } else if (toAdd == 'C' || toAdd == 'c') {
            binary += 0b01;
        } else if (toAdd == 'T' || toAdd == 't') {
            binary += 0b10;
        } else if (toAdd == 'G' || toAdd == 'g') {
            binary += 0b11;
        }
        return binary;
    }

    public static int countMax(String sequence, String STR) {
        int max = 0;
        int temp = 0;
        int STRBinary = createBinarySTR(STR);
        int curSequence = createBinarySTR(sequence.substring(0, STR.length()));
        Queue<Integer> possibleBeginningSTR = new LinkedList<Integer>();

        for (int i = STR.length(); i < sequence.length() - STR.length(); i++) {
            curSequence = incrementBinary(curSequence, sequence.charAt(i));
            if (curSequence == STRBinary) {
                temp += 1;
                for (int j = 0; j < STR.length(); j++) {
                    i++;
                    curSequence = incrementBinary(curSequence, sequence.charAt(i));
                    if (curSequence == STRBinary) {
                        if (possibleBeginningSTR.isEmpty() || possibleBeginningSTR.peek() + STR.length() != i) {
                            possibleBeginningSTR.add(i);
                        }
                    }
                }
            } else {
                if (temp > 0 && temp > max) {
                    max = temp;
                    temp = 0;
                }
                if (!possibleBeginningSTR.isEmpty()) {
                    i = possibleBeginningSTR.remove();
                    curSequence = createBinarySTR(sequence.substring(i, i + STR.length()));
                }
            }
        }

        return max;
    }

    public static int STRCount(String sequence, String STR) {
        return countMax(sequence, STR);
    }
}
