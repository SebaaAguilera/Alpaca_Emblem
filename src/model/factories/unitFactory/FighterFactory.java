package model.factories.unitFactory;

import model.factories.itemFactory.AxeFactory;
import model.items.IEquipableItem;
import model.map.Location;
import model.units.*;

/**
 * Fighter factory
 * @author Sebastián Aguilera Valenzuela
 * @since 2.5
 */
public class FighterFactory implements UnitFactory {
    private AxeFactory iFactory = new AxeFactory();

    @Override
    public IUnit create(Location location) {
        if (location.getUnit()!=null || (location.getRow()==-1 && location.getColumn()==-1)) return new NullUnit();
        return new Fighter(300,5,location); }

    @Override
    public IUnit createArmed(Location location) {
        IUnit unit = create(location);
        IEquipableItem item = iFactory.create();
        unit.saveItem(item);
        unit.equipItem(item);
        return unit;
    }
}
