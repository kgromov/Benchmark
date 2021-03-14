package com.home.samples.data.interview.find_pair_index;

/**
 * Created by konstantin on 21.03.2020.
 */
public class Main {
    public static void main(String[] args) {
        int [] array = {5, 1, 7};
        int [] array2 = {1, 5, 1, 5, 1};
        int k = 1;
        int k2 = 2;

        // max diff
        System.out.println("getMaxPairIndex by Map");
        PairDiffFinder pairDiffFinder = new MapPairDiffFinder();
        System.out.println(pairDiffFinder.getMaxPairIndex(array));
        System.out.println(pairDiffFinder.getMaxPairIndex(array2));

        System.out.println("getMaxPairIndex by Array");
        pairDiffFinder = new ArrayPairDiffFinder();
        System.out.println(pairDiffFinder.getMaxPairIndex(array));
        System.out.println(pairDiffFinder.getMaxPairIndex(array2));

        // max diff
        System.out.println("getMaxPairIndex by index, Map");
        pairDiffFinder = new MapPairDiffFinder();
        System.out.println(pairDiffFinder.getMaxPairIndex(array, k));
        System.out.println(pairDiffFinder.getMaxPairIndex(array, k2));
        System.out.println(pairDiffFinder.getMaxPairIndex(array2, k));
        System.out.println(pairDiffFinder.getMaxPairIndex(array2, k2));

        System.out.println("getMaxPairIndex by index, Array");
        pairDiffFinder = new ArrayPairDiffFinder();
        System.out.println(pairDiffFinder.getMaxPairIndex(array, k));
        System.out.println(pairDiffFinder.getMaxPairIndex(array, k2));
        System.out.println(pairDiffFinder.getMaxPairIndex(array2, k));
        System.out.println(pairDiffFinder.getMaxPairIndex(array2, k2));
    }
}
