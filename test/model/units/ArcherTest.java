package model.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

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
    archer = new Archer(100, 2, field.getCell(0, 0));
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
    setCombatUnits();
    double testUnitHP = archer.getCurrentHitPoints();
    archer.saveItem(bow);
    archer.equipItem(bow);
    Alpaca targetAlpaca = getTargetAlpaca();
    double targetFighterHP = targetAlpaca.getCurrentHitPoints();
    archer.combat(targetAlpaca);
    assertEquals(targetFighterHP-archer.getEquippedItem().getPower(), targetAlpaca.getCurrentHitPoints());
    assertEquals(testUnitHP,archer.getCurrentHitPoints());
    Cleric targetCleric = getTargetCleric();
    double targetFighterHP2 = targetCleric.getCurrentHitPoints();
    archer.combat(targetCleric);
    assertEquals(targetFighterHP2, targetCleric.getCurrentHitPoints());
    assertEquals(testUnitHP,archer.getCurrentHitPoints());
  }
}