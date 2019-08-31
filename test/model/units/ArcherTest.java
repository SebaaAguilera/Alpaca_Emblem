package model.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import model.items.Bow;
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

    Cleric targetCleric = getTargetCleric();
    double targetFighterHP = targetCleric.getCurrentHitPoints();
    archer.combat(targetCleric);
    assertEquals(targetFighterHP, targetCleric.getCurrentHitPoints());
    assertEquals(testUnitHP,archer.getCurrentHitPoints());

    Bow superBow = new Bow("Alpacaminator", 9999, 2, 3);
    archer.saveItem(superBow);
    archer.equipItem(superBow);
    Alpaca targetAlpaca = getTargetAlpaca();
    assertEquals(true,targetAlpaca.getCurrentHitPoints()>0);
    archer.combat(targetAlpaca);
    assertEquals(0, targetAlpaca.getCurrentHitPoints());
    assertEquals(testUnitHP,archer.getCurrentHitPoints());
  }
}