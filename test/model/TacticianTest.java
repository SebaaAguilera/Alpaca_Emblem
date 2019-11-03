package model;

import model.map.Field;
import model.Tactician;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TacticianTest {
    Tactician testTactician;
    Field map;

    public void setField() {
        map = new Field();
        map.addCells(true, map.arrayCells(4));
    }

    public void setTactician() { testTactician = new Tactician("Player"); }

    @BeforeEach
    public void setUp(){
        setField();
        setTactician();
    }

    @Test
    public void testName(){
        assertEquals("Player", testTactician.getName()); }

}
