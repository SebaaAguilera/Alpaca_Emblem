package model;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tactician tests set
 * the GameController methods includes all the Tactician methods, so, is
 * unnecessary to build up an entire bunch of tests here :c
 */
public class TacticianTest {
    Tactician testTactician;

    @Test
    public void testName(){
        testTactician = new Tactician("Player");
        assertEquals("Player", testTactician.getName()); }

}
