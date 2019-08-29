package model.units;

import model.items.*;
import model.map.Location;

/**
 * This class represents a <i>SwordMaster</i> type unit.
 * <p>
 * A <i>SwordMaster</i> is a unit that <b>can only use sword type weapons</b>.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class SwordMaster extends AbstractUnit {

  public SwordMaster(double hitPoints, final int movement, Location location,
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
    item.equipToSwordMaster(this);
  }

  @Override
  public void attackedWithSpear(Spear spear){
    double damage = this.getEquippedItem().damagedBySpear(spear);
    if (this.getCurrentHitPoints()-damage<=0){
      setCurrentHitPoints(0);
    } else {
      setCurrentHitPoints(this.getCurrentHitPoints()-damage);
    }
  }

  @Override
  public void attackedWithAxe(Axe axe){
    double damage = this.getEquippedItem().damagedByAxe(axe);
    if (this.getCurrentHitPoints()-damage<=0){
      setCurrentHitPoints(0);
    } else {
      setCurrentHitPoints(this.getCurrentHitPoints() - damage);
    }
  }

}
