package model.items;

import model.map.Location;
import model.units.Sorcerer;
import model.units.IUnit;

/**
 * Test set for LightBooks
 *
 * @author Sebastián Aguilera Valenzuela
 * @since 1.1
 */
public class LightBookTest extends AbstractTestItem {
    private LightBook book;
    private  LightBook wrongBook;
    private Sorcerer sorcerer;

    @Override
    public void setTestItem() {
        expectedName = "Common book";
        expectedPower = 10;
        expectedMinRange = 1;
        expectedMaxRange = 2;
        book = new LightBook(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
    }

    /**
     * Sets up an item with wrong ranges setted.
     */
    @Override
    public void setWrongRangeItem() {
        wrongBook = new LightBook("Wrong book", 0, -1, -2);
    }

    /**
     * Sets the unit that will be equipped with the test item
     */
    @Override
    public void setTestUnit() {
        sorcerer = new Sorcerer(100, 5, new Location(0, 0));
    }

    @Override
    public IEquipableItem getWrongTestItem() {
        return wrongBook;
    }

    /**
     * @return the item being tested
     */
    @Override
    public IEquipableItem getTestItem() {
        return book;
    }

    /**
     * @return a unit that can equip the item being tested
     */
    @Override
    public IUnit getTestUnit() {
        return sorcerer;
    }
}
