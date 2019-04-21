package com.github.collectionx;

import com.github.collectionx.internal.PairX;
import org.junit.Test;

import static com.github.collectionx.ListX.with;
import static com.github.collectionx.internal.PairX.pairOf;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.*;

public class ListXTest {

    private final ListX<Integer> listOne = with(1, 2, 3);
    private final ListX<Integer> listTwo = with(1, 2, 3, 4, 1);
    private final ListX<Integer> emptyList = with();

    @Test
    public void count() {
        assertEquals(2, listOne.count(it -> it >= 2));
    }

    @Test
    public void head() {
        assertEquals(1, listOne.head().intValue());
    }

    @Test
    public void any() {
        assertFalse(listOne.any(it -> it > 3));
        assertTrue(listOne.any(it -> it > 2));
    }

    @Test
    public void all() {
        assertFalse(listOne.all(it -> it > 2));
        assertTrue(listOne.any(it -> it > 0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void headOfEmptyLIst() {
        assertEquals(1, emptyList.head().intValue());
    }

    @Test
    public void tail() {
        assertEquals(with(2, 3), listOne.tail());
    }

    @Test
    public void partition() {
        assertEquals(with(with(1, 2), with(3)), listOne.partition(2));
    }

    @Test
    public void partitionPerfectly() {
        ListX<Integer> listTwo = with(1, 2, 3, 4);
        assertEquals(with(with(1, 2), with(3, 4)), listTwo.partition(2));
    }

    @Test
    public void partitionByCondition() {
        assertEquals(pairOf(with(1), with(2, 3)), listOne.partition(x -> x < 2));
    }

    @Test
    public void folder() {
        assertEquals(16, listOne.<Integer>folder(10, (x, y) -> x + y).intValue());
    }

    @Test
    public void reduce() {
        assertEquals(6, listOne.<Integer>reduce((x, y) -> x + y).intValue());
    }

    @Test
    public void zip() {
        ListX<PairX<Integer, String>> expected = with(pairOf(1, "one"), pairOf(2, "two"), pairOf(3, "three"));
        assertEquals(expected, listOne.zip(with("one", "two", "three")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void zipWithDifferentSize() {
        listOne.zip(emptyList);
    }

    @Test
    public void join() {
        assertEquals(with(1, 2, 3, 1, 2, 3, 4, 1), listOne.join(listTwo));
    }

    @Test
    public void dropWhile() {
        ListX<Integer> integers = listTwo.dropWhile(v -> v < 3);
        assertEquals(with(3, 4, 1), integers);
    }

    @Test
    public void toSet() {
        assertEquals(SetX.with(1, 2, 3, 4), listTwo.toSet());
    }

    @Test
    public void reverse() {
        assertEquals(with(3, 2, 1), listOne.reverse());
    }

    @Test
    public void sorted() {
        assertEquals(with(1, 1, 2, 3, 4), listTwo.sortWith((x, y) -> x.compareTo(y)));
    }

    @Test
    public void distinct() {
        assertThat(listOne.join(listOne).distinct(), containsInAnyOrder(1,2,3));
    }
}