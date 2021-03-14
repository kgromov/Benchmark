package com.home.samples.data.interview.find_index_in_array;

/**
 * Created by konstantin on 21.03.2020.
 */
public class ArrayFindIndex implements FindIndex {

    private final int [] array;

    public ArrayFindIndex(int[] array) {
        this.array = array;
    }

    @Override
    public int indexOf(int value) {
        for (int i = 0; i < array.length; i++)
        {
            if (array[i] == value)
            {
                return i;
            }
        }
        return -1;
    }
}
