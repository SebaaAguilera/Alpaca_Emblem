package model.units;

import model.items.Bow;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test set for the Archer unit.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class ArcherTest extends AbstractTestUnit {

  private Archer archer;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    archer = new Archer(500, 2, field.getCell(0, 0));
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public IUnit getTestUnit() {
    return archer;
  }

  /**
   * Checks if the bow is equipped correctly to the unit
   */
  @Test
  @Override
  public void equipBowTest() {
    assertNull(archer.getEquippedItem());
    archer.saveItem(bow);
    archer.equipItem(bow);
    assertEquals(bow, archer.getEquippedItem());
  }

  @Test
  @Override
  public void testCombat() {
    setUnits();
    double testUnitHP = archer.getCurrentHitPoints();
    archer.saveItem(bow);
    archer.equipItem(bow);

    Sorcerer targetSorcerer = getTargetLightSorcerer();
    targetSorcerer.moveTo(field.getCell(2, 0));
    double targetSorcererHP = targetSorcerer.getCurrentHitPoints();
    archer.combat(targetSorcerer);
    assertEquals(targetSorcererHP - 1.5 * archer.getEquippedItem().getPower(), targetSorcerer.getCurrentHitPoints());
    assertEquals(testUnitHP - 1.5 * targetSorcerer.getEquippedItem().getPower(), archer.getCurrentHitPoints());
    targetSorcerer.moveTo(field.getCell(0, 2));

    testUnitHP = archer.getCurrentHitPoints();
    Hero targetHero = getTargetHero();
    targetHero.moveTo(field.getCell(2, 0));
    double targetHeroHP = targetHero.getCurrentHitPoints();
    archer.combat(targetHero);
    assertEquals(targetHeroHP - archer.getEquippedItem().getPower(), targetHero.getCurrentHitPoints());
    assertEquals(testUnitHP - targetHero.getEquippedItem().getPower(), archer.getCurrentHitPoints());

    testUnitHP = archer.getCurrentHitPoints();
    Cleric targetCleric = getTargetCleric();
    double targetClericHP = targetCleric.getCurrentHitPoints();
    archer.combat(targetCleric);
    assertEquals(targetClericHP, targetCleric.getCurrentHitPoints());
    assertEquals(testUnitHP,archer.getCurrentHitPoints());

    testUnitHP = archer.getCurrentHitPoints();
    Bow superBow = new Bow("Alpacaminator", 9999, 2, 3);
    archer.saveItem(superBow);
    archer.equipItem(superBow);
    Alpaca targetAlpaca = getTargetAlpaca();
    assertTrue(targetAlpaca.getCurrentHitPoints() > 0);
    archer.combat(targetAlpaca);
    assertEquals(0, targetAlpaca.getCurrentHitPoints());
    assertEquals(testUnitHP,archer.getCurrentHitPoints());
  }
}