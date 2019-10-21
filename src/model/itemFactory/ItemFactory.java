package model.itemFactory;

import model.items.IEquipableItem;

/**
 * This interface represents the factory of every item in THE GAME
 * @author Sebastián Aguilera Valenzuela
 * @since 2.5
 */
public interface ItemFactory {
    public IEquipableItem create();
}
