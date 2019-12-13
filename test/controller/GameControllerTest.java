package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import model.Tactician;
import model.items.AnimaBook;
import model.factories.unitFactory.*;
import model.factories.itemFactory.*;
import model.items.DarknessBook;
import model.items.IEquipableItem;
import model.map.Field;
import model.map.Location;
import model.units.IUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Ignacio Slater Muñoz
 * @since v2.0
 */
class GameControllerTest {

  private GameController controller;
  private long randomSeed;
  private Random random;
  private List<String> testTacticians;

  private AlpacaFactory alpaca;
  private ArcherFactory archer;
  private ClericFactory cleric;
  private FighterFactory fighter;
  private HeroFactory hero;
  private SwordMasterFactory swordMaster;
  private AnimaSorcererFactory animaSorcerer;
  private DarkSorcererFactory darkSorcerer;
  private LightSorcererFactory lightSorcerer;

  private AnimaFactory anima;
  private AxeFactory axe;
  private BowFactory bow;
  private DarknessFactory dark;
  private LightFactory light;
  private SpearFactory spear;
  private StaffFactory staff;
  private SwordFactory sword;


  @BeforeEach
  void setUp() {
    controller = new GameController(4, 5);
    testTacticians = List.of("Player 0", "Player 1", "Player 2","Player 3");
    randomSeed = controller.getSeed();
    random = new Random(randomSeed);

    alpaca = new AlpacaFactory();
    archer = new ArcherFactory();
    cleric = new ClericFactory();
    fighter = new FighterFactory();
    hero   = new HeroFactory();
    swordMaster = new SwordMasterFactory() ;
    animaSorcerer = new AnimaSorcererFactory();
    darkSorcerer = new DarkSorcererFactory();
    lightSorcerer = new LightSorcererFactory();

    anima = new AnimaFactory();
    axe = new AxeFactory();
    bow = new BowFactory();
    dark = new DarknessFactory();
    light = new LightFactory();
    spear = new SpearFactory();
    staff = new StaffFactory();
    sword = new SwordFactory();
  }

  @Test
  void getTacticians() {
    List<Tactician> tacticians = controller.getTacticians();
    assertEquals(4, tacticians.size());
    for (int i = 0; i < tacticians.size(); i++) {
      assertEquals("Player " + i, tacticians.get(i).getName());
    }
  }

  @Test
  void getGameMap() {
    Field gameMap = controller.getGameMap();
    assertEquals(5, gameMap.getSize());
    assertTrue(controller.getGameMap().isConnected());

    Field testMap = new Field(5,randomSeed);
    assertTrue(testMap.isConnected());

    for (int i = 0; i < 5; i++){
      for (int j = 0; j < 5; j++){
        Location gm = gameMap.getCell(i,j);
        Location tm = testMap.getCell(i,j);
        assertEquals(gm.getNeighbours().size(),tm.getNeighbours().size());
        // I had to convert the Set to an arraylist. Idk why comparing two sets stopped working
        assertTrue(new ArrayList<>(gm.getNeighbours()).containsAll(tm.getNeighbours()));
        assertTrue(new ArrayList<>(tm.getNeighbours()).containsAll(gm.getNeighbours()));
      }
    }
  }

   private void roundSequence(List<Tactician> sequence, Tactician turnOwner){
      sequence.clear();
      int index;
      if (turnOwner!=null) {
        do {
          index = random.nextInt(controller.getTacticians().size());
        } while(controller.getTacticians().get(index).getName().equals(turnOwner.getName()));
        sequence.add(controller.getTacticians().get(index));
      }
      while (sequence.size()!=controller.getTacticians().size()){
        do {
          index = random.nextInt(controller.getTacticians().size());
        } while(sequence.contains(controller.getTacticians().get(index)));
        sequence.add(controller.getTacticians().get(index));
      }
  }

