package model.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

/**
 * Test set for the alpaca unit
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class AlpacaTest extends AbstractTestUnit {

  private Alpaca alpaca;

  @Override
  public void setTestUnit() {
    alpaca = new Alpaca(100, 2, field.getCell(0, 0));
  }

  @Override
  public Alpaca getTestUnit() {
    return alpaca;
  }

  @Test
  @Override
  public void testCombat(){
    setCombatUnits();
    SwordMaster targetSwordMaster = getTargetSwordMaster();
    Fighter targetFighter = getTargetFighter();
    Hero targetHero = getTargetHero();
    double targetSwordMasterHP = targetSwordMaster.getCurrentHitPoints();
    double targetFighterHP = targetFighter.getCurrentHitPoints();
    double targetHeroHP = targetHero.getCurrentHitPoints();
    double alpacaHP = alpaca.getCurrentHitPoints();
    targetSwordMaster.combat(alpaca);
    assertEquals(alpacaHP-targetSwordMaster.getEquippedItem().getPower(),alpaca.getCurrentHitPoints());
    assertEquals(targetSwordMasterHP,targetSwordMaster.getCurrentHitPoints());
    alpacaHP = alpaca.getCurrentHitPoints();
    targetFighter.combat(alpaca);
    assertEquals(alpacaHP-targetFighter.getEquippedItem().getPower(),alpaca.getCurrentHitPoints());
    assertEquals(targetFighterHP,targetFighter.getCurrentHitPoints());
    alpacaHP = alpaca.getCurrentHitPoints();
    targetHero.combat(alpaca);
    assertEquals(alpacaHP-targetHero.getEquippedItem().getPower(),alpaca.getCurrentHitPoints());
    assertEquals(targetHeroHP,targetHero.getCurrentHitPoints());

  }


}