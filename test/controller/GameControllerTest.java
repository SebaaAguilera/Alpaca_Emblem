package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import model.Tactician;
import model.unitFactory.*;
import model.itemFactory.*;
import model.items.IEquipableItem;
import model.map.Field;
import model.map.Location;
import model.units.IUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Ignacio Slater Muñoz
 * @since v2.0
 */
class GameControllerTest {

  private GameController controller;
  private long randomSeed;
  private List<String> testTacticians;

  private AlpacaFactory alpaca;
  private ArcherFactory archer;
  private ClericFactory cleric;
  private FighterFactory fighter;
  private HeroFactory hero;
  private SwordMasterFactory swordMaster;
  private AnimaSorcererFactory sorcerer;



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
    // Se define la semilla como un número aleatorio para generar variedad en los tests
    randomSeed = new Random().nextLong();
    controller = new GameController(4, 7);
    testTacticians = List.of("Player 0", "Player 1", "Player 2","Player 3");

    alpaca = new AlpacaFactory();
    archer = new ArcherFactory();
    cleric = new ClericFactory();
    fighter = new FighterFactory();
    hero   = new HeroFactory();
    swordMaster = new SwordMasterFactory() ;
    sorcerer = new AnimaSorcererFactory();

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
    assertEquals(7, gameMap.getSize());
    assertTrue(controller.getGameMap().isConnected());

    Random seed = controller.getSeed();

    Field testMap = new Field();
    testMap.setSeed(seed);
    testMap.addCells(false, testMap.arrayCells(7));
    assertTrue(testMap.isConnected());

    for (int i = 0; i < 7; i++){
      for (int j = 0; j < 7; j++){
        Location gm = gameMap.getCell(i,j);
        Location tm = testMap.getCell(i,j);
        assertEquals(gm.getNeighbours().size(),tm.getNeighbours().size());
        for (int k = 0; k < tm.getNeighbours().size(); k++){
          assertTrue(gm.getNeighbours().equals(tm.getNeighbours()));
        }
      }
    }
  }

  @Test
  void getTurnOwner() {
    //  En este caso deben hacer lo mismo que para el mapa
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
      System.out.println(nextInt);
      assertEquals(nextInt, controller.getMaxRounds());
      System.out.println(nextInt);
    });
    controller.initEndlessGame();
    assertEquals(-1, controller.getMaxRounds());
  }

  @Test
  void endTurn() {
    Tactician firstPlayer = controller.getTurnOwner();
    // Nuevamente, para determinar el orden de los jugadores se debe usar una semilla
    Tactician secondPlayer = new Tactician("Player 2"); // <- Deben cambiar esto (!)
    assertNotEquals(secondPlayer.getName(), firstPlayer.getName());

    controller.endTurn();
    assertNotEquals(firstPlayer.getName(), controller.getTurnOwner().getName());
    assertEquals(secondPlayer.getName(), controller.getTurnOwner().getName());
  }

  @Test
  void removeTactician() {
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
    assertNull(controller.getWinners());
    controller.removeTactician("Player 0");
    controller.removeTactician("Player 2");
    IntStream.range(0, 2).forEach(i -> controller.endTurn());
    List<String> winners = controller.getWinners();
    assertEquals(2, winners.size());
    assertTrue(List.of("Player 1", "Player 3").containsAll(winners));

    controller.initEndlessGame();
    for (int i = 0; i < 3; i++) {
      assertNull(controller.getWinners());
      controller.removeTactician("Player " + i);
    }
    assertTrue(List.of("Player 3").containsAll(controller.getWinners()));
  }

  // Desde aquí en adelante, los tests deben definirlos completamente ustedes
  @Test
  void getSelectedUnit() {
    IUnit alp = alpaca.create(controller.getGameMap().getCell(0,0));
    controller.setTurnOwner(controller.getTacticians().get(0));
    controller.addUnit(alp); // aplicar observer para que esto funcione
    controller.selectUnitIn(0,0);
    assertEquals(alp,controller.getSelectedUnit());
  }

  @Test // esta deberia ser diferente
  void selectUnitIn() {
    IUnit alp = alpaca.create(controller.getGameMap().getCell(0,0));
    controller.setTurnOwner(controller.getTacticians().get(0));
    controller.addUnit(alp); // aplicar observer para que esto funcione
    controller.selectUnitIn(0,0);
    assertEquals(alp,controller.getSelectedUnit());
  }

  @Test
  void getItems() {
    IUnit arc = archer.create(controller.getGameMap().getCell(0,0));
    controller.setTurnOwner(controller.getTacticians().get(0));
    controller.addUnit(arc); // aplicar observer para que esto funcione
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
    double fighterHP = controller.getSelectedUnit().getCurrentHitPoints();
    controller.useItemOn(1,0);
    assertEquals(fighterHP-(he.getEquippedItem().getPower()-20), fi.getCurrentHitPoints());
    assertEquals(heroHP-(he.getEquippedItem().getPower()*1.5),he.getCurrentHitPoints());

    controller.setTurnOwner(controller.getTacticians().get(0));

    // Hero try to combat Cleric
    heroHP = he.getCurrentHitPoints();
    double clericHP = controller.getSelectedUnit().getCurrentHitPoints();
    controller.useItemOn(0,0);
    assertEquals(heroHP, he.getCurrentHitPoints());
    assertEquals(clericHP,controller.getSelectedUnit().getCurrentHitPoints());

    // Cleric heal Hero
    controller.selectUnitIn(2,0);
    heroHP = he.getCurrentHitPoints();
    clericHP = controller.getSelectedUnit().getCurrentHitPoints();
    controller.useItemOn(1,0);
    assertEquals(heroHP+cl.getEquippedItem().getPower(), he.getCurrentHitPoints());
    assertEquals(clericHP,controller.getSelectedUnit().getCurrentHitPoints());

    // Cleric try to heal Fighter
    fighterHP = fi.getCurrentHitPoints();
    clericHP = controller.getSelectedUnit().getCurrentHitPoints();
    controller.useItemOn(0,0);
    assertEquals(fighterHP, fi.getCurrentHitPoints());
    assertEquals(clericHP,controller.getSelectedUnit().getCurrentHitPoints());
  }

  @Test
  void selectItem() {
  }

  @Test
  void giveItemTo() {
  }
}