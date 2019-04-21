package com.github.collectionx;

import com.github.collectionx.internal.PairX;
import com.github.collectionx.internal.Preconditions;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class ListX<E> extends ArrayList<E> implements Ordered<E> {

    public static <E> ListX<E> newList() {
        return new ListX<>();
    }

    public static <E> ListX<E> newList(E ... array) {
        return new ListX<>(array);
    }

    public static <E> ListX<E> newList(Collection<? extends E> data) {
        return new ListX<>(data);
    }

    public static <E> ListX<E> newListWithCapacity(int n) {
        return new ListX<>(n);
    }

    @SafeVarargs
    public static <E> ListX<E> with(E... array) {
        return newList(array);
    }

    @SafeVarargs
    public static <E> ListX<E> from(E... array) {
        return newList(array);
    }

    public ListX() {
        super();
    }

    @SafeVarargs
    public ListX(E... array) {
        super(array.length);
        for (E e : array) {
            add(e);
        }
    }

    public ListX(Collection<? extends E> c) {
        super(c);
    }

    public ListX(int n) {
        super(n);
    }

    ////// main functional transformations


    @Override
    public <R> ListX<R> map(Function<? super E, ? extends R> fun) {
        return (ListX<R>) Ordered.super.map(fun);
    }

    @Override
    public <R> ListX<R> flatmap(Function<? super E, Collection<? extends R>> fun) {
        return (ListX<R>) Ordered.super.flatmap(fun);
    }

    @Override
    public ListX<E> filter(Predicate<? super E> predicate) {
        return (ListX<E>) Ordered.super.filter(predicate);
    }

    /**
     * partition the original by fixed sized bucket
     * @param n: max size of bucket
     * @return list of buckets of elements
     */
    public ListX<ListX<E>> partition(int n) {
        ListX<ListX<E>> result = newListWithCapacity(size() / n + 1);
        ListX<E> buckets = null;
        for (E e : this) {
            if (buckets == null) {
                buckets = newListWithCapacity(n);
            }
            buckets.add(e);
            if (buckets.size() == n) {
                result.add(buckets);
                buckets = null;
            }
        }
        if (buckets != null) {
            result.add(buckets);
        }
        return result;
    }

    public Map.Entry<ListX<E>, ListX<E>> partition(Predicate<? super E> predicate) {
        return PairX.pairOf(filter(predicate), filter(predicate.negate()));
    }

    @Override
    public ListX<E> take(int n) {
        return (ListX<E>) Ordered.super.take(n);
    }

    @Override
    public ListX<E> takeWhile(Predicate<? super E> predicate) {
        return (ListX<E>) Ordered.super.takeWhile(predicate);
    }

    @Override
    public ListX<E> drop(int n) {
        return (ListX<E>) Ordered.super.drop(n);
    }

    @Override
    public ListX<E> dropWhile(Predicate<? super E> predicate) {
        return (ListX<E>) Ordered.super.dropWhile(predicate);
    }

    public E head() {
        return get(0);
    }

    public ListX<E> tail() {
        ListX<E> result = newListWithCapacity(size() - 1);
        for (int i = 0; i < this.size(); i++) {
            if (i > 0) {
                result.add(this.get(i));
            }
        }
        return result;
    }


    public ListX<E> sortWith(Comparator<? super E> comparator) {
        ListX<E> result = newList(this);
        result.sort(comparator);
        return result;
    }

    public <E2> ListX<Map.Entry<E, E2>> zip(List<E2> other) {
        Preconditions.checkArgument(this.size() == other.size(), "size should match: " + this.size() + " != " + other.size());
        ListX<Map.Entry<E, E2>> result = newListWithCapacity(size());
        for (int i = 0; i < this.size(); i++) {
            E e = this.get(i);
            result.add(new PairX<>(e, other.get(i)));
        }
        return result;
    }

    public <R> R folder(R seed, BiFunction<? super R, ? super E, ? super R> func) {
        R result = seed;
        for (E e : this) {
            result = (R) func.apply(result, e);
        }
        return result;
    }

    public E reduce(BiFunction<? super E, ? super E, ? super E> func) {
        return tail().folder(head(), func);
    }

    public ListX<E> distinct(){
        return toSet().toList();
    }

    ///// deprecate the methods with side effects

    @Override
    @Deprecated
    public boolean removeIf(Predicate<? super E> filter) {
        return super.removeIf(filter);
    }

    @Override
    @Deprecated
    public void replaceAll(UnaryOperator<E> operator) {
        super.replaceAll(operator);
    }

    @Override
    @Deprecated
    public void sort(Comparator<? super E> c) {
        super.sort(c);
    }

    // util functions

    public ListX<E> reverse() {
        ListX<E> result = newList(this);
        Collections.reverse(result);
        return result;
    }

    public ListX<E> join(Iterable<? extends E> other) {
        ListX<E> result = newList(this);
        for (E e : other) {
            result.add(e);
        }
        return result;
    }

}
