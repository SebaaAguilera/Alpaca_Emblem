package model.items;

import model.units.*;

/**
 * This class represents a sword type item.
 * <p>
 * Swords are strong against axes and weak against spears.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Sword extends AbstractItem {

  /**
   * Creates a new Sword.
   *
   * @param name
   *     the name that identifies the weapon
   * @param power
   *     the base damage pf the weapon
   * @param minRange
   *     the minimum range of the weapon
   * @param maxRange
   *     the maximum range of the weapon
   */
  public Sword(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
  }


  public void equipToSwordMaster(SwordMaster swordMaster) {
    super.equipTo(swordMaster);
  }


  public void attackTo(IUnit unit) {
    unit.attackedWithSword(this);
  }

  public double damagedBySpear(Spear spear){
    return 1.5*spear.getPower();
  }
  public double damagedByAxe(Axe axe){
    return axe.getPower()-20;
  }

}
