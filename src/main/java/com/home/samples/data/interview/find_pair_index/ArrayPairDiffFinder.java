package com.home.samples.data.interview.find_pair_index;

/**
 * Created by konstantin on 21.03.2020.
 */
public class ArrayPairDiffFinder implements PairDiffFinder {

    @Override
    public int getMaxPairIndex(int[] array) {
        int maxDiff = 0;
        int pairIndex = 0;
        for (int i = 0; i < array.length - 1 ; i++)
        {
            int diff = Math.abs(array[i] - array[i + 1]);
            if (diff > maxDiff)
            {
                pairIndex = i;
                maxDiff = diff;
            }
        }
        return pairIndex;
    }

    @Override
    public int getMaxPairIndex(int[] array, int k) {
        int maxDiff = 0;
        int pairIndex = -1;
        int iter = 0;
        while (iter != k)
        {
            int maxDiffInternal = 0;
            for (int i = 0; i < array.length - 1 && i != pairIndex ; i++)
            {
                int diff = Math.abs(array[i] - array[i + 1]);
                if (diff > maxDiffInternal && (iter == 0 || maxDiffInternal < maxDiff && diff < maxDiff))
                {
                    pairIndex = i;
                    maxDiffInternal = diff;
                }
            }
            maxDiff = maxDiffInternal;
            ++iter;
        }
        return pairIndex;
    }
}
