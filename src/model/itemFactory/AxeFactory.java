package model.itemFactory;

import model.items.Axe;
import model.items.IEquipableItem;

/**
 * Axe factory
 * @author Sebastián Aguilera Valenzuela
 * @since 2.5
 */
public class AxeFactory implements ItemFactory {
    @Override
    public IEquipableItem create() {
        return new Axe("Axe", 60, 1, 1);
    }
}
