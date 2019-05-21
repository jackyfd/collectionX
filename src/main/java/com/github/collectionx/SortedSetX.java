package com.github.collectionx;

import java.util.Collection;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;

public class SortedSetX<E extends Comparable<E>> extends TreeSet<E> implements Ordered<E> {

    public static <E extends Comparable<E>> SortedSetX<E> newSet(E... array) {
        return new SortedSetX<>(array);
    }

    public static <E extends Comparable<E>> SortedSetX<E> newSet(Collection<? extends E> collection) {
        return new SortedSetX<>(collection);
    }

    public static <E extends Comparable<E>> SortedSetX<E> with(E ... array) {
        return newSet(array);
    }

    public static <E extends Comparable<E>> SortedSetX<E> from(E ... array) {
        return with(array);
    }

    public SortedSetX() {
    }

    public SortedSetX(Comparator<? super E> comparator) {
        super(comparator);
    }

    public SortedSetX(Collection<? extends E> c) {
        super(c);
    }

    public SortedSetX(E... array) {
        super();
        for (E e : array) {
            add(e);
        }
    }

    @Override
    public SortedSetX<E> take(int n) {
        return SortedSetX.newSet(Ordered.super.take(n));
    }

    @Override
    public SortedSetX<E> takeWhile(Predicate<? super E> predicate) {
        return SortedSetX.newSet(Ordered.super.takeWhile(predicate));
    }

    @Override
    public SortedSetX<E> drop(int n) {
        return SortedSetX.newSet(Ordered.super.drop(n));
    }

    @Override
    public SortedSetX<E> dropWhile(Predicate<? super E> predicate) {
        return SortedSetX.newSet(Ordered.super.dropWhile(predicate));
    }

    public SortedSetX<E> intersect(Set<?extends E> other) {
        SortedSetX<E> result = SortedSetX.newSet();
        for (E e : this) {
            if (other.contains(e)) {
                result.add(e);
            }
        }
        return result;
    }

    public SortedSetX<E> union(Set<? extends E> other) {
        SortedSetX<E> result = SortedSetX.newSet(this);
        result.addAll(other);
        return result;
    }

    public SortedSetX<E> subtract(Set<? extends E> other) {
        SortedSetX<E> result = SortedSetX.newSet();
        for (E e : this) {
            if (!other.contains(e)) {
                result.add(e);
            }
        }
        return result;
    }
}
