package model.items;

import model.units.IUnit;

/**
 * This class represents a DarknessBook
 * <p>
 * DarknessBooks are strong against anima and nonMagical items but weak against lightBooks.
 *
 * @author Sebastián Aguilera Valenzuela
 * @since 1.1
 */
public class DarknessBook extends AbstractMagicItem {
    /**
     * Constructor for a default item without any special behaviour.
     *
     * @param name     the name of the item
     * @param power    the power of the item (this could be the amount of damage or healing the item does)
     * @param minRange the minimum range of the item
     * @param maxRange the maximum range of the bow
     */
    public DarknessBook(String name, int power, int minRange, int maxRange) {
        super(name, power, minRange, maxRange);
    }

    @Override
    public void attackTo(IUnit unit) {
        if (unit.getEquippedItem() == null) {
            unit.attacked(this.getPower());
        } else {
            unit.getEquippedItem().attackedWithDarkness(this);
        }
    }

    @Override
    public void attackedWithLight(LightBook light) {
        this.getOwner().attacked(1.5 * this.getPower());
    }

    @Override
    public void attackedWithAnima(AnimaBook anima) {
        if (anima.getPower() - 20 <= 0) return;
        this.getOwner().attacked(this.getPower() - 20);
    }
}
