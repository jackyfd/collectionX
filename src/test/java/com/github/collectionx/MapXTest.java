package com.github.collectionx;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class MapXTest {

    private MapX<String, Integer> mapOne = MapX.of("100", 1, "200", 2, "3000", 1);
    private SortedMapX<String, Integer> mapTwo = SortedMapX.of("100", 1, "200", 2, "3000", 1);

    @Test
    public void newMap() {
        Map<String, String> m = new HashMap<>();
        m.put("1", "1");
        MapX<String, String> mapX = MapX.newMap(m);
        assertEquals(m, mapX);
    }

    @Test
    public void mapEntry() {
        MapX<Integer, Integer> result = mapOne.mapEntry(k -> k.length(), v -> v + 10);
        assertEquals(MapX.of(3, 12, 4, 11), result);
    }

    @Test
    public void mapKey() {
        assertEquals(MapX.of(3, 2, 4, 1), mapOne.mapKey(key -> key.length()));
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
    public void filterValue() {
        assertEquals(MapX.of("100", 1, "3000", 1), mapOne.filterValue(v -> v.equals(1)));
    }

    @Test
    public void filterEntry() {
        assertEquals(MapX.of("100", 1), mapOne.filter(entry -> entry.getKey().equals("100")));
    }

    @Test
    public void flip() {
        assertEquals(2, mapOne.flip().size());
    }

}