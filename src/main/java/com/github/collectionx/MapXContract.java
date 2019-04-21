package com.github.collectionx;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public interface MapXContract<K, V> extends Map<K, V> {

    default  <K2> MapX<K2, V> mapKey(Function<K, K2> fun) {
        MapX<K2, V> result = new MapX<>();
        for (Map.Entry<K, V> entry : this.entrySet()) {
            result.put(fun.apply(entry.getKey()), entry.getValue());
        }
        return result;
    }

    default MapX<V, K> flip() {
        MapX<V, K> result = MapX.newMap();
        for (Entry<K, V> entry : this.entrySet()) {
            result.put(entry.getValue(), entry.getKey());
        }
        return result;
    }

    default int count(Predicate<Entry<K, V>> predicate) {
        int total = 0;
        for (Entry<K, V> entry : entrySet()) {
            if(predicate.test(entry)) {
                total ++;
            }
        }
        return total;
    }

    default boolean isNotEmpty() {
        return !isEmpty();
    }

    default boolean notContainsKey(K k) {
        return !containsKey(k);
    }

    default boolean notContainsValue(V v) {
        return !containsValue(v);
    }
}
