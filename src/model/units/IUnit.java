package model.units;

import java.util.List;
import model.items.IEquipableItem;
import model.map.Location;

/**
 * This interface represents all units in the game.
 * <p>
 * The signature of all the common methods that a unit can execute are defined here. All units
 * except some special ones can carry at most 3 weapons.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public interface IUnit {
  
  /**
   * Sets the currently equipped item of this unit.
   *
   * @param item
   *     the item to equip
   */
  void equipItem(IEquipableItem item);

  /**
   * @return hit points of the unit
   */
  double getCurrentHitPoints();

  /**
   * updates the currentHitPoints
   * @param currentHitPoints
   *    updates the currentHitpoints
   */
  void setCurrentHitPoints(double currentHitPoints);

  /**
   * @return the items carried by this unit
   */
  List<IEquipableItem> getItems();

  /**
   * @return the currently equipped item
   */
  IEquipableItem getEquippedItem();

  /**
   * @param item
   *    the item to be equipped
   */
  void setEquippedItem(IEquipableItem item);

  /**
   *
   * @return an unit max items
   */
  int getMaxItems();

  /**
   * @param item the item is going to be saved
   */
  void saveItem(IEquipableItem item);

  /**
   * Gives an item from a unit to another
   * @param unit receiver unit
   * @param item item
   */
  void giveItem(IUnit unit,IEquipableItem item);

  /**
   * unequip an unit item
   */
  void unEquipItem();

  /**
   * @return the current location of the unit
   */
  Location getLocation();

  /**
   * Sets a new location for this unit,
   */
  void setLocation(final Location location);

  /**
   * @return the number of cells this unit can move
   */
  int getMovement();

  /**
   * Moves this unit to another location.
   * <p>
   * If the other location is out of this unit's movement range, the unit doesn't move.
   */
  void moveTo(Location targetLocation);

  /**
   * @param unit
   * start a combat between a unit and another one
   */
  void combat(IUnit unit);

  /**
   * @param damage
   * decreases the unit current points depending on the damage
   */
  void attacked(double damage);

  /**
   * heals an unit
   * @param healHP
   *    the points are going to be added to the unit currentHitpoints
   *    the new currentHitpoins can be greater than the initial CurrentHitPoints
   */
  void healed(double healHP);

}