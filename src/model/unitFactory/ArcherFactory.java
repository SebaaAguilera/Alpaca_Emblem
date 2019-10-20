package model.unitFactory;

import model.map.Location;
import model.units.IUnit;

public class ArcherFactory implements UnitFactory {
    @Override
    public IUnit create(Location location) { return new model.units.Archer(250,3,location);  }
}
