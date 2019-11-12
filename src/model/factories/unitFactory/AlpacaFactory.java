package model.factories.unitFactory;

import model.map.Location;
import model.units.Alpaca;
import model.units.IUnit;

/**
 * Alpaca factory
 * Alpacas can't equip items, so: create=createArmed
 * @author Sebastián Aguilera Valenzuela
 * @since 2.5
 */
public class AlpacaFactory implements UnitFactory{
    @Override
    public IUnit create(Location location) {
        if (location.getUnit()!=null || (location.getRow()==-1 && location.getColumn()==-1)) return null;
        return new Alpaca(200,5,location);}

    @Override
    public IUnit createArmed(Location location) {
        if (location.getUnit()!=null || (location.getRow()==-1 && location.getColumn()==-1)) return null;
        return create(location); }


}
