package model;

import model.items.*;

public class ItemFactory {

    public AnimaBook createAnimaBook(){ return new AnimaBook("Anima",40,1,4); }

    public DarknessBook createDarknessBook(){
        return new DarknessBook("Darkness",40,1,4);
    }

    public LightBook createLightBook(){
        return new LightBook("Light",40,1,4);
    }

    public Axe createAxe(){
        return new Axe("Axe", 60,1,1);
    }

    public Bow createBow(){
        return new Bow("Bow", 40, 2,6);
    }

    public Spear createSpear(){
        return new Spear("Spear",50,1,3);
    }

    public Sword createSword(){
        return new Sword("Sword", 40,1,2);
    }


}
