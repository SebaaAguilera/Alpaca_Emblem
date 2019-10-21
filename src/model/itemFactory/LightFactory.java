package model.itemFactory;

import model.items.IEquipableItem;
import model.items.LightBook;

/**
 * Lightbook factory
 * @author Sebastián Aguilera Valenzuela
 * @since 2.5
 */
public class LightFactory implements ItemFactory {
    @Override
    public IEquipableItem create(){
        return new LightBook("Light",40,1,4);
    }
}
