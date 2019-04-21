package com.github.collectionx;



import com.github.collectionx.internal.PairX;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public class MapX<K, V> extends HashMap<K, V> {

    public static <K, V> MapX<K, V> newMap() {
        return new MapX<>();
    }

    public static <K, V> MapX<K, V> newMap(Map<K,V> map) {
        MapX<K, V> result = new MapX<>();
        result.putAll(map);
        return result;
    }

    public static <K, V> MapX<K, V> of() {
        return new MapX<>();
    }

    public static <K, V> MapX<K, V> of(K k1, V v1) {
        return ofEntries(entryOf(k1, v1));
    }

    public static <K, V> MapX<K, V> of(K k1, V v1, K k2, V v2) {
        return ofEntries(entryOf(k1, v1), entryOf(k2, v2));
    }

    public static <K, V> MapX<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3) {
        return ofEntries(entryOf(k1, v1), entryOf(k2, v2), entryOf(k3, v3));
    }

    public static <K, V> MapX<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
        return ofEntries(entryOf(k1, v1), entryOf(k2, v2), entryOf(k3, v3), entryOf(k4, v4));
    }

    public static <K, V> MapX<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
        return ofEntries(entryOf(k1, v1), entryOf(k2, v2), entryOf(k3, v3), entryOf(k4, v4), entryOf(k5, v5));
    }

    public static <K, V> MapX<K, V> ofEntries(Entry<K, V>... entries) {
        MapX<K, V> result = new MapX<K, V>();
        for (Entry<K, V> entry : entries) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    private static <K, V> Entry<K, V> entryOf(K k, V v) {
        return new PairX<>(k, v);
    }

    public MapX<K, V> filter(Predicate<Entry<?super K, ?super V>>predicate) {
        MapX<K, V> result = MapX.newMap();
        for (Entry<K, V> entry : this.entrySet()) {
            if(predicate.test(entry)) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        return result;
    }

    public MapX<K, V> filterKey(Predicate<? super K>predicate) {
        MapX<K, V> result = MapX.newMap();
        for (Entry<K, V> entry : this.entrySet()) {
            if(predicate.test(entry.getKey())) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        return result;
    }

    public MapX<K, V> filterValue(Predicate<? super V>predicate) {
        MapX<K, V> result = MapX.newMap();
        for (Entry<K, V> entry : this.entrySet()) {
            if(predicate.test(entry.getValue())) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        return result;
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

    public <K2> MapX<K2, V> mapKey(Function<K, K2> fun) {
        MapX<K2, V> result = new MapX<>();
        for (Entry<K, V> entry : this.entrySet()) {
            result.put(fun.apply(entry.getKey()), entry.getValue());
        }
        return result;
    }


    public <V2> MapX<K, V2> mapValue(Function<V, V2> fun) {
        MapX<K, V2> result = new MapX<>();
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
