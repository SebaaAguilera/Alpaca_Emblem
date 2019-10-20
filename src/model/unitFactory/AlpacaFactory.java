package model.unitFactory;

import model.map.Location;
import model.units.Alpaca;
import model.units.IUnit;

public class AlpacaFactory implements UnitFactory{
    @Override
    public IUnit create(Location location) { return new Alpaca(200,5,location);}
}
