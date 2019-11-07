package model.factories.unitFactory;

import model.factories.itemFactory.AxeFactory;
import model.items.IEquipableItem;
import model.map.Location;
import model.units.Fighter;
import model.units.IUnit;

/**
 * Fighter factory
 * @author Sebastián Aguilera Valenzuela
 * @since 2.5
 */
public class FighterFactory implements UnitFactory {
    private AxeFactory iFactory = new AxeFactory();

    @Override
    public IUnit create(Location location) { return new Fighter(300,5,location); }

    @Override
    public IUnit createArmed(Location location) {
        IUnit unit = create(location);
        IEquipableItem item = iFactory.create();
        unit.saveItem(item);
        unit.equipItem(item);
        return unit;
    }
}
