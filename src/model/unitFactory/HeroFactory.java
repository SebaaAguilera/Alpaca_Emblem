package model.unitFactory;

import model.map.Location;
import model.units.Hero;
import model.units.IUnit;

public class HeroFactory implements UnitFactory {
    @Override
    public IUnit create(Location location) { return new Hero(400, 5, location); }
}
