package model.units;

import model.items.*;
import model.map.Field;
import model.map.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public abstract class AbstractTestUnit implements ITestUnit {

  private Alpaca targetAlpaca;
  private Archer targetArcher;
  private Cleric targetCleric;
  private Fighter targetFighter;
  private Hero targetHero;
  private SwordMaster targetSwordMaster;

  protected Bow testBow;
  protected Axe testAxe;
  protected Sword testSword;
  protected Staff testStaff;
  protected Spear testSpear;
  protected LightBook targetLightBook;

  protected Bow bow;
  protected Field field;
  protected Axe axe;
  protected Sword sword;
  protected Staff staff;
  protected Spear spear;
  protected LightBook lightBook;


  @Override
  public void setTargetAlpaca() {
    targetAlpaca = new Alpaca(50, 2, field.getCell(1, 0));
  }

  /**
   * Sets up the units and weapons to be tested
   */
  @BeforeEach
  public void setUp() {
    setField();
    setTestUnit();
    setTargetAlpaca();
    setWeapons();
  }

  /**
   * Set up the game field
   */
  @Override
  public void setField() {
    this.field = new Field();
    this.field.addCells(true, new Location(0, 0), new Location(0, 1), new Location(0, 2),
        new Location(1, 0), new Location(1, 1), new Location(1, 2), new Location(2, 0),
        new Location(2, 1), new Location(2, 2));
  }

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public abstract void setTestUnit();

  /**
   * Creates a set of testing weapons
   */
  @Override
  public void setWeapons() {
    this.axe = new Axe("Axe", 25, 1, 2);
    this.sword = new Sword("Sword", 25, 1, 2);
    this.spear = new Spear("Spear", 25, 1, 2);
    this.staff = new Staff("Staff", 25, 1, 2);
    this.bow = new Bow("Bow", 15, 2, 3);

    this.lightBook = new LightBook("LightBook", 25, 1, 2);
  }

  /**
   * Creates a set of testing combat weapons
   */
  @Override
  public void setCombatsWeapons() {
    this.testAxe = new Axe("testAxe", 25, 1, 2);
    this.testSword = new Sword("testSword", 25, 1, 2);
    this.testSpear = new Spear("testSpear", 25, 1, 2);
    this.testStaff = new Staff("testStaff", 25, 1, 2);
    this.testBow = new Bow("testBow", 25, 2, 3);
  }

  /**
   * Checks that the constructor works properly.
   */
  @Override
  @Test
  public void constructorTest() {
    assertEquals(500, getTestUnit().getCurrentHitPoints());
    assertEquals(2, getTestUnit().getMovement());
    assertEquals(new Location(0, 0), getTestUnit().getLocation());
    assertTrue(getTestUnit().getItems().isEmpty());
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public abstract IUnit getTestUnit();

  /**
   * Checks if the axe is equipped correctly to the unit
   */
  @Override
  @Test
  public void equipAxeTest() {
    assertNull(getTestUnit().getEquippedItem());
    checkEquippedItem(getAxe());
  }

  /**
   * Tries to equip a weapon to the alpaca and verifies that it was not equipped
   *
   * @param item
   *     to be equipped
   */
  @Override
  public void checkEquippedItem(IEquipableItem item) {
    assertNull(getTestUnit().getEquippedItem());
    getTestUnit().saveItem(item);
    getTestUnit().equipItem(item);
    assertNull(getTestUnit().getEquippedItem());
  }

  /**
   * @return the test axe
   */
  @Override
  public Axe getAxe() {
    return axe;
  }

  @Override
  @Test
  public void equipSwordTest() {
    checkEquippedItem(getSword());
  }

  /**
   * @return the test sword
   */
  @Override
  public Sword getSword() {
    return sword;
  }

  @Override
  @Test
  public void equipSpearTest() {
    checkEquippedItem(getSpear());
  }

  /**
   * @return the test spear
   */
  @Override
  public Spear getSpear() {
    return spear;
  }

  @Override
  @Test
  public void equipStaffTest() {
    checkEquippedItem(getStaff());
  }

  /**
   * @return the test staff
   */
  @Override
  public Staff getStaff() {
    return staff;
  }

  @Override
  @Test
  public void equipBowTest() {
    checkEquippedItem(getBow());
  }

  /**
   * @return the test bow
   */
  @Override
  public Bow getBow() {
    return bow;
  }

  @Test
  @Override
  public void testMaxSavingItems() {
    ArrayList<IEquipableItem> Items = new ArrayList<IEquipableItem>();
    assertEquals(Items, getTestUnit().getItems());
    setWeapons();
    getTestUnit().saveItem(getAxe());
    Items.add(getAxe());
    getTestUnit().saveItem(getBow());
    Items.add(getBow());
    getTestUnit().saveItem(getSword());
    Items.add(getSword());
    getTestUnit().saveItem(getStaff());
    assertEquals(Items.size(), getTestUnit().getMaxItems());
    assertEquals(Items, getTestUnit().getItems());
  }

  @Override
  @Test
  public void testTrading() {
      setUnits();
      Hero hero = getTargetHero();
      IUnit unit = getTestUnit();

      IEquipableItem item = hero.getEquippedItem();

      hero.giveItem(unit, item);

      assertNull(hero.getEquippedItem());
      assertNull(item.getOwner());
      assertEquals(true, getTestUnit().getItems().contains(item));
  }

    /**
   * Checks if the unit moves correctly
   */
  @Override
  @Test
  public void testMovement() {
    getTestUnit().moveTo(getField().getCell(2, 2));
    assertEquals(new Location(0, 0), getTestUnit().getLocation());

    getTestUnit().moveTo(getField().getCell(0, 2));
    assertEquals(new Location(0, 2), getTestUnit().getLocation());

    getField().getCell(0, 1).setUnit(getTargetAlpaca());
    getTestUnit().moveTo(getField().getCell(0, 1));
    assertEquals(new Location(0, 2), getTestUnit().getLocation());
  }

  /**
   * @return the test field
   */
  @Override
  public Field getField() {
    return field;
  }

  @Override
  public void setUnits(){
    setCombatsWeapons();
    targetAlpaca = new Alpaca(1001, 2, field.getCell(2, 0));
    targetArcher = new Archer(1001, 2, field.getCell(1, 0));
    targetArcher.saveItem(testBow);
    targetArcher.equipItem(testBow);
    targetCleric = new Cleric(1001, 2, field.getCell(1, 0));
    targetCleric.saveItem(testSpear);
    targetCleric.equipItem(testStaff);
    targetFighter = new Fighter(1001, 2, field.getCell(1, 0));
    targetFighter.saveItem(testAxe);
    targetFighter.equipItem(testAxe);
    targetHero = new Hero(1001, 2, field.getCell(1, 0));
    targetHero.saveItem(testSpear);
    targetHero.equipItem(testSpear);
    targetSwordMaster = new SwordMaster(1001, 2, field.getCell(1, 0));
    targetSwordMaster.saveItem(testSword);
    targetSwordMaster.equipItem(testSword);
  }


  /**
   * @return the target Alpaca
   */
  @Override
  public Alpaca getTargetAlpaca() {
    return targetAlpaca;
  }

  /**
   * @return the target Archer
   */
  @Override
  public Archer getTargetArcher() {
    return targetArcher;
  }

  /**
   * @return the target Cleric
   */
  @Override
  public Cleric getTargetCleric() {
    return targetCleric;
  }

  /**
   * @return the target Fighter
   */
  @Override
  public Fighter getTargetFighter() {
    return targetFighter;
  }

  /**
   * @return the target Hero
   */
  @Override
  public Hero getTargetHero() {
    return targetHero;
  }

  /**
   * @return the target SwordMaster
   */
  @Override
  public SwordMaster getTargetSwordMaster() {
    return targetSwordMaster;
  }


  @Override
  @Test
  public void testCombat() {
    //every unit have different test for combat
  }
}
