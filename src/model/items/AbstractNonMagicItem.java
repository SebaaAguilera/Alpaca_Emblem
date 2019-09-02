package model.items;

/**
 * Abstract class that defines some common information and behaviour between nonMagic items.
 *
 * @author Sebastián Aguilera Valenzuela
 * @since 1.0
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
    public AbstractNonMagicItem(String name, int power, int minRange, int maxRange) {
        super(name, power, minRange, maxRange);
    }

    @Override
    public void attackedWithSword(Sword sword) {
        this.getOwner().attacked(this.getPower());
    }

    @Override
    public void attackedWithAxe(Axe axe) {
        this.getOwner().attacked(this.getPower());
    }

    @Override
    public void attackedWithSpear(Spear spear) {
        this.getOwner().attacked(this.getPower());
    }

    @Override
    public void attackedWithLight(LightBook light) { this.getOwner().attacked(1.5*this.getPower()); }

    @Override
    public void attackedWithDarkness(DarknessBook darkness) {
        this.getOwner().attacked(1.5*this.getPower());
    }

    @Override
    public void attackedWithAnima(AnimaBook anima) {
        this.getOwner().attacked(1.5*this.getPower());
    }
}

