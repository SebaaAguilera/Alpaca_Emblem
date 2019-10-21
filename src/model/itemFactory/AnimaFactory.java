package model.itemFactory;

import model.items.AnimaBook;
import model.items.IEquipableItem;

/**
 * Animabook factory
 * @author Sebastián Aguilera Valenzuela
 * @since 2.5
 */
public class AnimaFactory implements ItemFactory {
    @Override
    public IEquipableItem create(){ return new AnimaBook("Anima",40,1,4); }
}
