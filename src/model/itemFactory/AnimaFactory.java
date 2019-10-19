package model.itemFactory;

import model.items.AnimaBook;
import model.items.IEquipableItem;

public class AnimaFactory implements ItemFactory {
    @Override
    public IEquipableItem create(){ return new AnimaBook("Anima",40,1,4); }
}
