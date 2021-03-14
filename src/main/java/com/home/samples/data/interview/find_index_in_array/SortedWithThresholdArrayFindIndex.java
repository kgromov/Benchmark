package com.home.samples.data.interview.find_index_in_array;

/**
 * Created by konstantin on 21.03.2020.
 */
public class SortedWithThresholdArrayFindIndex extends SortedArrayFindIndex {
    private static final int THRESHOLD = 4;

    public SortedWithThresholdArrayFindIndex(int[] array) {
        super(array);
    }

    @Override
    protected int indexOfByDivision(int value, int startIndex, int endIndex)
    {
        if (value < values[startIndex] || value > values[endIndex - 1])
        {
            return -1;
        }
        int middle = (startIndex + endIndex) / 2 + (startIndex + endIndex) % 2;
        if (value == values[middle])
        {
            return originalIndexes[middle];
        }
        for (int i = startIndex; i < endIndex && endIndex - startIndex <= THRESHOLD; i++)
        {
            if (values [i] == value)
            {
                return originalIndexes[middle];
            }
        }
        if (value < values[middle])
        {
            return indexOfByDivision(value, startIndex, middle);
        }
        else
        {
            return indexOfByDivision(value,middle + 1, endIndex);
        }
    }
}
