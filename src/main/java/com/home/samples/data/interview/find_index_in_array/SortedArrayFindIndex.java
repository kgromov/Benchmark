package com.home.samples.data.interview.find_index_in_array;

import java.util.stream.IntStream;

/**
 * Created by konstantin on 21.03.2020.
 */
public class SortedArrayFindIndex implements FindIndex {
    protected final int[] values;
    protected final int[] originalIndexes;

    public SortedArrayFindIndex(int[] array) {
        this.values = array;
        this.originalIndexes = IntStream.range(0, array.length).toArray();
        bubbleSort();
    }

    private void bubbleSort() {
        boolean sorted = false;
        int temp;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < values.length - 1; i++) {
                if (values[i] > values[i + 1]) {
                    temp = values[i];
                    values[i] = values[i + 1];
                    values[i + 1] = temp;

                    temp = originalIndexes[i];
                    originalIndexes[i] = originalIndexes[i + 1];
                    originalIndexes[i + 1] = temp;
                    sorted = false;
                }
            }
        }
    }

    @Override
    public int indexOf(int value) {
        return indexOfByDivision(value, 0, values.length);
    }

    protected int indexOfByDivision(int value, int startIndex, int endIndex) {
        if (value < values[startIndex] || value > values[endIndex - 1]) {
            return -1;
        }
        int middle = (startIndex + endIndex) / 2 + (startIndex + endIndex) % 2;
        if (value == values[middle]) {
            return originalIndexes[middle];
        }
        if (endIndex - startIndex <= 1) {
            return -1;
        }
        if (value < values[middle]) {
            return indexOfByDivision(value, startIndex, middle);
        } else {
            return indexOfByDivision(value, middle + 1, endIndex);
        }
    }
}