  @Test
  void getTurnOwner() {
    List<Tactician> testSequence = new ArrayList<>(controller.getTacticians());
    Tactician testTurnOwner = null;
    controller.initGame(10);
    for (int i = 0; i < 40; i++){
        if (i%4==0){
          roundSequence(testSequence, testTurnOwner);
          testTurnOwner = testSequence.get(0);
          assertEquals(controller.getTurnOwner().getName(),testTurnOwner.getName());
          controller.endTurn();

        } else {
          testTurnOwner =  testSequence.get(i%4);
          assertEquals(controller.getTurnOwner().getName(),testTurnOwner.getName());
          controller.endTurn();
        }
    }
  }

  @Test
  void getRoundNumber() {
    controller.initGame(10);
    for (int i = 1; i < 10; i++) {
      assertEquals(i, controller.getRoundNumber());
      for (int j = 0; j < 4; j++) {
        controller.endTurn();
      }
    }
  }

  @Test
  void getMaxRounds() {
    Random randomTurnSequence = new Random();
    IntStream.range(0, 50).map(i -> randomTurnSequence.nextInt() & Integer.MAX_VALUE).forEach(nextInt -> {
      controller.initGame(nextInt);
      assertEquals(nextInt, controller.getMaxRounds());
    });
    controller.initEndlessGame();
    assertEquals(-1, controller.getMaxRounds());
  }

  @Test
  void endTurn() {
    controller.initGame(2);
    List <Tactician> roundSequence= controller.getRoundSequence();
    Tactician firstPlayer = controller.getTurnOwner();
    Tactician secondPlayer = roundSequence.get(roundSequence.indexOf(controller.getTurnOwner())+1);

    assertNotEquals(secondPlayer.getName(), firstPlayer.getName());

    controller.endTurn();
    assertNotEquals(firstPlayer.getName(), controller.getTurnOwner().getName());
    assertEquals(secondPlayer.getName(), controller.getTurnOwner().getName());

    controller.endTurn();
    controller.endTurn();

    Tactician fourthPlayer = controller.getTurnOwner();
    controller.endTurn();
    assertNotEquals(fourthPlayer.getName(), controller.getTurnOwner().getName());
  }

  @Test
  void removeTactician() {
    // Test tactician to set as a turnOwner
    controller.setTurnOwner(new Tactician("El tontito la lleva"));

    assertEquals(4, controller.getTacticians().size());
    controller.getTacticians()
        .forEach(tactician -> Assertions.assertTrue(testTacticians.contains(tactician.getName())));

    controller.removeTactician("Player 0");
    assertEquals(3, controller.getTacticians().size());
    controller.getTacticians().forEach(tactician -> assertNotEquals("Player 0", tactician));
    controller.getTacticians()
        .forEach(tactician -> Assertions.assertTrue(testTacticians.contains(tactician.getName())));

    controller.removeTactician("Player 5");
    assertEquals(3, controller.getTacticians().size());
    controller.getTacticians()
        .forEach(tactician -> Assertions.assertTrue(testTacticians.contains(tactician.getName())));
  }

  @Test
  void getWinners() {
    controller.initGame(2);
    IntStream.range(0, 8).forEach(i -> controller.endTurn());
    assertEquals(4, controller.getWinners().size());
    controller.getWinners()
        .forEach(player -> Assertions.assertTrue(testTacticians.contains(player)));

    controller.initGame(2);
    IntStream.range(0, 4).forEach(i -> controller.endTurn());
    assertEquals(0,controller.getWinners().size());
    controller.removeTactician("Player 0");
    controller.removeTactician("Player 2");
    IntStream.range(0, 2).forEach(i -> controller.endTurn());
    List<String> winners = controller.getWinners();
    assertEquals(2, winners.size());
    assertTrue(List.of("Player 1", "Player 3").containsAll(winners));

    controller.initEndlessGame();
    for (int i = 0; i < 2; i++) {
      assertEquals(0,controller.getWinners().size());
      controller.removeTactician("Player " + i);
    }
    assertTrue(List.of("Player 3").containsAll(controller.getWinners()));
  }

