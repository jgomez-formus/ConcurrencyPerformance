/**
 * Evaluating parallel performance
 *
 * @author Juan Gomez Blandon
 * @email jgomezblandon@mail.valenciacollege.edu
 *
 */
package ConcurrencyPerformance;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Random rn = new Random();

        int[] randomNumbersArray = new int[200000000];

        for (int i = 0; i < randomNumbersArray.length; i++) {
            randomNumbersArray[i] = rn.nextInt(10) + 1;
        }

        long startTime = System.currentTimeMillis();
        System.out.println("Sum Total Multiple Thread: " + Sum.parallelSum(randomNumbersArray));
        System.out.println("Time Elapsed: " + (System.currentTimeMillis() - startTime) + " milliseconds");

        startTime = System.currentTimeMillis();
        System.out.println("\nSum Total Single Thread: " + Sum.sumSingleThread(randomNumbersArray));
        System.out.println("Time Elapsed: " + (System.currentTimeMillis() - startTime) + " milliseconds\n\n");
    }
}
