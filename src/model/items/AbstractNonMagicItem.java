package model.items;

/**
 * Abstract class that defines some common information and behaviour between nonMagic items.
 * nonMagic items are strong against Magic items and Magic items are strong against nonMagic items.
 * @author Sebastián Aguilera Valenzuela
 * @since 1.1
 */
public abstract class AbstractNonMagicItem extends AbstractItem{
    /**
     * Constructor for a default item without any special behaviour.
     *
     * @param name     the name of the item
     * @param power    the power of the item (this could be the amount of damage or healing the item does)
     * @param minRange the minimum range of the item
     * @param maxRange the maximum range of the item
     */
    protected AbstractNonMagicItem(String name, int power, int minRange, int maxRange) {
        super(name, power, minRange, maxRange);
    }

    @Override
    public void attackedWithBow(Bow bow) {
        this.getOwner().attacked(bow.getPower());
    }

    @Override
    public void attackedWithSword(Sword sword) {
        this.getOwner().attacked(sword.getPower());
    }

    @Override
    public void attackedWithAxe(Axe axe) {
        this.getOwner().attacked(axe.getPower());
    }

    @Override
    public void attackedWithSpear(Spear spear) {
        this.getOwner().attacked(spear.getPower());
    }

    @Override
    public void attackedWithLight(LightBook light) {
        this.getOwner().attacked(1.5 * light.getPower());
    }

    @Override
    public void attackedWithDarkness(DarknessBook darkness) {
        this.getOwner().attacked(1.5 * darkness.getPower());
    }

    @Override
    public void attackedWithAnima(AnimaBook anima) {
        this.getOwner().attacked(1.5 * anima.getPower());
    }
}