  @Test
  void addUnit(){
    controller.setTurnOwner(controller.getTacticians().get(0));
    List<IUnit> units = new ArrayList<>();

    IUnit al = alpaca.createArmed(controller.getGameMap().getCell(0,0));
    units.add(al);
    controller.addUnit(al);
    assertTrue(controller.getTurnOwner().getUnits().containsAll(units));
    assertEquals(units.size(),controller.getTurnOwner().getUnits().size());

    IUnit an = animaSorcerer.createArmed(controller.getGameMap().getCell(0,0));
    assertFalse(controller.getTurnOwner().getUnits().contains(an));
    assertEquals(units.size(),controller.getTurnOwner().getUnits().size());

    IUnit al2 = alpaca.create(controller.getGameMap().getCell(5,4)); // out of the map
    assertFalse(controller.getTurnOwner().getUnits().contains(al2));
    assertEquals(units.size(),controller.getTurnOwner().getUnits().size());

    IUnit an2 = animaSorcerer.createArmed(controller.getGameMap().getCell(0,1));
    units.add(an2);
    controller.addUnit(an2);
    assertTrue(controller.getTurnOwner().getUnits().containsAll(units));
    assertEquals(units.size(),controller.getTurnOwner().getUnits().size());

    IUnit li = lightSorcerer.createArmed(controller.getGameMap().getCell(1,1));
    units.add(li);
    controller.addUnit(li);
    assertTrue(controller.getTurnOwner().getUnits().containsAll(units));
    assertEquals(units.size(),controller.getTurnOwner().getUnits().size());

    IUnit sm = swordMaster.createArmed(controller.getGameMap().getCell(2,1));
    units.add(sm);
    controller.addUnit(sm);
    assertTrue(controller.getTurnOwner().getUnits().containsAll(units));
    assertEquals(units.size(),controller.getTurnOwner().getUnits().size());

    IUnit sm2 = animaSorcerer.create(controller.getGameMap().getCell(2,0));
    assertFalse(controller.getTurnOwner().getUnits().contains(sm2));
    assertEquals(units.size(),controller.getTurnOwner().getUnits().size());
  }

  @Test
  void getSelectedUnit() {
    IUnit alp = alpaca.create(controller.getGameMap().getCell(0,0));
    controller.setTurnOwner(controller.getTacticians().get(0));
    controller.addUnit(alp); // aplicar observer para que esto funcione
    controller.selectUnitIn(0,0);
    assertEquals(alp,controller.getSelectedUnit());
  }

  @Test
  void selectUnitIn() {
    IUnit alp = alpaca.create(controller.getGameMap().getCell(0,0));
    controller.setTurnOwner(controller.getTacticians().get(0));
    controller.addUnit(alp);
    controller.selectUnitIn(0,0);
    assertEquals(alp,controller.getSelectedUnit());

    assertEquals(alp.getMaxHitPoints(),controller.getSelectedUnitMaxHP());
    assertEquals(alp.getCurrentHitPoints(),controller.getSelectedUnitHP());

    controller.setTurnOwner(controller.getTacticians().get(1));
    controller.selectUnitIn(0,0);
    assertNull(controller.getSelectedUnit());
  }

