package model.units;

import model.items.IEquipableItem;
import model.map.Location;

/**
 * This class represents a cleric type unit. A cleric can only use staff type weapons, which means
 * that it can receive attacks but can't counter attack any of those.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Cleric extends AbstractUnit {

  /**
   * Creates a new Cleric
   *
   * @param hitPoints
   *     the maximum amount of damage a unit can sustain
   * @param movement
   *     the number of panels a unit can move
   */
  public Cleric(double hitPoints, final int movement, Location location,
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
    item.equipToCleric(this);
  }

  @Override
  public void useItemOn(IUnit unit){
    if (!getTactician().getUnits().contains(unit)) return;
    this.combat(unit);
  }

  @Override
  public void combat(IUnit unit) {
    if(unit.getCurrentHitPoints()>0 && this.getEquippedItem()!=null && this.getEquippedItem().inRange(unit)){
      getEquippedItem().healUnit(unit);
    }
  }

}
