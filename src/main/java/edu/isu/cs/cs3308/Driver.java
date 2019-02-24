package edu.isu.cs.cs3308;

import edu.isu.cs.cs3308.algorithms.ArraySearch;
import edu.isu.cs.cs3308.algorithms.impl.BinarySearch;
import edu.isu.cs.cs3308.algorithms.impl.LinearSearch;
import edu.isu.cs.cs3308.algorithms.impl.RecursiveBinarySearch;
import edu.isu.cs.cs3308.algorithms.impl.RecursiveLinearSearch;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Random;

import static java.lang.System.nanoTime;

/**
 * Driver class for the experimental simulator.
 * @author Isaac Griffith
 */
public class Driver
{
    //all my time storage arrays, I run through each algorithm 10 times
    private static long[] linearSearchTimes = new long[10];
    private static long[] recursiveLinearSearchTimes = new long[10];
    private static long[] linearBinarySearchTimes = new long[10];
    private static long[] recursiveBinarySearchTimes = new long[10];
    //only generating one random seed for use
    private static Random rand = new Random();

    public static void main(String args[])
    {
        // do the simulation using generateRandomArray()
        // report the results using report;

        //instantiating every algorithm for use later
        LinearSearch linearSearch = new LinearSearch();
        RecursiveLinearSearch recursiveLinearSearch = new RecursiveLinearSearch();
        BinarySearch linearBinarySearch = new BinarySearch();
        RecursiveBinarySearch recursiveBinarySearch = new RecursiveBinarySearch();

        for(int i = 0; i < 10; i++)
        {
            int size = 2000 + (500*i);

            runSimulation(linearSearchTimes, linearSearch, size, i);

            runSimulation(recursiveLinearSearchTimes, recursiveLinearSearch, size, i);

            runSimulation(linearBinarySearchTimes, linearBinarySearch, size, i);

            runSimulation(recursiveBinarySearchTimes, recursiveBinarySearch, size, i);
        }
        //your report function, I chose big increments for fun
        report(linearSearchTimes, recursiveLinearSearchTimes, linearBinarySearchTimes, recursiveBinarySearchTimes,2000,500);
    }
    //this is the actual timer function, main just loops through this function 10 times for each algorithm
    private static void runSimulation(long[] timesArr, ArraySearch searchAlgorithm, int size, int timerIndex)
    {
        long timeTaken = 0;

        for(int i = 0; i < 2000; i++)
        {
            Integer[] arrayToSearch = generateRandomArray(size);
            int item = rand.nextInt(2000);

            /**
             * start timer then run the algorithm
             * stops timer after and subtracts the start clock time from start
             */
            long timeStart = nanoTime();
            searchAlgorithm.search(arrayToSearch, item);
            long timeEnd = nanoTime();

            timeTaken += (timeEnd - timeStart);

        }
        //stores the time into my end array
        timesArr[timerIndex] = timeTaken/2000;

    }

    /**
     * Generates a random ordered array of integers of the provided size
     *
     * @param size Size of the random array
     * @return An array of the provided size of random numbers in ascending
     * order.
     */
    public static Integer[] generateRandomArray(int size) {
        Random rand = new Random();

        Integer[] array = new Integer[size];

        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(2000);
        }

        Arrays.sort(array);
        return array;
    }

    /**
     * Generates the output to both a Comma Separated Values file called
     * "results.csv" and to the screen.
     *
     * @param iterLinTimes Array of average values for the Iterative Linear
     * Search
     * @param recLinTimes Array of average values for the Recursive Linear
     * Search
     * @param iterBinTimes Array of average values for the Iterative Binary
     * Search
     * @param recBinTimes Array of average values for the Recursive Binary
     * Search
     * @param startIncrement Start increment for array sizes
     * @param increment Increment of array sizes.
     */
    private static void report(long[] iterLinTimes, long[] recLinTimes, long[] iterBinTimes, long[] recBinTimes, int startIncrement, int increment) {
        StringBuilder file = new StringBuilder();
        StringBuilder screen = new StringBuilder();

        screen.append(String.format("N    IterLin\tRecLin\tIterBin\tRecBin%s", System.lineSeparator()));
        file.append(String.format("N,IterLin,RecLin,IterBin,RecBin%s", System.lineSeparator()));

        for (int i = 0; i < iterLinTimes.length; i++) {
            screen.append(String.format("%d %d\t%d\t%d\t%d%s", startIncrement + (i * increment), iterLinTimes[i], recLinTimes[i], iterBinTimes[i], recBinTimes[i], System.lineSeparator()
            ));
            file.append(String.format("%d,%d,%d,%d,%d%s", startIncrement + (i * increment), iterLinTimes[i], recLinTimes[i], iterBinTimes[i], recBinTimes[i], System.lineSeparator()
            ));
        }

        System.out.println(screen.toString());

        Path p = Paths.get("results.csv");
        try {
            Files.deleteIfExists(p);
        } catch (IOException e) {

        }

        try (PrintWriter pw = new PrintWriter(Files.newBufferedWriter(p, StandardOpenOption.CREATE, StandardOpenOption.WRITE))) {
            pw.println(file.toString());
        } catch (IOException e) {

        }
    }
}