  @Test
  void moveUnitTo(){
    assertNull(controller.getGameMap().getCell(1,0).getUnit());
    IUnit alp = alpaca.create(controller.getGameMap().getCell(1,0));
    IUnit ds = darkSorcerer.createArmed(controller.getGameMap().getCell(1,1));
    controller.setTurnOwner(controller.getTacticians().get(0));
    controller.addUnit(alp);
    controller.addUnit(ds);
    assertNull(controller.getGameMap().getCell(0,0).getUnit());
    assertEquals(alp,controller.getGameMap().getCell(1,0).getUnit());

    controller.selectUnitIn(1,0);
    // Move the alpaca
    controller.moveUnitTo(0,0);
    assertNull(controller.getGameMap().getCell(1,0).getUnit());
    assertEquals(alp,controller.getGameMap().getCell(0,0).getUnit());

    // Try to move the alpaca twice will have no effects
    controller.moveUnitTo(1,0);
    assertNull(controller.getGameMap().getCell(1,0).getUnit());
    assertEquals(alp,controller.getGameMap().getCell(0,0).getUnit());

    // Move another unit will work
    controller.selectUnitIn(1,1);
    controller.moveUnitTo(1,0);
    assertNull(controller.getGameMap().getCell(1,1).getUnit());
    assertEquals(ds,controller.getGameMap().getCell(1,0).getUnit());
  }

  @Test
  void getItems() {
    IUnit arc = archer.create(controller.getGameMap().getCell(0,0));
    controller.setTurnOwner(controller.getTacticians().get(0));
    controller.addUnit(arc);
    List<IEquipableItem> items = new ArrayList<>();
    IEquipableItem ab = anima.create();
    IEquipableItem b = bow.create();
    items.add(ab);
    items.add(b);
    controller.selectUnitIn(0,0);
    controller.saveItem(ab);
    controller.saveItem(b);
    assertEquals(items,controller.getItems());
  }

  @Test
  void equipItem() {
    IUnit arc = archer.create(controller.getGameMap().getCell(0,0));
    controller.setTurnOwner(controller.getTacticians().get(0));
    controller.addUnit(arc);
    IEquipableItem ab = anima.create();
    IEquipableItem b = bow.create();
    controller.selectUnitIn(0,0);
    controller.saveItem(ab);
    controller.saveItem(b);
    controller.equipItem(0);
    assertNull(controller.getTurnOwner().getSelectedUnit().getEquippedItem());
    controller.equipItem(1);
    assertEquals(b,controller.getTurnOwner().getSelectedUnit().getEquippedItem());
  }

  @Test
  void useItemOn() {
    // Setea un evento a emular
    IUnit fi = fighter.createArmed(controller.getGameMap().getCell(0,0));
    IUnit he = hero.createArmed(controller.getGameMap().getCell(1,0));
    IUnit cl = cleric.createArmed(controller.getGameMap().getCell(2,0));

    controller.setTurnOwner(controller.getTacticians().get(0));
    controller.addUnit(he);
    controller.addUnit(cl);

    controller.setTurnOwner(controller.getTacticians().get(1));
    controller.addUnit(fi);
    controller.selectUnitIn(0,0);

    // Hero combat fighter
    double heroHP = he.getCurrentHitPoints();
    double fighterHP = controller.getSelectedUnitHP();
    controller.useItemOn(1,0);
    assertEquals(fighterHP-(he.getEquippedItem().getPower()-20), controller.getSelectedUnitHP());
    assertEquals(heroHP-(controller.getSelectedUnit().getEquippedItem().getPower()*1.5),he.getCurrentHitPoints());

    controller.setTurnOwner(controller.getTacticians().get(0));
    controller.selectUnitIn(2,0);
    // Hero try to combat Cleric
    heroHP = he.getCurrentHitPoints();
    double clericHP = controller.getSelectedUnitHP();
    controller.useItemOn(0,0);
    assertEquals(heroHP, he.getCurrentHitPoints());
    assertEquals(clericHP,controller.getSelectedUnitHP());

    // Cleric heal Hero
    controller.selectUnitIn(2,0);
    heroHP = he.getCurrentHitPoints();
    clericHP = controller.getSelectedUnitHP();
    controller.useItemOn(1,0);
    assertEquals(heroHP+controller.getSelectedUnit().getEquippedItem().getPower(), he.getCurrentHitPoints());
    assertEquals(clericHP,controller.getSelectedUnitHP());

    // Cleric try to heal Fighter
    fighterHP = fi.getCurrentHitPoints();
    clericHP = controller.getSelectedUnitHP();
    controller.useItemOn(0,0);
    assertEquals(fighterHP, fi.getCurrentHitPoints());
    assertEquals(clericHP,controller.getSelectedUnitHP());
  }

