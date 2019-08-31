package model.items;

import model.map.Location;
import model.units.*;

/**
 * Abstract class that defines some common information and behaviour between all items.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public abstract class AbstractItem implements IEquipableItem {

  private final String name;
  private final int power;
  protected int maxRange;
  protected int minRange;
  private IUnit owner;

  /**
   * Constructor for a default item without any special behaviour.
   *
   * @param name
   *     the name of the item
   * @param power
   *     the power of the item (this could be the amount of damage or healing the item does)
   * @param minRange
   *     the minimum range of the item
   * @param maxRange
   *     the maximum range of the item
   */
  public AbstractItem(final String name, final int power, final int minRange, final int maxRange) {
    this.name = name;
    this.power = power;
    this.minRange = Math.max(minRange, 1);
    this.maxRange = Math.max(maxRange, this.minRange);
  }

  @Override
  public void equipTo(final IUnit unit) {
    if(unit.getItems().contains(this)){
      unit.setEquippedItem(this);
      owner = unit;
    }

  }
  @Override
  public void equipToArcher(Archer archer) {
    // Method body intentionally left empty
  }

  @Override
  public void equipToCleric(Cleric cleric) {
    // Method body intentionally left empty
  }

  @Override
  public void equipToFighter(Fighter fighter){
    // Method body intentionally left empty
  }

  @Override
  public void equipToHero(Hero hero) {
    // Method body intentionally left empty
  }

  @Override
  public void equipToSwordMaster(SwordMaster swordMaster) {
    // Method body intentionally left empty
  }

  @Override
  public void attackTo(IUnit unit){
    // Method body intentionally left empty
  }

  @Override
  public double damagedBySword(Sword sword) {
        return 0;
    }

  @Override
  public double damagedByAxe(Axe axe) { return 0; }

  @Override
  public double damagedBySpear(Spear spear) { return 0; }

  @Override
  public boolean inRange(IUnit enemy) {
    Location enemyLocation = enemy.getLocation();
    double distance = this.getOwner().getLocation().distanceTo(enemyLocation);
    if (this.getMinRange() <= distance && distance <= this.getMaxRange()){
      return true;
    }
    return false;

  }

  @Override
  public IUnit getOwner() {
    return owner;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getPower() {
    return power;
  }

  @Override
  public int getMinRange() {
    return minRange;
  }

  @Override
  public int getMaxRange() {
    return maxRange;
  }


}
