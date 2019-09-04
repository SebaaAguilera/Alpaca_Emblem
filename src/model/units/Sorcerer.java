package model.units;

import model.items.IEquipableItem;
import model.map.Location;

/**
 * This class represents a <i>Sorcerer</i> type unit.
 * <p>
 * This kind of unit <b>can only use Magic Items(books)</b>.
 *
 * @author Sebastián Aguilera Valenzuela
 * @since 1.1
 */
public class Sorcerer extends AbstractUnit{

    /**
     * Creates a new archer
     *
     * @param hitPoints
     *     maximum hit points of the unit
     * @param movement
     *     the amount of cells this unit can move
     * @param position
     *     the initial position of this unit
     * @param items
     *     the items carried by this unit
     */
    public Sorcerer(double hitPoints, final int movement, Location position,
                  IEquipableItem... items) {
        super(hitPoints, movement, position, 3, items);
    }

    @Override
    public void equipItem(IEquipableItem item) {
        item.equipToSorcerer(this);
    }

}
