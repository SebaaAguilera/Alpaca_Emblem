package model.items;

import model.units.Sorcerer;

/**
 * Abstract class that defines some common information and behaviour between Magic items.
 * Magic items are strong against nonMagic items and nonMagic items are strong against Magic items.
 * @author Sebastián Aguilera Valenzuela
 * @since 1.1
 */
public abstract class AbstractMagicItem extends AbstractItem{
    /**
     * Constructor for a default item without any special behaviour.
     *
     * @param name     the name of the item
     * @param power    the power of the item (this could be the amount of damage or healing the item does)
     * @param minRange the minimum range of the item
     * @param maxRange the maximum range of the item
     */
    public AbstractMagicItem(String name, int power, int minRange, int maxRange) {
        super(name, power, minRange, maxRange);
    }

    @Override
    public void equipToSorcerer(Sorcerer sorcerer){ super.equipTo(sorcerer); }

    @Override
    public void attackedWithBow(Bow bow) {
        this.getOwner().attacked(1.5 * bow.getPower());
    }

    @Override
    public void attackedWithSword(Sword sword) {
        this.getOwner().attacked(1.5 * sword.getPower());
    }

    @Override
    public void attackedWithAxe(Axe axe) {
        this.getOwner().attacked(1.5 * axe.getPower());
    }

    @Override
    public void attackedWithSpear(Spear spear) {
        this.getOwner().attacked(1.5 * spear.getPower());
    }

    @Override
    public void attackedWithLight(LightBook light) {
        this.getOwner().attacked(light.getPower());
    }

    @Override
    public void attackedWithDarkness(DarknessBook darkness) {
        this.getOwner().attacked(darkness.getPower());
    }

    @Override
    public void attackedWithAnima(AnimaBook anima) {
        this.getOwner().attacked(anima.getPower());
    }
}
