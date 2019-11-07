package model.factories.unitFactory;

import model.map.Location;
import model.units.IUnit;

/**
 * This interface represents the factory of every unit in the game
 * @author Sebastián Aguilera Valenzuela
 * @since 2.5
 */
public interface UnitFactory {
    /**
     * @param location of the unit
     * @return a predefined unit
     */
    IUnit create(Location location);

    /**
     * @param location of the unit
     * @return a predefined unit with a predefined weapon equipped
     *          (The alpaca has a special case)
     */
    IUnit createArmed(Location location);
}
