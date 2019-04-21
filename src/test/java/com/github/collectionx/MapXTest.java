package com.github.collectionx;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MapXTest {

    private MapX<String, Integer> mapOne = MapX.of("100", 1, "200", 2, "3000", 1);

    @Test
    public void mapEntry() {
        MapX<Integer, Integer> result = mapOne.mapEntry(k -> k.length(), v -> v + 10);
        assertEquals(MapX.of(3, 12, 4, 11), result);
    }

    @Test
    public void mapValue() {
        MapX<String, Integer> result = mapOne.mapValue(v -> v * 10);
        assertEquals(MapX.of("100", 10, "200", 20, "3000", 10), result);
    }

    @Test
    public void filterKey() {
        assertEquals(MapX.of("100", 1), mapOne.filterKey(k -> k.equals("100")));
    }

    @Test
    public void filterEntry() {
        assertEquals(MapX.of("100", 1), mapOne.filter(entry -> entry.getKey().equals("100")));
    }

}