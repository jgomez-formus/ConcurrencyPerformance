/**
 * Evaluating parallel performance
 *
 * @author Juan Gomez Blandon
 * @email jgomezblandon@mail.valenciacollege.edu
 *
 */

package ConcurrencyPerformance;

public class Sum extends Thread {
    private final int[] randomNumbersArray;

    private final int startIndex;
    private final int endIndex;
    private int tmpTotal;

    public Sum(int[] randomNumbersArray, int startIndex, int endIndex) {
        this.randomNumbersArray = randomNumbersArray;
        this.startIndex = startIndex;
        this.endIndex = Math.min(endIndex, randomNumbersArray.length);

    }

    public static int sumSingleThread(int[] randomNumbersArray) {
        int total = 0;

        for (int i = 0; i < randomNumbersArray.length; i++) {
            total += randomNumbersArray[i];
        }

        return total;
    }



    public static int parallelSum(int[] randomNumbersArray) {
        return parallelSum(randomNumbersArray, Runtime.getRuntime().availableProcessors());
    }

    public static int parallelSum(int[] randomNumbersArray, int threads) {

        System.out.println("\nThere are " + threads + " processors available for parallel execution!");

        // finds the size for each batch
        int batchSize = (int) Math.ceil(randomNumbersArray.length * 1.0 / threads);
        System.out.println("batchSize: " + batchSize);

        Sum[] sums = new Sum[threads];

        for (int i = 0; i < threads; i++) {
            sums[i] = new Sum(randomNumbersArray, i * batchSize, (i + 1) * batchSize);
            sums[i].start();
        }

        try {
            for (Sum sum : sums) {
                sum.join();
            }

        } catch (InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }

        int total = 0;

        for (Sum sum : sums) {
            total += sum.getTmpTotal();
        }

        return total;

    }

    public int getTmpTotal() {
        return this.tmpTotal;
    }


    public static int sumChunk(int[] randomNumbersArray, int startIndex, int endIndex) {

        int total = 0;
        for (int i = startIndex; i < endIndex; i++) {
            total += randomNumbersArray[i];
        }

        return total;
    }

    public void run() {
        this.tmpTotal = sumChunk(randomNumbersArray, startIndex, endIndex);
    }
}
