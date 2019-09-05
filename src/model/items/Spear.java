package model.items;

import model.units.Hero;
import model.units.IUnit;

/**
 * This class represents a <i>spear</i>.
 * <p>
 * Spears are strong against swords and magical items but weak against axes
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Spear extends AbstractNonMagicItem {

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
    if (unit.getEquippedItem()==null){
      unit.attacked(this.getPower());
    } else {
      unit.getEquippedItem().attackedWithSpear(this);
    }
  }

  @Override
  public void attackedWithAxe(Axe axe){
    this.getOwner().attacked(1.5*axe.getPower());
  }

  @Override
  public void attackedWithSword(Sword sword){
    if (sword.getPower()-20<=0) return;
    this.getOwner().attacked(sword.getPower()-20);
  }




}
