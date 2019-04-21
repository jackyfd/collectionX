package com.github.collectionx;

import org.junit.Test;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class SortedSetXTest {

    private final SortedSetX<Integer> setOne = SortedSetX.newSet(1, 2, 3);
    private final SortedSetX<Integer> setTwo = SortedSetX.newSet(2, 3, 4);

    @Test
    public void intersect() {
        assertEquals(SortedSetX.newSet(2, 3), setOne.intersect(setTwo));
    }

    @Test
    public void union() {
        assertEquals(SortedSetX.newSet(1, 2, 3, 4), setOne.union(setTwo));
    }

    @Test
    public void subtract() {
        assertEquals(SortedSetX.newSet(1), setOne.subtract(setTwo));
    }

    @Test
    public void toList() {
        assertThat(setOne.toList(), containsInAnyOrder(1, 2, 3));
    }

    @Test
    public void groupBy() {
        assertEquals(MapX.of("1", 1, "2", 2, "3", 3), setOne.groupBy(i -> i.toString()));
    }
}