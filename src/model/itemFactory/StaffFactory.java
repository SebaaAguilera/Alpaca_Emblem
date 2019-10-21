package model.itemFactory;

import model.items.IEquipableItem;
import model.items.Staff;

/**
 * Staff Factory
 * @author Sebastián Aguilera Valenzuela
 * @since 2.5
 */
public class StaffFactory implements ItemFactory {
    @Override
    public IEquipableItem create(){
        return new Staff("Staff", 40,1,2);
    }
}



