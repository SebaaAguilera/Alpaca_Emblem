package model.unitFactory;

import model.map.Location;
import model.units.IUnit;
import model.units.SwordMaster;

public class SwordMasterFactory implements UnitFactory {
    @Override
    public IUnit create(Location location) { return new SwordMaster(300, 5, location); }
}
