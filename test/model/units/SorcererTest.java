package model.units;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class SorcererTest extends AbstractTestUnit{
    private Sorcerer sorcerer;

    /**
     * Set up the main unit that's going to be tested in the test set
     */
    @Override
    public void setTestUnit() {
        sorcerer = new Sorcerer(500, 2, field.getCell(0, 0));
    }

    /**
     * @return the current unit being tested
     */
    @Override
    public IUnit getTestUnit() {
        return sorcerer;
    }

    /**
     * Checks if the bow is equipped correctly to the unit
     */
    @Test
    @Override
    public void equipBowTest() {
        assertNull(sorcerer.getEquippedItem());
        sorcerer.saveItem(lightBook);
        sorcerer.equipItem(lightBook);
        assertEquals(lightBook, sorcerer.getEquippedItem());
    }

    @Test
    @Override
    public void testCombat() {
        //
    }
}
