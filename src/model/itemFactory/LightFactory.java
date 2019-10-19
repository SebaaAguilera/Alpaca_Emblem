package model.itemFactory;

import model.items.IEquipableItem;
import model.items.LightBook;

public class LightFactory implements ItemFactory {
    @Override
    public IEquipableItem create(){
        return new LightBook("Light",40,1,4);
    }
}
