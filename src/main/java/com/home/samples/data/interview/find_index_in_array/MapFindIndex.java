package com.home.samples.data.interview.find_index_in_array;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by konstantin on 21.03.2020.
 */
public class MapFindIndex implements FindIndex {
    private final Map<Integer, Integer> map;

    public MapFindIndex(int [] array) {
        this.map = Arrays.stream(array, 0 , array.length)
                .boxed()
                .collect(Collectors.toMap(i -> array[i], Function.identity(), (v1, v2) -> v1));
    }

    @Override
    public int indexOf(int value) {
        return map.getOrDefault(value, -1);
    }
}
