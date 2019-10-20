package model.unitFactory;

import model.map.Location;
import model.units.Fighter;
import model.units.IUnit;

public class FighterFactory implements UnitFactory {
    @Override
    public IUnit create(Location location) { return new Fighter(300,5,location); }
}
