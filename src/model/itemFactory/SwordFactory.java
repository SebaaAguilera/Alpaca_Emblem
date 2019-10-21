package model.itemFactory;

import model.items.IEquipableItem;
import model.items.Sword;

/**
 * Sword Factory
 * @author Sebastián Aguilera Valenzuela
 * @since 2.5
 */
public class SwordFactory implements ItemFactory{
    @Override
    public IEquipableItem create(){
        return new Sword("Sword", 40,1,2);
    }
}
