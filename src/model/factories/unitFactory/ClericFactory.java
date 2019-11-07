package model.factories.unitFactory;

import model.factories.itemFactory.StaffFactory;
import model.items.IEquipableItem;
import model.map.Location;
import model.units.IUnit;

/**
 * Cleric factory
 * @author Sebastián Aguilera Valenzuela
 * @since 2.5
 */
public class ClericFactory implements UnitFactory {
    private StaffFactory iFactory = new StaffFactory();

    @Override
    public IUnit create(Location location) { return new model.units.Cleric(200,2,location); }

    @Override
    public IUnit createArmed(Location location) {
        IUnit unit = create(location);
        IEquipableItem item = iFactory.create();
        unit.saveItem(item);
        unit.equipItem(item);
        return unit;
    }
}
