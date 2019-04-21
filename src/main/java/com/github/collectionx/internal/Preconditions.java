package com.github.collectionx.internal;

public class Preconditions {

    private Preconditions() {
    }

    public static void checkArgument(boolean expression, String errorMsg) {
        if (!expression) {
            throw new IllegalArgumentException(errorMsg);
        }
    }

}
