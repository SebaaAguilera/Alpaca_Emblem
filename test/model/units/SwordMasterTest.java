package model.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

/**
 * @author Ignacio Slater Muñoz
 */
public class SwordMasterTest extends AbstractTestUnit {

  private SwordMaster swordMaster;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    swordMaster = new SwordMaster(100, 2, field.getCell(0, 0));
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public IUnit getTestUnit() {
    return swordMaster;
  }

  /**
   * Checks if the sword is equipped correctly to the unit
   */
  @Test
  @Override
  public void equipSwordTest() {
    assertNull(swordMaster.getEquippedItem());
    swordMaster.saveItem(sword);
    swordMaster.equipItem(sword);
    assertEquals(sword, swordMaster.getEquippedItem());
  }

  @Test
  @Override
  public void testCombat() {
    setCombatUnits();
    double testUnitHP = swordMaster.getCurrentHitPoints();
    swordMaster.saveItem(sword);
    swordMaster.equipItem(sword);
    Hero targetHero = getTargetHero();
    double targetHeroHP = targetHero.getCurrentHitPoints();
    swordMaster.combat(targetHero);
    assertEquals(targetHeroHP-(swordMaster.getEquippedItem().getPower()-20),targetHero.getCurrentHitPoints());
    assertEquals(testUnitHP-(targetHero.getEquippedItem().getPower()*1.5),swordMaster.getCurrentHitPoints());
  }
}