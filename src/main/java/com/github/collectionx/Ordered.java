package com.github.collectionx;

import com.github.collectionx.internal.Preconditions;

import java.util.Optional;
import java.util.function.Predicate;

public interface Ordered<E> extends CollectionX<E> {

    default E head() {
        if(isEmpty()) {
            throw new IllegalStateException("empty collection");
        }
        return iterator().next();
    }

    default Optional<E> headOption() {
        if(isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(head());
    }

    default Ordered<E> take(int n) {
        Preconditions.checkArgument(n > 0, "n should be great than zero: " + n);
        ListX<E> result = new ListX<E>(n);
        int i = 0;
        for (E e : this) {
            if (i < n) {
                result.add(e);
            }
            i++;
        }
        return result;
    }

    default Ordered<E> takeWhile(Predicate<? super E> predicate) {
        ListX<E> result = new ListX<>();
        for (E e : this) {
            if (predicate.test(e)) {
                result.add(e);
            } else {
                break;
            }
        }
        return result;
    }

    default Ordered<E> drop(int n) {
        Preconditions.checkArgument(n > 0, "n should be great than zero: " + n);
        if (n >= size()) {
            return ListX.newList();
        }
        ListX<E> result = ListX.newList();
        int i = 0;
        for (E e : this) {
            if (i >= n) {
                result.add(e);
            }
            i++;
        }
        return result;
    }

    default Ordered<E> dropWhile(Predicate<? super E> predicate) {
        ListX<E> result = ListX.newList();
        boolean skip = true;
        for (E e : this) {
            if (skip) {
                if (!predicate.test(e)) {
                    skip = false;
                }
            }
            if (!skip) {
                result.add(e);
            }
        }
        return result;
    }


}
