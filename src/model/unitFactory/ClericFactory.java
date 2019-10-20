package model.unitFactory;

import model.map.Location;
import model.units.IUnit;

public class ClericFactory implements UnitFactory {
    @Override
    public IUnit create(Location location) { return new model.units.Cleric(200,2,location); }
}
