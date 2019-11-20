package model.factories.unitFactory;

import model.factories.itemFactory.BowFactory;
import model.items.IEquipableItem;
import model.map.Location;
import model.units.*;
/**
 * Archer factory
 * @author Sebastián Aguilera Valenzuela
 * @since 2.5
 */
public class ArcherFactory implements UnitFactory {
    private BowFactory iFactory = new BowFactory();

    @Override
    public IUnit create(Location location) {
        if (location.getUnit()!=null || (location.getRow()==-1 && location.getColumn()==-1)) return new NullUnit();
        return new Archer(250,3,location);  }

    @Override
    public IUnit createArmed(Location location) {
        IUnit unit = create(location);
        IEquipableItem item = iFactory.create();
        unit.saveItem(item);
        unit.equipItem(item);
        return unit;
    }
}
