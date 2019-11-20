package model.factories.unitFactory;

import model.factories.itemFactory.DarknessFactory;
import model.items.IEquipableItem;
import model.map.Location;
import model.units.*;

/**
 * Sorcerer factory
 * createArmed gives a Darkness Sorcerer
 * @author Sebastián Aguilera Valenzuela
 * @since 2.5
 */
public class DarkSorcererFactory implements UnitFactory {
    private DarknessFactory iFactory = new DarknessFactory();

    @Override
    public IUnit create(Location location) {
        if (location.getUnit()!=null || (location.getRow()==-1 && location.getColumn()==-1)) return new NullUnit();
        return new Sorcerer(250, 3, location); }

    @Override
    public IUnit createArmed(Location location) {
        IUnit unit = create(location);
        IEquipableItem item = iFactory.create();
        unit.saveItem(item);
        unit.equipItem(item);
        return unit;
    }
}
