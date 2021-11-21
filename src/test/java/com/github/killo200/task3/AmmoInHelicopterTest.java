package com.github.killo200.task3;

import org.junit.Test;

import static org.junit.Assert.*;

public class AmmoInHelicopterTest {

    AmmoInHelicopter aH = new AmmoInHelicopter();

    @Test
    public void fileShouldBeReadAndParse() {
        aH.readConditions();
        assertEquals(100, aH.capacityHelicopter);
        assertEquals(12, aH.ammoNeed.size());
    }

    @Test
    public void matrixShouldWork() {
        aH.readConditions();
        aH.matrixAmmo();
        assertEquals(11, aH.ammoGet.size());
    }
}