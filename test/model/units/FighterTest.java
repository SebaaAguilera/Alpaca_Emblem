package model.units;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Test set of a Fighter Unit
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class FighterTest extends AbstractTestUnit {

  private Fighter fighter;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    fighter = new Fighter(500, 2, field.getCell(0, 0));
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public IUnit getTestUnit() {
    return fighter;
  }

  /**
   * Checks if the axe is equipped correctly to the unit
   */
  @Test
  @Override
  public void equipAxeTest() {
    assertNull(fighter.getEquippedItem());
    fighter.saveItem(axe);
    fighter.equipItem(axe);
    assertEquals(axe, fighter.getEquippedItem());
  }

  @Test
  @Override
  public void testCombat() {
    setUnits();
    double testUnitHP = fighter.getCurrentHitPoints();
    fighter.saveItem(axe);
    fighter.equipItem(axe);

    SwordMaster targetSwordMaster = getTargetSwordMaster();
    double targetSwordMasterHP = targetSwordMaster.getCurrentHitPoints();
    fighter.combat(targetSwordMaster);
    assertEquals(targetSwordMasterHP-(fighter.getEquippedItem().getPower()-20),targetSwordMaster.getCurrentHitPoints());
    assertEquals(testUnitHP-(targetSwordMaster.getEquippedItem().getPower()*1.5),fighter.getCurrentHitPoints());

    testUnitHP = fighter.getCurrentHitPoints();
    Archer targetArcher = getTargetArcher();
    double targetArcherHP = targetArcher.getCurrentHitPoints();
    fighter.combat(targetArcher);
    assertEquals(targetArcherHP-fighter.getEquippedItem().getPower(),targetArcher.getCurrentHitPoints());
    assertEquals(testUnitHP,fighter.getCurrentHitPoints());

    testUnitHP = fighter.getCurrentHitPoints();
    Fighter targetFighter = getTargetFighter();
    double targetFighterHP = targetFighter.getCurrentHitPoints();
    fighter.combat(targetFighter);
    assertEquals(targetFighterHP-fighter.getEquippedItem().getPower(),targetFighter.getCurrentHitPoints());
    assertEquals(testUnitHP-targetFighter.getEquippedItem().getPower(),fighter.getCurrentHitPoints());

  }
}