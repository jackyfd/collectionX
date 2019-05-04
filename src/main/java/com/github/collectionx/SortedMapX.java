package com.github.collectionx;


import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.function.Function;

public class SortedMapX<K extends Comparable<K>, V> extends TreeMap<K, V> implements MapXContract<K, V> {

    public static <K extends Comparable<K>, V> SortedMapX<K, V> newSortedMap() {
        return new SortedMapX<>();
    }

    public static <K extends Comparable<K>, V> SortedMapX<K, V> newSortedMap(Map<? extends K, ? extends V> map) {
        SortedMapX<K, V> result = new SortedMapX<>();
        result.putAll(map);
        return result;
    }

    public static <K extends Comparable<K>, V> SortedMapX<K, V> of() {
        return new SortedMapX<>();
    }

    public static <K extends Comparable<K>, V> SortedMapX<K, V> of(K k1, V v1) {
        return SortedMapX.ofEntries(entryOf(k1, v1));
    }

    public static <K extends Comparable<K>, V> SortedMapX<K, V> of(K k1, V v1, K k2, V v2) {
        return ofEntries(entryOf(k1, v1), entryOf(k2, v2));
    }

    public static <K extends Comparable<K>, V> SortedMapX<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3) {
        return ofEntries(entryOf(k1, v1), entryOf(k2, v2), entryOf(k3, v3));
    }

    public static <K extends Comparable<K>, V> SortedMapX<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
        return ofEntries(entryOf(k1, v1), entryOf(k2, v2), entryOf(k3, v3), entryOf(k4, v4));
    }

    public static <K extends Comparable<K>, V> SortedMapX<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
        return ofEntries(entryOf(k1, v1), entryOf(k2, v2), entryOf(k3, v3), entryOf(k4, v4), entryOf(k5, v5));
    }

    public static <K extends Comparable<K>, V> SortedMapX<K, V> ofEntries(Entry<K, V>... entries) {
        SortedMapX<K, V> result = new SortedMapX<K, V>();
        for (Entry<K, V> entry : entries) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    private static <K, V> Entry<K, V> entryOf(K k, V v) {
        return new PairX<>(k, v);
    }

    public <K2, V2> MapX<K2, V2> mapEntry(Function<? super K, ? extends K2> keyFunc, Function<? super V, ? extends V2> valueFunc) {
        MapX<K2, V2> result = new MapX<>();
        for (Entry<K, V> entry : this.entrySet()) {
            result.put(keyFunc.apply(entry.getKey()), valueFunc.apply(entry.getValue()));
        }
        return result;
    }

    public <K2, V2> MultimapX<K2, V2> mapEntryWithMulti(Function<? super K, ? extends K2> keyFunc, Function<? super V, ? extends V2> valueFunc) {
        MultimapX<K2, V2> result = MultimapX.newMultimap();
        for (Entry<K, V> entry : this.entrySet()) {
            result.put(keyFunc.apply(entry.getKey()), valueFunc.apply(entry.getValue()));
        }
        return result;
    }

    public <V2> SortedMapX<K, V2> mapValue(Function<V, V2> fun) {
        SortedMapX<K, V2> result = new SortedMapX<>();
        for (Entry<K, V> entry : this.entrySet()) {

            result.put(entry.getKey(), fun.apply(entry.getValue()));
        }
        return result;
    }

    @Override
    public SetX<Entry<K, V>> entrySet() {
        return SetX.newSet(super.entrySet());
    }

    @Override
    public SetX<K> keySet() {
        return SetX.newSet(super.keySet());
    }

    @Override
    public ListX<V> values() {
        return ListX.newList(super.values());
    }


    public boolean isNotEmpty() {
        return !isEmpty();
    }

    public boolean notContainsKey(K k) {
        return !containsKey(k);
    }

    public boolean notContainsValue(V v) {
        return !containsValue(v);
    }

}
