package model.factories.unitFactory;

import model.factories.itemFactory.SpearFactory;
import model.items.IEquipableItem;
import model.map.Location;
import model.units.*;

/**
 * Hero factory
 * @author Sebastián Aguilera Valenzuela
 * @since 2.5
 */
public class HeroFactory implements UnitFactory {
    private SpearFactory iFactory = new SpearFactory();

    @Override
    public IUnit create(Location location) {
        if (location.getUnit()!=null || (location.getRow()==-1 && location.getColumn()==-1)) return new NullUnit();
        return new Hero(500, 5, location); }

    @Override
    public IUnit createArmed(Location location) {
        IUnit unit = create(location);
        IEquipableItem item = iFactory.create();
        unit.saveItem(item);
        unit.equipItem(item);
        return unit;
    }
}
