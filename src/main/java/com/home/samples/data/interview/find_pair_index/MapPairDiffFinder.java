package com.home.samples.data.interview.find_pair_index;

import java.util.Comparator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * Created by konstantin on 21.03.2020.
 */
public class MapPairDiffFinder implements PairDiffFinder {

    @Override
    public int getMaxPairIndex(int[] array) {
        NavigableMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < array.length - 1; i++) {
            int diff = Math.abs(array[i] - array[i + 1]);
            map.putIfAbsent(diff, i);
        }
        return map.lastEntry().getValue();
    }

    @Override
    public int getMaxPairIndex(int[] array, int k) {
        NavigableMap<Integer, Integer> map = new TreeMap<>(Comparator.reverseOrder());
        for (int i = 0; i < array.length - 1; i++) {
            int diff = Math.abs(array[i] - array[i + 1]);
            map.putIfAbsent(diff, i);
        }
        return k >= map.size()
                ? map.lastEntry().getValue()
                : map.entrySet().stream()
                .skip(k - 1)
                .findFirst()
                .map(Map.Entry::getValue)
                .orElse(0);
    }
}
