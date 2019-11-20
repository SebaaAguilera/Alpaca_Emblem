package model.units;

import model.items.IEquipableItem;
import model.map.InvalidLocation;

public class NullUnit extends AbstractUnit {
    /**
     * Creates a Null Unit. It literally does nothing and can't be added to a tactician unit list
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
