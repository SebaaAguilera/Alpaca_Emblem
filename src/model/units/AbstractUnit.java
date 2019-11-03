package model.units;

import model.Tactician;
import model.items.IEquipableItem;
import model.map.Location;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.min;

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

  private List<IEquipableItem> items = new ArrayList<>();
  private final double maxHitPoints;
  private double currentHitPoints;
  private final int movement;
  private IEquipableItem equippedItem;
  private final int maxItems;
  private Location location;
  private Tactician tactician;

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
  protected AbstractUnit(double hitPoints, final int movement,
                         Location location, final int maxItems, IEquipableItem... items) {
    this.maxHitPoints = hitPoints;
    this.currentHitPoints = hitPoints;
    this.movement = movement;
    this.location = location;
    location.setUnit(this);
    this.maxItems = maxItems;
    this.items.addAll(Arrays.asList(items).subList(0, min(maxItems, items.length)));
  }

  @Override
  public Tactician getTactician() {return tactician; }

  @Override
  public void setTactician(Tactician tactician){ this.tactician = tactician; }

  @Override
  public double getMaxHitPoints(){ return maxHitPoints; }

  @Override
  public double getCurrentHitPoints() { return currentHitPoints; }

  @Override
  public void setCurrentHitPoints(double currentHitPoints) {
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
  public int getMaxItems() {
    return this.maxItems;
  }

  @Override
  public void saveItem(IEquipableItem item) {
    if(items.size()<maxItems){
      items.add(item);
    }
  }

  @Override
  public void giveItem(IUnit unit, IEquipableItem item) {
      if (this.getItems().contains(item) && unit.getItems().size() < unit.getMaxItems() &&
              this.getLocation().distanceTo(unit.getLocation()) == 1) {
        if (this.getEquippedItem()==item) {
          this.unEquipItem();
        }
        this.items.remove(item);
        unit.saveItem(item);
    }
  }

  @Override
  public void unEquipItem(){
    this.getEquippedItem().unEquipFrom();
    this.setEquippedItem(null);
  }

  @Override
  public Location getLocation() {
    return location;
  }

  @Override
  public void setLocation(Location location) { //location had a final
    this.location = location;
    location.setUnit(this);
  }

  @Override
  public int getMovement() {
    return movement;
  }

  @Override
  public void moveTo(final Location targetLocation) {
    if (getLocation().distanceTo(targetLocation) <= getMovement()
        && targetLocation.getUnit() == null) {
      getLocation().setUnit(null);
      setLocation(targetLocation);
    }
  }

  @Override
  public void useItemOn(IUnit unit){
    if (getTactician().getUnits().contains(unit)) return;
    this.combat(unit);
  }

  @Override
  public void combat(IUnit unit) {
    if (this.getCurrentHitPoints() == 0 || this.getEquippedItem() == null || !this.getEquippedItem().inRange(unit)) return;
    this.getEquippedItem().attackTo(unit);
    if (unit.getCurrentHitPoints()==0 || unit.getEquippedItem() == null || !unit.getEquippedItem().inRange(this)) return;
    unit.getEquippedItem().attackTo(this);
  }

  @Override
  public void attacked(double damage) {
    if (this.getCurrentHitPoints()-damage<=0){
      setCurrentHitPoints(0);
      this.deadUnit();
    } else {
      setCurrentHitPoints(this.getCurrentHitPoints() - damage);
    }
  }

  @Override
  public void healed(double healHP) {
    if (healHP + this.getCurrentHitPoints()>this.getMaxHitPoints()){
      this.setCurrentHitPoints(this.getMaxHitPoints());
    } else {
      this.setCurrentHitPoints(this.getCurrentHitPoints()+healHP);
    }
  }

  @Override
  public void deadUnit() {
    getTactician().removeUnit(this); //esto debiese hacerse con un handler
    getLocation().setUnit(null);
  }
}
