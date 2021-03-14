package com.home.samples.collections;

import java.util.*;

/**
 * Created by konstantin on 04.10.2020.
 */
public class InitCollectionData {
    private static InitCollectionData INSTANCE;

    private Map<Integer, List<String>> data;

    private InitCollectionData(int [] sizes) {
        data = new HashMap<>(sizes.length);
        Arrays.stream(sizes).forEach(size -> data.put(size, getData(size)));
    }

    public static InitCollectionData getInstance(int [] sizes) {
        if (INSTANCE == null) {
            synchronized (InitCollectionData.class) {
                if (INSTANCE == null) {
                    INSTANCE = new InitCollectionData(sizes);
                }
            }
        }
        return INSTANCE;
    }

    private List<String> getData(int size) {
        List<String> data = new ArrayList<>(size);
//        ThreadLocalRandom random = ThreadLocalRandom.current();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            int nextInt = random.nextInt(size);
            data.add(String.valueOf(nextInt));
        }
        System.out.println(String.format("N = %d, unique values = %d", size, new HashSet<>(data).size()));
        return data;
    }

    public Map<Integer, List<String>> getData() {
        return Collections.unmodifiableMap(data);
    }
}
