package com.github.collectionx;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class SetX<E> extends HashSet<E> implements CollectionX<E> {

    public static <E> SetX<E> newSet() {
        return new SetX<>();
    }

    public static <E> SetX<E> newSet(E... array) {
        return new SetX<>(array);
    }

    public static <E> SetX<E> newSet(Collection<? extends E> collection) {
        return new SetX<>(collection);
    }

    public static <E> SetX<E> with(E... array) {
        return newSet(array);
    }

    public static <E> SetX<E> from(E... array) {
        return newSet(array);
    }

    public SetX() {
    }

    public SetX(Collection<? extends E> c) {
        super(c);
    }

    @SafeVarargs
    public SetX(E... array) {
        super(Arrays.asList(array));
    }

    public SetX<E> intersect(Set<? extends E> other) {
        SetX<E> result = SetX.newSet();
        for (E e : this) {
            if (other.contains(e)) {
                result.add(e);
            }
        }
        return result;
    }

    public SetX<E> union(Set<? extends E> other) {
        SetX<E> result = SetX.newSet(this);
        result.addAll(other);
        return result;
    }

    public SetX<E> subtract(Set<? extends E> other) {
        SetX<E> result = SetX.newSet();
        for (E e : this) {
            if (!other.contains(e)) {
                result.add(e);
            }
        }
        return result;
    }

}
