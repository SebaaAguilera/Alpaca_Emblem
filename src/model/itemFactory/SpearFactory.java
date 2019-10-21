package model.itemFactory;

import model.items.IEquipableItem;
import model.items.Spear;

/**
 * Spear factory
 * @author Sebastián Aguilera Valenzuela
 * @since 2.5
 */
public class SpearFactory implements ItemFactory {
    @Override
    public IEquipableItem create(){ return new Spear("Spear",50,1,3); }

}
