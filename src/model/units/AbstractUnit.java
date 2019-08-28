package model.units;

import static java.lang.Math.min;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.items.IEquipableItem;
import model.map.Location;

/**
 * This class represents an abstract unit.
 * <p>
 * An abstract unit is a unit that cannot be used in the
 * game, but that contains the implementation of some of the methods that are common for most
 * units.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public abstract class AbstractUnit implements IUnit {

  protected List<IEquipableItem> items = new ArrayList<>();
  private final int maxHitPoints;
  private int currentHitPoints;
  private final int movement;
  protected IEquipableItem equippedItem;
  protected int maxItems;
  private Location location;

  /**
   * Creates a new Unit.
   * @param hitPoints
   *     the maximum amount of damage a unit can sustain, at the begining max=current
   * @param movement
   *     the number of panels a unit can move
   * @param location
   *     the current position of this unit on the map
   * @param maxItems
   *     maximum amount of items this unit can carry
   */
  protected AbstractUnit(int hitPoints, final int movement,
                         Location location, final int maxItems, IEquipableItem... items) {
    this.maxHitPoints = hitPoints;
    this.currentHitPoints = hitPoints;
    this.movement = movement;
    this.location = location;
    this.maxItems = maxItems;
    this.items.addAll(Arrays.asList(items).subList(0, min(maxItems, items.length)));
  }

  @Override
  public int getCurrentHitPoints() {
    return currentHitPoints;
  }

  @Override
  public void setCurrentHitPoints(int currentHitPoints) {
    this.currentHitPoints = currentHitPoints;
  }

  @Override
  public List<IEquipableItem> getItems() {
    return List.copyOf(items);
  }

  @Override
  public IEquipableItem getEquippedItem() {
    return equippedItem;
  }

  @Override
  public void setEquippedItem(IEquipableItem item) {
    this.equippedItem = item;
  }

  @Override
  public void setSavedItem(IEquipableItem item) {
    if(items.size()<maxItems){
      items.add(item);
    }
  }

  @Override
  public Location getLocation() {
    return location;
  }

  @Override
  public void setLocation(Location location) { //location had a final
    this.location = location;
  }

  @Override
  public int getMovement() {
    return movement;
  }

  @Override
  public void moveTo(final Location targetLocation) {
    if (getLocation().distanceTo(targetLocation) <= getMovement()
        && targetLocation.getUnit() == null) {
      setLocation(targetLocation);
    }
  }

  @Override
  public boolean inRange(IUnit enemy) {
    Location enemyLocation = enemy.getLocation();
    double distance = getLocation().distanceTo(enemyLocation);
    if (getEquippedItem().getMinRange()>distance && getEquippedItem().getMaxRange()<distance){
      return true;
    }
    return false;
  }

  @Override
  public void attack(IUnit enemy) {
    if (inRange(enemy)){
      int currentHitPoints = enemy.getCurrentHitPoints() - getEquippedItem().getPower();
      if (currentHitPoints<=0){
        enemy.setCurrentHitPoints(0);
      } else {
        enemy.setCurrentHitPoints(currentHitPoints);
      }

    }
  }


}
