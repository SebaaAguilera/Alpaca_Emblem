package model.itemFactory;

import model.items.IEquipableItem;
import model.items.Staff;

public class StaffFactory implements ItemFactory {
    @Override
    public IEquipableItem create(){
        return new Staff("Staff", 40,1,2);
    }
}
