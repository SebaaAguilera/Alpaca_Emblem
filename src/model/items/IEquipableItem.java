package model.items;

import model.units.*;

/**
 * This interface represents the <i>weapons</i> that the units of the game can use.
 * <p>
 * The signature for all the common methods of the weapons are defined here. Every weapon have a
 * base damage and is strong or weak against other type of weapons.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public interface IEquipableItem {

  /**
   * Equips this item to a unit.
   *
   * @param unit
   *     the unit that will be quipped with the item
   */
  void equipTo(IUnit unit);

  /**
   * unequip the item from the unit
   */
  void unEquipFrom();

  /**
   * Equips item to an Archer if it's possible
   *
   * @param archer
   *    the Archer that is supposed to equip the item
   */
  void equipToArcher(Archer archer);

  /**
   * Equips item to a Cleric if it's possible
   *
   * @param cleric
   *    the Cleric that is supposed to equip the item
   */
  void equipToCleric(Cleric cleric);

  /**
   * Equips item to a Fighter if it's possible
   *
   * @param fighter
   *    the Fighter that is supposed to equip the item
   */
  void equipToFighter(Fighter fighter);

  /**
   * Equips item to a Hero if it's possible
   *
   * @param hero
   *    the Hero that is supposed to equip the item
   */
  void equipToHero(Hero hero);

  /**
   *  Equips item to a Sword Master if it's possible
   *
   * @param swordMaster
   *    the Sword Master that is supposed to equip the item
   */
  void equipToSwordMaster(SwordMaster swordMaster);

    /**
     * Equips item to a Sorcerer if it's possible
     *
     * @param sorcerer the Sorcerer that is supposed to equip the item
     */
    void equipToSorcerer(Sorcerer sorcerer);

  /**
   * @return the unit that has currently equipped this item
   */
  IUnit getOwner();

  /**
   * @return the name of the item
   */
  String getName();

  /**
   * @return the power of the item
   */
  int getPower();

  /**
   * @return the minimum range of the item
   */
  int getMinRange();

  /**
   * @return the maximum range of the item
   */
  int getMaxRange();

  /**
   * Verify if a weapon is in range to attack an unit
   * @param unit that is being attacked
   * @return true if is in range, false if not
   */
  boolean inRange(IUnit unit);

    /**Attacks an unit
     * @param unit
     *  the unit is going to be attacked
     */
  void attackTo(IUnit unit);

    /**
     * Says to a weapon that is being attacked by a bow
     *
     * @param bow the bow that attacks
     */
    void attackedWithBow(Bow bow);

    /**
   * Aays to a weapon that is being attacked by a sword
     * @param sword
     *    the sword that attacks
     */
  void attackedWithSword(Sword sword);

  /**
   * Says to a weapon that is being attacked by an axe
   * @param axe
   *    the axe that attacks
   */
  void attackedWithAxe(Axe axe);

  /**
   * Says to a weapon that is being attacked by a spear
   * @param spear
   *    the spear that attacks
   */
  void attackedWithSpear(Spear spear);

  /**
   * Says to a weapon that is being attacked by a light book
   * @param light
   *    the light book that attacks
   */
  void attackedWithLight(LightBook light);

  /**
   * Says to a weapon that is being attacked by a darkness book
   * @param darkness
   *    the darkness nook that attacks
   */
  void attackedWithDarkness(DarknessBook darkness);

  /**
   * Says to a weapon that is being attacked by an anima book
   * @param anima
   *    the anima book that attacks
   */
  void attackedWithAnima(AnimaBook anima);

  /**
   * Heals an unit
   * @param unit
   *    the unit is going to be healed
   */
  void healUnit(IUnit unit);


}
