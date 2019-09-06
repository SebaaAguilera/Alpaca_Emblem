package model.items;

import model.units.Cleric;
import model.units.IUnit;

/**
 * This class represents a <i>Staff</i> type item.
 * <p>
 * A staff is an item that can heal other units but cannot counter any attack
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Staff extends AbstractNonMagicItem {

  /**
   * Creates a new Staff item.
   *
   * @param name
   *     the name of the staff
   * @param power
   *     the healing power of the staff
   * @param minRange
   *     the minimum range of the staff
   * @param maxRange
   *     the maximum range of the staff
   */
  public Staff(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
  }

  @Override
  public void equipToCleric(Cleric cleric) {
    super.equipTo(cleric);
  }

  public void healUnit(IUnit unit){ unit.healed(this.getPower()); }

  @Override
  public void attackTo(IUnit unit) {
     // Method body intentionally left empty
  }
}
