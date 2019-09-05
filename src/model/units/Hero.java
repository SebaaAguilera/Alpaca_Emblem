package model.units;

import model.items.IEquipableItem;
import model.map.Location;

/**
 * A <i>Hero</i> is a special kind of unit, the player that defeats this unit wins the game.
 * <p>
 * This unit <b>can only use spear weapons</b>.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Hero extends AbstractUnit {

  /**
   * Creates a new Unit.
   *
   * @param hitPoints
   *     the maximum amount of damage a unit can sustain
   * @param movement
   *     the number of panels a unit can move
   */
  public Hero(double hitPoints, final int movement, Location location,
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
    item.equipToHero(this);
  }

}
