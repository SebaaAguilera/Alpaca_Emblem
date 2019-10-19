package model.itemFactory;

import model.items.Axe;
import model.items.IEquipableItem;

public class AxeFactory implements ItemFactory {
    @Override
    public IEquipableItem create() {
        return new Axe("Axe", 60, 1, 1);
    }
}
