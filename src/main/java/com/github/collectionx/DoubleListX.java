package com.github.collectionx;

import java.util.ArrayList;

public class DoubleListX extends ArrayList<Double> implements CollectionX<Double> {

    public double sum() {
        return folder(0.0,  (a, b) -> a + b);
    }

}
