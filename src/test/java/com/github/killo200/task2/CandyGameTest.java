package com.github.killo200.task2;

import org.junit.Test;

import static org.junit.Assert.*;

public class CandyGameTest {

    @Test
    public void fileShouldBeRead() {
        CandyGame cg = new CandyGame();
        int[] expect = {5, 2};
        assertArrayEquals(expect, cg.readConditions());
    }

    @Test
    public void gameWithFiveCandyMaxTakeTwo() {
        CandyGame cg5To2 = new CandyGame();
        cg5To2.condition[0] = 5;
        cg5To2.candyCount = 5;
        cg5To2.maxTake = 2;

        assertEquals(3, cg5To2.game());
    }

    @Test
    public void gameWithFiveCandyMaxTakeThree() {
        CandyGame cg5To2 = new CandyGame();
        cg5To2.condition[0] = 5;
        cg5To2.candyCount = 5;
        cg5To2.maxTake = 3;

        assertEquals(3, cg5To2.game());
    }

    @Test
    public void gameWithFiveCandyMaxTakeFour() {
        CandyGame cg5To2 = new CandyGame();
        cg5To2.condition[0] = 5;
        cg5To2.candyCount = 5;
        cg5To2.maxTake = 4;

        assertEquals(4, cg5To2.game());
    }

    @Test
    public void gameWithFiveCandyMaxTakeFive() {
        CandyGame cg5To2 = new CandyGame();
        cg5To2.condition[0] = 5;
        cg5To2.candyCount = 5;
        cg5To2.maxTake = 5;

        assertEquals(5, cg5To2.game());
    }

    @Test
    public void gameWithFiveCandyMaxTakeOne() {
        CandyGame cg5To1 = new CandyGame();
        cg5To1.condition[0] = 5;
        cg5To1.candyCount = 5;
        cg5To1.maxTake = 1;

        assertEquals(4, cg5To1.game());
    }

    @Test
    public void gameWithTenCandyMaxTakeThree() {
        CandyGame cg10To3 = new CandyGame();
        cg10To3.condition[0] = 10;
        cg10To3.candyCount = 10;
        cg10To3.maxTake = 3;

        assertEquals(3, cg10To3.game());
    }

    @Test
    public void gameWithOneCandyMaxTakeOne() {
        CandyGame cg1To1 = new CandyGame();
        cg1To1.condition[0] = 1;
        cg1To1.candyCount = 1;
        cg1To1.maxTake = 1;

        assertEquals(1, cg1To1.game());
    }
}