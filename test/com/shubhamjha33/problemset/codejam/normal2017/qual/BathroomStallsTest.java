package com.shubhamjha33.problemset.codejam.normal2017.qual;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BathroomStallsTest {

    @Test
    public void testStallFunctions() throws Exception {
        BathroomStalls.Stall stall = new BathroomStalls.Stall(1, 6);
        assertEquals(3, stall.getStallNumber());
        assertEquals(1, stall.getLs());
        assertEquals(2, stall.getRs());
        stall = new BathroomStalls.Stall(1, 7);
        assertEquals(4, stall.getStallNumber());
        assertEquals(2, stall.getLs());
        assertEquals(2, stall.getRs());
    }


}