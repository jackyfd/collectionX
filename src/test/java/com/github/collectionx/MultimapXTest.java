package com.github.collectionx;

import org.junit.Test;

import static org.junit.Assert.*;

public class MultimapXTest {

    private MapX<String, Integer> mapX = MapX.of("100", 1, "200", 2, "3000", 1);

    @Test
    public void fromMap(){
        MultimapX<String, Integer> multimapX = MultimapX.newMultimap(mapX);
        assertEquals(3, multimapX.size());
        assertEquals(SetX.newSet(1), multimapX.getOrCreate("100"));
        assertEquals(SetX.newSet(2), multimapX.getOrCreate("200"));
        assertEquals(SetX.newSet(1), multimapX.getOrCreate("3000"));
    }

    @Test
    public void countByKey(){
        MultimapX<String, Integer> multimapX = MultimapX.newMultimap(mapX);
        assertEquals(1, multimapX.countByKey(it -> it.length() > 3));
    }


    @Test
    public void putAndRemove(){
        MultimapX<String, Integer> multimapX = MultimapX.newMultimap();
        multimapX.put("100", 1);
        multimapX.put("200", 2);
        assertEquals(2, multimapX.size());
        assertFalse(multimapX.remove("300"));
        assertTrue(multimapX.remove("100"));
        assertEquals(1, multimapX.size());
    }

}