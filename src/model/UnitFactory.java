package model;

import model.units.*;
import model.map.Location;

public class UnitFactory {

    public Alpaca createAlpaca(Location location){
        return new Alpaca(200,5,location);
    }

    public Archer createArcher(Location location){
        return new Archer(250,3,location);
    }

    public Cleric createCleric(Location location){
        return new Cleric(200,2,location);
    }

    public Fighter createFighter(Location location){
        return new Fighter(300,5,location);
    }

    public Hero createHero(Location location) {
        return new Hero(400, 5, location);
    }

    public Sorcerer createSorcerer(Location location){
        return new Sorcerer(250, 3, location);
    }

    public SwordMaster createSwordMaster(Location location){
        return new SwordMaster(300, 5, location);
    }
}
