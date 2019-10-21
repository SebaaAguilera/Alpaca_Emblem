package model.itemFactory;

import model.items.Bow;
import model.items.IEquipableItem;

/**
 * Bow factory
 * @author Sebastián Aguilera Valenzuela
 * @since 2.5
 */
public class BowFactory implements ItemFactory {
    @Override
    public IEquipableItem create() {
        return new Bow("Bow", 40, 2, 6);
    }
}
