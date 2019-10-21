package model.unitFactory;

import model.itemFactory.BowFactory;
import model.items.IEquipableItem;
import model.map.Location;
import model.units.IUnit;

/**
 * Archer factory
 * @author Sebastián Aguilera Valenzuela
 * @since 2.5
 */
public class ArcherFactory implements UnitFactory {
    private BowFactory iFactory = new BowFactory();

    @Override
    public IUnit create(Location location) { return new model.units.Archer(250,3,location);  }

    @Override
    public IUnit createArmed(Location location) {
        IUnit unit = create(location);
        IEquipableItem item = iFactory.create();
        unit.saveItem(item);
        unit.equipItem(item);
        return unit;
    }
}
