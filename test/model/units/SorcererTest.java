package model.units;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Test set of a Sorcerer Unit
 *
 * @author Sebastián Aguilera Valenzuela
 * @since 1.1
 */
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
    public void equipBookTest() {
        assertNull(sorcerer.getEquippedItem());
        sorcerer.saveItem(lightBook);
        sorcerer.equipItem(lightBook);
        assertEquals(lightBook, sorcerer.getEquippedItem());
    }

    @Test
    @Override
    public void testCombat() {
        setUnits();
        sorcerer.saveItem(lightBook);
        sorcerer.saveItem(darknessBook);
        sorcerer.saveItem(animaBook);

        sorcerer.equipItem(lightBook);
        double testUnitHP = sorcerer.getCurrentHitPoints();
        Sorcerer targetLightSorcerer = getTargetLightSorcerer();
        double targetLightSorcererHP = targetLightSorcerer.getCurrentHitPoints();
        sorcerer.combat(targetLightSorcerer);
        assertEquals(targetLightSorcererHP - sorcerer.getEquippedItem().getPower(), targetLightSorcerer.getCurrentHitPoints());
        assertEquals(testUnitHP - targetLightSorcerer.getEquippedItem().getPower(), sorcerer.getCurrentHitPoints());

        testUnitHP = sorcerer.getCurrentHitPoints();
        Sorcerer targetDarkSorcerer = getTargetDarkSorcerer();
        double targetDarkSorcererHP = targetDarkSorcerer.getCurrentHitPoints();
        sorcerer.combat(targetDarkSorcerer);
        assertEquals(targetDarkSorcererHP - (1.5 * sorcerer.getEquippedItem().getPower()), targetDarkSorcerer.getCurrentHitPoints());
        assertEquals(testUnitHP - (targetDarkSorcerer.getEquippedItem().getPower() - 20), sorcerer.getCurrentHitPoints());

        testUnitHP = sorcerer.getCurrentHitPoints();
        SwordMaster targetSwordMaster = getTargetSwordMaster();
        double targetSwordMasterHP = targetSwordMaster.getCurrentHitPoints();
        sorcerer.combat(targetSwordMaster);
        assertEquals(targetSwordMasterHP - (1.5 * sorcerer.getEquippedItem().getPower()), targetSwordMaster.getCurrentHitPoints());
        assertEquals(testUnitHP - (1.5 * targetSwordMaster.getEquippedItem().getPower()), sorcerer.getCurrentHitPoints());

        sorcerer.equipItem(darknessBook);
        testUnitHP = sorcerer.getCurrentHitPoints();
        targetDarkSorcererHP = targetDarkSorcerer.getCurrentHitPoints();
        sorcerer.combat(targetDarkSorcerer);
        assertEquals(targetDarkSorcererHP - sorcerer.getEquippedItem().getPower(), targetDarkSorcerer.getCurrentHitPoints());
        assertEquals(testUnitHP - targetDarkSorcerer.getEquippedItem().getPower(), sorcerer.getCurrentHitPoints());

        testUnitHP = sorcerer.getCurrentHitPoints();
        Sorcerer targetAnimaSorcerer = getTargetAnimaSorcerer();
        double targetAnimaSorcererHP = targetAnimaSorcerer.getCurrentHitPoints();
        sorcerer.combat(targetAnimaSorcerer);
        assertEquals(targetAnimaSorcererHP - (1.5 * sorcerer.getEquippedItem().getPower()), targetAnimaSorcerer.getCurrentHitPoints());
        assertEquals(testUnitHP - (targetAnimaSorcerer.getEquippedItem().getPower() - 20), sorcerer.getCurrentHitPoints());

        testUnitHP = sorcerer.getCurrentHitPoints();
        Hero targetHero = getTargetHero();
        double targetHeroHP = targetHero.getCurrentHitPoints();
        sorcerer.combat(targetHero);
        assertEquals(targetHeroHP - (1.5 * sorcerer.getEquippedItem().getPower()), targetHero.getCurrentHitPoints());
        assertEquals(testUnitHP - (1.5 * targetHero.getEquippedItem().getPower()), sorcerer.getCurrentHitPoints());

        sorcerer.equipItem(animaBook);
        testUnitHP = sorcerer.getCurrentHitPoints();
        targetAnimaSorcererHP = targetAnimaSorcerer.getCurrentHitPoints();
        sorcerer.combat(targetAnimaSorcerer);
        assertEquals(targetAnimaSorcererHP - sorcerer.getEquippedItem().getPower(), targetAnimaSorcerer.getCurrentHitPoints());
        assertEquals(testUnitHP - targetAnimaSorcerer.getEquippedItem().getPower(), sorcerer.getCurrentHitPoints());

        testUnitHP = sorcerer.getCurrentHitPoints();
        targetLightSorcerer = getTargetLightSorcerer();
        targetLightSorcererHP = targetLightSorcerer.getCurrentHitPoints();
        sorcerer.combat(targetLightSorcerer);
        assertEquals(targetLightSorcererHP - (1.5 * sorcerer.getEquippedItem().getPower()), targetLightSorcerer.getCurrentHitPoints());
        assertEquals(testUnitHP - (targetLightSorcerer.getEquippedItem().getPower() - 20), sorcerer.getCurrentHitPoints());

        testUnitHP = sorcerer.getCurrentHitPoints();
        Fighter targetFighter = getTargetFighter();
        double targetFighterHP = targetFighter.getCurrentHitPoints();
        sorcerer.combat(targetFighter);
        assertEquals(targetFighterHP - (1.5 * sorcerer.getEquippedItem().getPower()), targetFighter.getCurrentHitPoints());
        assertEquals(testUnitHP - (1.5 * targetFighter.getEquippedItem().getPower()), sorcerer.getCurrentHitPoints());

    }
}
