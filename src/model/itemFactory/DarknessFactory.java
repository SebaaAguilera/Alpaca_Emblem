package model.itemFactory;

import model.items.DarknessBook;
import model.items.IEquipableItem;

/**
 * Darknessbook factory
 * @author Sebastián Aguilera Valenzuela
 * @since 2.5
 */
public class DarknessFactory implements ItemFactory {
    @Override
    public IEquipableItem create() {
        return new DarknessBook("Darkness", 40, 1, 4);
    }
}