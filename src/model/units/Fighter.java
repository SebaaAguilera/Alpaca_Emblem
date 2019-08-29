package model.units;

import model.items.*;
import model.map.Location;

/**
 * This class represents a fighter type unit.
 * A fighter is a unit that can only use axe type weapons.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Fighter extends AbstractUnit {

  public Fighter(double hitPoints, final int movement, Location location,
      IEquipableItem... items) {
    super(hitPoints, movement, location, 3, items);
  }

  /**
   * Sets the currently equipped item of this unit.
   *
   * @param item
   *     the item to equip
   */
  @Override
  public void equipItem(IEquipableItem item) {
    item.equipToFighter(this);
  }


  @Override
  public void attackedWithSpear(Spear spear){
    double damage = this.getEquippedItem().damagedBySpear(spear);
    if (this.getCurrentHitPoints()-damage<=0){
      setCurrentHitPoints(0);
    } else {
      setCurrentHitPoints(this.getCurrentHitPoints() - damage);
    }
  }

  @Override
  public void attackedWithSword(Sword sword){
    double damage =  this.getEquippedItem().damagedBySword(sword);
    if (this.getCurrentHitPoints()-damage<=0){
      setCurrentHitPoints(0);
    } else {
      setCurrentHitPoints(this.getCurrentHitPoints() - damage);
    }
  }
}
