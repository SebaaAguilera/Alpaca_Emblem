package model.items;

import model.units.*;

/**
 * This class represents a <i>spear</i>.
 * <p>
 * Spears are strong against swords and weak against axes
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Spear extends AbstractItem {

  /**
   * Creates a new Axe item
   *
   * @param name
   *     the name of the Axe
   * @param power
   *     the damage of the axe
   * @param minRange
   *     the minimum range of the axe
   * @param maxRange
   *     the maximum range of the axe
   */
  public Spear(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
  }

  @Override
  public void equipToHero(Hero hero) {
    super.equipTo(hero);
  }

  @Override
  public void attackTo(IUnit unit) {
   unit.attackedWithSpear(this);
  }


  public double damagedByAxe(Axe axe){
    return 1.5*axe.getPower();
  }

  public double damagedBySword(Sword sword){
    return sword.getPower()-20;

  }




}
