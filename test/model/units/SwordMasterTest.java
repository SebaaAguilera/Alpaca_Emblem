package model.units;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Set test of SwordMaster unit
 *
 * @author Ignacio Slater Muñoz
 * @since 1.1
 */
public class SwordMasterTest extends AbstractTestUnit {

  private SwordMaster swordMaster;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    swordMaster = new SwordMaster(500, 2, field.getCell(0, 0));
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
    setUnits();
    double testUnitHP = swordMaster.getCurrentHitPoints();
    swordMaster.saveItem(sword);
    swordMaster.equipItem(sword);
    Hero targetHero = getTargetHero();
    double targetHeroHP = targetHero.getCurrentHitPoints();
    swordMaster.combat(targetHero);
    assertEquals(targetHeroHP-(swordMaster.getEquippedItem().getPower()-20),targetHero.getCurrentHitPoints());
    assertEquals(testUnitHP-(targetHero.getEquippedItem().getPower()*1.5),swordMaster.getCurrentHitPoints());

    testUnitHP = swordMaster.getCurrentHitPoints();
    Archer targetArcher = getTargetArcher();
    double targetArcherHP = targetArcher.getCurrentHitPoints();
    swordMaster.combat(targetArcher);
    assertEquals(targetArcherHP-swordMaster.getEquippedItem().getPower(),targetArcher.getCurrentHitPoints());
    assertEquals(testUnitHP,swordMaster.getCurrentHitPoints());

    testUnitHP = swordMaster.getCurrentHitPoints();
    SwordMaster targetSwordMaster = getTargetSwordMaster();
    double targetSwordMasterHP = targetSwordMaster.getCurrentHitPoints();
    swordMaster.combat(targetSwordMaster);
    assertEquals(targetArcherHP-swordMaster.getEquippedItem().getPower(),targetSwordMaster.getCurrentHitPoints());
    assertEquals(testUnitHP-targetSwordMaster.getEquippedItem().getPower(),swordMaster.getCurrentHitPoints());
  }
}