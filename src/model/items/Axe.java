package model.items;

import model.units.*;

/**
 * This class represents an Axe.
 * <p>
 * Axes are strong against spears and magical items but weak agains swords.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Axe extends AbstractNonMagicItem {

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
  public Axe(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
  }

  public void equipToFighter(Fighter fighter) {
    super.equipTo(fighter);
  }

  @Override
  public void attackTo(IUnit unit) {
    if (unit.getEquippedItem()==null){
      unit.attacked(this.getPower());
    } else {
      unit.getEquippedItem().attackedWithAxe(this);
    }
  }

  @Override
  public void attackedWithSword(Sword sword){
    this.getOwner().attacked(1.5*sword.getPower());
  }

  @Override
  public void attackedWithSpear(Spear spear){
    if (spear.getPower()-20<=0) return;
    this.getOwner().attacked(spear.getPower()-20);
  }



}
