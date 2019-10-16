package model;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TacticianTest {
    Tactician testTactician;

    @BeforeEach
    public void setTactician(){
        testTactician = new Tactician("Player")
    }

    @Test
    public void testName(){
        assertEquals("Player", testTactician.getName());
    }
}
