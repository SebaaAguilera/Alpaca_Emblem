package model.units;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Test set of a Cleric Unit
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class ClericTest extends AbstractTestUnit {

  private Cleric cleric;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    cleric = new Cleric(500, 2, field.getCell(0, 0));
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public IUnit getTestUnit() {
    return cleric;
  }

  /**
   * Checks if the staff is equipped correctly to the unit
   */
  @Test
  @Override
  public void equipStaffTest() {
    assertNull(cleric.getEquippedItem());
    cleric.saveItem(staff);
    cleric.equipItem(staff);
    assertEquals(staff, cleric.getEquippedItem());
  }

  @Test
  @Override
  public void testCombat() {
    setUnits();
    double testUnitHP = cleric.getCurrentHitPoints();
    cleric.saveItem(staff);
    cleric.equipItem(staff);
    Archer targetArcher = getTargetArcher();
    double targetArcherHP = targetArcher.getCurrentHitPoints();
    cleric.combat(targetArcher);
    assertEquals(targetArcherHP, targetArcher.getCurrentHitPoints());
    assertEquals(testUnitHP, cleric.getCurrentHitPoints());
  }

  @Test
  public void testHealing() {
    setUnits();
    cleric.saveItem(staff);
    cleric.equipItem(staff);
    double testUnitHP = cleric.getCurrentHitPoints();
    Alpaca targetAlpaca = getTargetAlpaca();
    double targetAlpacaHP = targetAlpaca.getCurrentHitPoints();
    cleric.heal(targetAlpaca);
    assertEquals(targetAlpacaHP + cleric.getEquippedItem().getPower(), targetAlpaca.getCurrentHitPoints());
    assertEquals(testUnitHP, cleric.getCurrentHitPoints());

  }
}