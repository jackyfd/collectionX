package com.github.collectionx;


import java.util.Collection;
import java.util.function.Function;
import java.util.function.Predicate;

import static com.github.collectionx.ListX.newListWithCapacity;


public interface CollectionX<E> extends Collection<E> {

    default CollectionX<E> filter(Predicate<? super E> predicate) {
        ListX<E> result = newListWithCapacity(this.size());
        for (E e : this) {
            if (predicate.test(e)) {
                result.add(e);
            }
        }
        return result;
    }

    default int count(Predicate<? super E> predicate) {
        int matches = 0;
        for (E e : this) {
            if (predicate.test(e)) {
                matches ++;
            }
        }
        return matches;
    }

    default <R> CollectionX<R> map(Function<? super E, ? extends R> fun) {
        ListX<R> result = newListWithCapacity(this.size());
        for (E e : this) {
            result.add(fun.apply(e));
        }
        return result;
    }

    default <R> CollectionX<R> flatmap(Function<? super E, Collection<? extends R>> fun) {
        ListX<R> result = newListWithCapacity(this.size());
        for (E e : this) {
            result.addAll(fun.apply(e));
        }
        return result;
    }


    default ListX<E> toList() {
        return ListX.newList(this);
    }

    default SetX<E> toSet() {
        return SetX.newSet(this);
    }

    default <K> MapX<K, E> groupBy(Function<E, K> keyFun) {
        MapX<K, E> result = MapX.newMap();
        for (E e : this) {
            result.put(keyFun.apply(e), e);
        }
        return result;
    }

    default <K> MultimapX<K, E> groupByWithMutiValue(Function<? super E, K> keyFun) {
        MultimapX<K, E> result = new MultimapX<>();
        for (E e : this) {
            result.put(keyFun.apply(e), e);
        }
        return result;
    }

    default boolean any(Predicate<? super E> predicate) {
        for (E e : this) {
            if (predicate.test(e)) {
                return true;
            }
        }
        return false;
    }

    default boolean all(Predicate<? super E> predicate) {
        for (E e : this) {
            if (!predicate.test(e)) {
                return false;
            }
        }
        return true;
    }

    default boolean isNotEmpty() {
        return !isEmpty();
    }

    default boolean notContains(E e) {
        return !contains(e);
    }

    default boolean notContainsAll(Collection<? extends E> c) {
        return !containsAll(c);
    }

    default String mkString(String seperator) {
        return mkString(seperator, "", "");
    }

    default String mkString(String seperator, String start, String end) {
        StringBuilder sb = new StringBuilder(start);
        int i = 0;
        for (E e : this) {
            sb.append(e);
            if (i > 0 && i < size() - 1) {
                sb.append(seperator);
            }
            if (i == size() - 1) {
                sb.append(end);
            }
            i++;
        }
        return sb.toString();
    }

}
