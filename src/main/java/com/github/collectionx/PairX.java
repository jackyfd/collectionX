package com.github.collectionx;

import java.util.Map;

public class PairX<K, V> implements Map.Entry<K, V> {

    private K k;
    private V v;

    public static <K, V> PairX<K, V> pairOf(K k, V v) {
        return new PairX<>(k, v);
    }

    public PairX(K k, V v) {
        this.k = k;
        this.v = v;
    }

    @Override
    public K getKey() {
        return k;
    }

    @Override
    public V getValue() {
        return v;
    }

    public K first() {
        return k;
    }

    public V second() {
        return v;
    }

    @Override
    public V setValue(V value) {
        V oldValue = v;
        this.v = value;
        return oldValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PairX<?, ?> that = (PairX<?, ?>) o;

        if (k != null ? !k.equals(that.k) : that.k != null) return false;
        return v != null ? v.equals(that.v) : that.v == null;

    }

    @Override
    public int hashCode() {
        int result = k != null ? k.hashCode() : 0;
        result = 31 * result + (v != null ? v.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", k, v);
    }
}
