package model.itemFactory;

import model.items.IEquipableItem;
import model.items.Sword;

public class SwordFactory implements ItemFactory{
    @Override
    public IEquipableItem create(){
        return new Sword("Sword", 40,1,2);
    }
}
