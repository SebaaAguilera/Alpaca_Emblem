package model.unitFactory;

import model.itemFactory.SpearFactory;
import model.items.IEquipableItem;
import model.map.Location;
import model.units.Hero;
import model.units.IUnit;

/**
 * Hero factory
 * @author Sebastián Aguilera Valenzuela
 * @since 2.5
 */
public class HeroFactory implements UnitFactory {
    private SpearFactory iFactory = new SpearFactory();

    @Override
    public IUnit create(Location location) { return new Hero(400, 5, location); }

    @Override
    public IUnit createArmed(Location location) {
        IUnit unit = create(location);
        IEquipableItem item = iFactory.create();
        unit.saveItem(item);
        unit.equipItem(item);
        return unit;
    }
}
