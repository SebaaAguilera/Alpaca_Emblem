package model.unitFactory;

import model.map.Location;
import model.units.IUnit;

public class SorcererFactory implements UnitFactory {
    @Override
    public IUnit create(Location location) {return new model.units.Sorcerer(250, 3, location); }
}
