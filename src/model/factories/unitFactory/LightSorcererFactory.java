package model.factories.unitFactory;

import model.factories.itemFactory.LightFactory;
import model.items.IEquipableItem;
import model.map.Location;
import model.units.IUnit;

/**
 * Sorcerer factory
 * createArmed gives a Light Sorcerer
 * @author Sebastián Aguilera Valenzuela
 * @since 2.5
 */
public class LightSorcererFactory implements UnitFactory {
    private LightFactory iFactory = new LightFactory();

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
