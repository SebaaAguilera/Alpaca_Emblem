package model.units;

import model.items.IEquipableItem;
import model.map.InvalidLocation;

/**
 * This class represents a <i>NullUnit</i> type unit.
 * <p>
 * This type of Unit It literally does nothing and can't be added to a tactician unit list
 *
 * @author Sebastián Aguilera Valenzuela
 * @since 2.5
 */
public class NullUnit extends AbstractUnit {
    /**
     * Creates a Null Unit.
     */
    public NullUnit() {
        super(-1, -1, new InvalidLocation(), 0);
    }

    @Override
    public void saveItem(IEquipableItem item) {
    }

    @Override
    public void giveItem(IUnit unit, IEquipableItem item) {
    }

    @Override
    public void equipItem(IEquipableItem item) {
    }

    @Override
    public void combat(IUnit unit) {
    }
}
