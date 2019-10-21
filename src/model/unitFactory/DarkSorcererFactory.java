package model.unitFactory;

import model.itemFactory.DarknessFactory;
import model.items.IEquipableItem;
import model.map.Location;
import model.units.IUnit;

/**
 * Sorcerer factory
 * createArmed gives a Darkness Sorcerer
 * @author Sebastián Aguilera Valenzuela
 * @since 2.5
 */
public class DarkSorcererFactory implements UnitFactory {
    private DarknessFactory iFactory = new DarknessFactory();

    @Override
    public IUnit create(Location location) {return new model.units.Sorcerer(250, 3, location); }

    @Override
    public IUnit createArmed(Location location) {
        IUnit unit = create(location);
        IEquipableItem item = iFactory.create();
        unit.saveItem(item);
        unit.equipItem(item);
        return unit;
    }
}
