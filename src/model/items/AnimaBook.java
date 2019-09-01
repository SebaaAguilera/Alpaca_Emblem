package model.items;

/**
 * This class represents a AnimaBook
 * <p>
 * AnimaBooks are strong against Light and nonMagical items but weak against DarknessBooks.
 *
 * @author Sebastián Aguilera Valenzuela
 * @since 1.0
 */
public class AnimaBook extends AbstractMagicItem  {
    /**
     * Constructor for a default item without any special behaviour.
     *
     * @param name     the name of the item
     * @param power    the power of the item (this could be the amount of damage or healing the item does)
     * @param minRange the minimum range of the item
     * @param maxRange the maximum range of the bow
     */
    public AnimaBook(String name, int power, int minRange, int maxRange) {
        super(name, power, minRange, maxRange);
    }
}
