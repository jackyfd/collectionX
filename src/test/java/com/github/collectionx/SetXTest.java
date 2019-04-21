package com.github.collectionx;

import org.junit.Test;

import static com.github.collectionx.SetX.newSet;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class SetXTest {

    private final SetX<Integer> setOne = newSet(1, 2, 3);
    private final SetX<Integer> setTwo = newSet(2, 3, 4);

    @Test
    public void toList() {
        assertThat(setOne.toList(), containsInAnyOrder(1, 2, 3));
    }

    @Test
    public void intersect() {
        assertEquals(newSet(2, 3), setOne.intersect(setTwo));
    }

    @Test
    public void union() {
        assertEquals(newSet(1, 2, 3, 4), setOne.union(setTwo));
    }

    @Test
    public void subtract() {
        assertEquals(newSet(1), setOne.subtract(setTwo));
    }
}