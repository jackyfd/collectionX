package com.github.collectionx;

import java.util.ArrayList;

public class IntListX extends ArrayList<Integer> implements CollectionX<Integer> {



    public int sum() {
        return folder(0,  (a, b) -> a + b);
    }

}
