package game.tools;

import java.util.Random;

/**
 * A random number generator
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Amanda Goh, Samantha Oh Jia-Jia, Tan Yi Jin
 *
 */
public class RandomNumberGenerator {
    /**
     * A random number generator function
     * @param bound the maximum number that can be generated
     * @return a generated random integer from 0 to bound
     */
    public static int getRandomInt(int bound) {
        return bound > 0 ? new Random().nextInt(bound) : 0;
    }

    /**
     * A random number generator function
     * @param lowerBound the minimum number that can be generated
     * @param upperBound the maximum number that can be generated
     * @return a generated random integer from lowerBound to upperBound
     */
    public static int getRandomInt(int lowerBound, int upperBound) {
        int range = upperBound - lowerBound + 1;
        return new Random().nextInt(range) + lowerBound;
    }
}
