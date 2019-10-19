package model;

import model.map.Field;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TacticianTest {
    Tactician testTactician;
    UnitFactory unitfactory;
    Field map;

    @BeforeEach
    public void setField() {
        map = new Field();
        map.addCells(true, map.arrayCells(4));
    }

    @BeforeEach
    public void setTactician() { testTactician = new Tactician("Player"); }

    @Test
    public void testName(){ assertEquals("Player", testTactician.getName()); }

    @Test
    public void setUnits(){
        testTactician.addUnit(unitfactory.createHero(map.getCell(0,0)));
    }

}
