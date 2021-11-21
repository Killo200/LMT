package com.github.killo200.task1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class UnderAttackTest {



    @Test
    public void fileShouldBeRead() {
        UnderAttack uA = new UnderAttack();
        uA.readConditions();
        assertTrue(uA.conditions.size() > 0);
    }

    @Test
    public void answerShouldBeGet() {
        UnderAttack uA = new UnderAttack();
        List<Long> expected = Arrays.asList(0L, 100L, 0L, 173L);
        assertEquals(expected, uA.answer());
    }
}