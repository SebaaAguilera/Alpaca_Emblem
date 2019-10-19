package model.itemFactory;

import model.items.Bow;
import model.items.IEquipableItem;

public class BowFactory implements ItemFactory {
    @Override
    public IEquipableItem create() {
        return new Bow("Bow", 40, 2, 6);
    }
}
