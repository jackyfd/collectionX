package com.github.collectionx;

import org.junit.Test;

import static org.junit.Assert.*;

public class MultimapXTest {

    private MapX<String, Integer> mapX = MapX.of("100", 1, "200", 2, "3000", 1);

    @Test
    public void fromMap(){
        MultimapX<String, Integer> stringIntegerMultimapX = MultimapX.newMultimap(mapX);
    }

}