package model.itemFactory;

import model.items.IEquipableItem;

import java.security.PublicKey;

public interface ItemFactory {
    public IEquipableItem create();
}
