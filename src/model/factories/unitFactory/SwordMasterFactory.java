package model.factories.unitFactory;

import model.factories.itemFactory.SwordFactory;
import model.items.IEquipableItem;
import model.map.Location;
import model.units.IUnit;
import model.units.SwordMaster;

/**
 * SwordMaster factory
 * @author Sebastián Aguilera Valenzuela
 * @since 2.5
 */
public class SwordMasterFactory implements UnitFactory {
    private SwordFactory iFactory = new SwordFactory();

    @Override
    public IUnit create(Location location) {
        if (location.getUnit()!=null) return null;
        return new SwordMaster(300, 5, location); }

    @Override
    public IUnit createArmed(Location location) {
        if (location.getUnit()!=null) return null;
        IUnit unit = create(location);
        IEquipableItem item = iFactory.create();
        unit.saveItem(item);
        unit.equipItem(item);
        return unit;
    }
}
