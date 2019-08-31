package model.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

/**
 * @author Ignacio Slater Muñoz
 */
public class HeroTest extends AbstractTestUnit {

  private Hero hero;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    hero = new Hero(500, 2, field.getCell(0, 0));
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public IUnit getTestUnit() {
    return hero;
  }

  /**
   * Checks if the spear is equipped correctly to the unit
   */
  @Override
  @Test
  public void equipSpearTest() {
    assertNull(hero.getEquippedItem());
    hero.saveItem(spear);
    hero.equipItem(spear);
    assertEquals(spear, hero.getEquippedItem());
  }

  @Test
  @Override
  public void testCombat() {
    setUnits();
    double testUnitHP = hero.getCurrentHitPoints();
    hero.saveItem(spear);
    hero.equipItem(spear);
    Fighter targetFighter = getTargetFighter();
    double targetFighterHP = targetFighter.getCurrentHitPoints();
    hero.combat(targetFighter);
    assertEquals(targetFighterHP-(hero.getEquippedItem().getPower()-20), targetFighter.getCurrentHitPoints());
    assertEquals(testUnitHP-(hero.getEquippedItem().getPower()*1.5),hero.getCurrentHitPoints());

    testUnitHP = hero.getCurrentHitPoints();
    Archer targetArcher = getTargetArcher();
    double targetArcherHP = targetArcher.getCurrentHitPoints();
    hero.combat(targetArcher);
    assertEquals(targetArcherHP-hero.getEquippedItem().getPower(),targetArcher.getCurrentHitPoints());
    assertEquals(testUnitHP,hero.getCurrentHitPoints());

    testUnitHP = hero.getCurrentHitPoints();
    Hero targetHero = getTargetHero();
    double targetHeroHP = targetHero.getCurrentHitPoints();
    hero.combat(targetHero);
    assertEquals(targetHeroHP-hero.getEquippedItem().getPower(),targetHero.getCurrentHitPoints());
    assertEquals(testUnitHP-targetHero.getEquippedItem().getPower(),hero.getCurrentHitPoints());
  }
}