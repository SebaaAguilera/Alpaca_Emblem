package model.units;

import model.items.IEquipableItem;
import model.map.InvalidLocation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test set of a Null Unit
 * This test makes sure that the Null Unit won't behave in bad ways
 *
 * @author Sebastián Aguilera Valenzuela
 * @since 2.5
 */
public class NullUnitTest extends AbstractTestUnit{
    private NullUnit nullUnit;

    /**
     * Set up the main unit that's going to be tested in the test set
     */
    @Override
    public void setTestUnit() {
        nullUnit = new NullUnit();
    }

    /**
     * @return the current unit being tested
     */
    @Override
    public IUnit getTestUnit() {
        return nullUnit;
    }

    @Override
    @Test
    public void constructorTest() {
        assertEquals(-1, getTestUnit().getCurrentHitPoints());
        assertEquals(-1, getTestUnit().getMovement());
        assertEquals(new InvalidLocation(), getTestUnit().getLocation());
        assertTrue(getTestUnit().getItems().isEmpty());
    }

    @Test
    @Override
    public void testMaxSavingItems() {
        assertTrue(nullUnit.getItems().isEmpty());
        setWeapons();
        nullUnit.saveItem(getAxe());
        assertFalse(nullUnit.getItems().contains(getAxe()));
        nullUnit.saveItem(getBow());
        assertFalse(nullUnit.getItems().contains(getBow()));
        nullUnit.saveItem(getSword());
        assertFalse(nullUnit.getItems().contains(getSword()));
        nullUnit.saveItem(getStaff());
        assertFalse(nullUnit.getItems().contains(getStaff()));
    }

    @Override
    @Test
    public void testTrading() {
        setUnits();
        Hero hero = getTargetHero();

        IEquipableItem item = hero.getEquippedItem();

        hero.giveItem(nullUnit, item);

        assertEquals(item,hero.getEquippedItem());
        assertEquals(hero,item.getOwner());
        assertFalse(getTestUnit().getItems().contains(item));

        nullUnit.giveItem(hero,item);
        assertEquals(item,hero.getEquippedItem());
        assertEquals(hero,item.getOwner());
        assertFalse(getTestUnit().getItems().contains(item));
    }

    @Override
    @Test
    public void testMovement() {
        getTestUnit().moveTo(getField().getCell(2, 2));
        assertEquals(new InvalidLocation(), getTestUnit().getLocation());

        getTestUnit().moveTo(getField().getCell(0, 2));
        assertEquals(new InvalidLocation(), getTestUnit().getLocation());

        getField().getCell(0, 1).setUnit(getTargetAlpaca());
        getTestUnit().moveTo(getField().getCell(0, 1));
        assertEquals(new InvalidLocation(), getTestUnit().getLocation());
    }

    @Override
    @Test
    public void testCombat() {
        setUnits();

        Hero targetHero = getTargetHero();
        targetHero.saveItem(spear);
        targetHero.equipItem(spear);
        Cleric targetCleric = getTargetCleric();
        targetCleric.saveItem(staff);
        targetCleric.equipItem(staff);

        double nullHP = nullUnit.getCurrentHitPoints();
        double heroHP = targetHero.getCurrentHitPoints();
        double clericHP = targetCleric.getCurrentHitPoints();

        targetHero.combat(nullUnit);
        assertEquals(heroHP,targetHero.getCurrentHitPoints());
        assertEquals(nullHP,nullUnit.getCurrentHitPoints());

        targetCleric.combat(nullUnit);
        assertEquals(clericHP,targetCleric.getCurrentHitPoints());
        assertEquals(nullHP,nullUnit.getCurrentHitPoints());

        nullUnit.combat(targetHero);
        assertEquals(heroHP,targetHero.getCurrentHitPoints());
        assertEquals(nullHP,nullUnit.getCurrentHitPoints());
    }
}
