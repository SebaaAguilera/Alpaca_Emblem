package model.unitFactory;

import model.map.Location;
import model.units.IUnit;

public interface UnitFactory {
    IUnit create(Location location);
}
