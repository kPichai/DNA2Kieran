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

    // charMap for DNA base O(1) lookup
    private static int[] charMap = new int['t' + 1];

    // Creates a binary hash of a given string
    public static int createBinarySTR(String STR) {
        int binarySTR = 0;

        // Loops through string, bit shifts by 2 each time then adds on the next value given by charMap
        for (int i = 0; i < STR.length(); i++) {
            binarySTR = binarySTR << 2;
            binarySTR += charMap[STR.charAt(i)];
        }

        return binarySTR;
    }

    // Initializes the map of DNA bases with their corresponding values
    public static void initializeCharMap() {
        charMap['c'] = 0b01;
        charMap['C'] = 0b01;
        charMap['t'] = 0b10;
        charMap['T'] = 0b10;
        charMap['g'] = 0b11;
        charMap['G'] = 0b11;
    }

    // Generates a mask used to avoid incorrect binary shifting
    public static int generateMask(int lenOfSTR) {
        // Takes 1, shifts it left by 2*n times (because each base takes up 2 bits) then subtracts one to finalize mask
        return (1 << (2*lenOfSTR)) - 1;
    }

    // Increments the current binary value in constant time
    public static int incrementBinary(int binary, char toAdd, int mask) {
        // Shifts binary left by two, bitwise and to remove leading numbers, then adds new value onto end
        binary = (binary << 2) & mask;
        binary += charMap[toAdd];

        return binary;
    }

    // Counts the maximum amount of STR repeats in a given sequence
    public static int countMax(String sequence, String STR) {
        int max = 0;
        // Hashes the STR and first sequence
        int STRBinary = createBinarySTR(STR);
        int curSequence = createBinarySTR(sequence.substring(0, STR.length()));
        // Array that tracks number of current repetitions of STR at any instance in the code
        int[] repetitions = new int[STR.length()];
        int mask = generateMask(STR.length());
        int len = sequence.length();
        int lenSTR = STR.length();
        initializeCharMap();

        // For loop through all of sequence
        for (int i = lenSTR; i < len; i++) {
            // Checks if you find a match of the STR and curSequence
            if (curSequence == STRBinary) {
                repetitions[i % lenSTR] += 1;
            } else {
                // If you lose a streak you check if it was greater than the max
                if (repetitions[i % lenSTR] > max) {
                    max = repetitions[i % lenSTR];
                }
                // Resets current counter at current offset
                repetitions[i % lenSTR] = 0;
                // Exit early condition if no possible way for a new max to be achieved in the remaining sequence length
                if (max * lenSTR > len - i) {
                    // Checks if nothing else is currently counting before exiting
                    for (int repetitionCount : repetitions) {
                        if (repetitionCount > 0) {
                            break;
                        }
                    }
                    return max;
                }
            }
            // Increments curSequence by one char
            curSequence = incrementBinary(curSequence, sequence.charAt(i), mask);
        }

        // Checks case if the final curSequence is a match
        if (curSequence == STRBinary) {
            repetitions[len % lenSTR] += 1;
        }

        // Checks case if our longest max repeat happens to be at the very end of the sequence
        for (int repetitionCount : repetitions) {
            if (repetitionCount > max) {
                max = repetitionCount;
            }
        }

        return max;
    }

    // Main function to return the max counted repetitions, only needs sequence to search in and STR to find
    public static int STRCount(String sequence, String STR) {
        return countMax(sequence, STR);
    }
}