  @Test
  void selectItem() {
    IUnit ar = archer.createArmed(controller.getGameMap().getCell(0,0));

    controller.setTurnOwner(controller.getTacticians().get(0));
    controller.addUnit(ar);
    controller.selectUnitIn(0,0);
    controller.selectItem(0);
    assertEquals("Bow",controller.getSelectedItem().getName());
    assertEquals(40,controller.getSelectedUnitItemPower());
  }

  @Test
  void giveItemTo() {
    IUnit he = hero.createArmed(controller.getGameMap().getCell(1,0));
    IUnit cl = cleric.createArmed(controller.getGameMap().getCell(2,0));

    controller.setTurnOwner(controller.getTacticians().get(0));
    controller.addUnit(he);
    controller.addUnit(cl);

    List<IEquipableItem> items = new ArrayList<>();
    items.add(cl.getEquippedItem());
    items.add(he.getEquippedItem());

    controller.selectUnitIn(1,0);
    controller.selectItem(0);
    controller.giveItemTo(2,0);

    assertEquals(new ArrayList<>(),he.getItems());
    assertEquals(items,cl.getItems());
  }

  @Test
  void gameOver(){
    controller.initGame(2);
    IUnit he = hero.create(controller.getGameMap().getCell(0,0));
    IUnit fi = fighter.create(controller.getGameMap().getCell(1,0));

    IUnit li = lightSorcerer.create(controller.getGameMap().getCell(0,1));
    IUnit arc = archer.create(controller.getGameMap().getCell(1,1));

    IUnit an = animaSorcerer.create(controller.getGameMap().getCell(2,0));
    AnimaBook deadlyBook = new AnimaBook("Deadly dead anima book",1000,1,10);

    IUnit dk = darkSorcerer.create(controller.getGameMap().getCell(2,1));
    DarknessBook theDarkBook = new DarknessBook("Darker darkness book",40,1,10);

    Tactician FirstPlayer = controller.getRoundSequence().get(0);
    controller.setTurnOwner(FirstPlayer);
    controller.addUnit(he);
    controller.addUnit(fi);

    Tactician SecondPlayer = controller.getRoundSequence().get(1);
    controller.setTurnOwner(SecondPlayer);
    controller.addUnit(li);
    controller.addUnit(arc);

    Tactician ThirdPlayer = controller.getRoundSequence().get(2);
    controller.setTurnOwner(ThirdPlayer);
    controller.addUnit(an);
    controller.selectUnitIn(2,0);
    controller.saveItem(deadlyBook);
    controller.equipItem(0);
    Tactician FourthPlayer = controller.getRoundSequence().get(3);

    controller.useItemOn(0,0);
    assertFalse(controller.getTacticians().contains(FirstPlayer));
    assertNull(fi.getLocation().getUnit());
    assertNull(he.getLocation().getUnit());

    controller.useItemOn(0,1);
    assertTrue(controller.getTacticians().contains(SecondPlayer));
    controller.useItemOn(1,1);
    assertFalse(controller.getTacticians().contains(SecondPlayer));
    assertNull(li.getLocation().getUnit());
    assertNull(arc.getLocation().getUnit());
    controller.setTurnOwner(FourthPlayer);
    controller.addUnit(dk);
    controller.selectUnitIn(2,1);
    controller.saveItem(theDarkBook);
    controller.equipItem(0);
    controller.useItemOn(2,0);
    assertFalse(controller.getTacticians().contains(FourthPlayer));
    assertNull(li.getLocation().getUnit());
    assertEquals(ThirdPlayer,controller.getTurnOwner());
  }
}