package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Tactician;
import model.items.IEquipableItem;
import model.map.Field;
import model.units.IUnit;

/**
 * Controller of the game.
 * The controller manages all the input received from de game's GUI.
 *
 * @author Ignacio Slater Muñoz
 * @version 2.0
 * @since 2.0
 */
public class GameController {

  private List<Tactician> tacticians = new ArrayList<>();
  private List<Tactician> roundSequence = new ArrayList();
  private Field map;
  private long seed = new Random().nextLong();
  private Random random = new Random(seed);
  private Tactician turnOwner;
  private int roundNumber;
  private int maxRounds;
  private int maxUnits;

  /**
   * Creates the controller for a new game.
   *
   * @param numberOfPlayers
   *     the number of players for this game
   * @param mapSize
   *     the dimensions of the map, for simplicity, all maps are squares
   */
  public GameController(int numberOfPlayers, int mapSize) {
    maxUnits= (int) mapSize^2/numberOfPlayers;

    map = new Field(mapSize,seed);
    for (int i=0; i<numberOfPlayers;i++){
      tacticians.add(new Tactician("Player " + i));
      tacticians.get(i).setController(this);
    }
  }

  /**
   * @return the list of all the tacticians participating in the game.
   */
  public List<Tactician> getTacticians() { return tacticians; }

  /**
   * @return the list of the tacticians in the round sequence
   */
  public List<Tactician> getRoundSequence() { return roundSequence; }

  /**
   * Sets a new round tactician sequence and round number
   */
  public void newRoundSequence(){
    roundSequence.clear();
    roundNumber+=1;

    int index = random.nextInt(tacticians.size());
    while(turnOwner!=null && index==tacticians.indexOf(turnOwner)){
      index = random.nextInt(tacticians.size());
    }
    roundSequence.add(tacticians.get(index));

    for (int i = 1; i < tacticians.size(); i++){
      index = random.nextInt(tacticians.size());
      while(roundSequence.contains(tacticians.get(index))){
        index = random.nextInt(tacticians.size());
      }
      roundSequence.add(tacticians.get(index));
    }
  }

  /**
   * @return the map of the current game
   */
  public Field getGameMap() { return map; }

  /**
   * @return the Game seed
   */
  public long getSeed(){ return seed;}

  /**
   * @param tactician will be the turn owner
   */
  public void setTurnOwner(Tactician tactician){ turnOwner = tactician; }
  /**
   * @return the tactician that's currently playing
   */
  public Tactician getTurnOwner() { return turnOwner; }

  /**
   * @return the number of rounds since the start of the game.
   */
  public int getRoundNumber() { return roundNumber; }

  /**
   * @return the maximum number of rounds a match can last
   */
  public int getMaxRounds() { return maxRounds; }

  /**
   * Finish the current player's turn.
   */
  public void endTurn() {
    turnOwner.clearMovedUnits();
    if (roundSequence.indexOf(turnOwner)<tacticians.size()-1){
      turnOwner = roundSequence.get(roundSequence.indexOf(turnOwner)+1);
    } else {
      newRoundSequence();
      turnOwner = roundSequence.get(0);
    }
  }


  /**
   * Removes a tactician and all of it's units from the game.
   *
   * @param tactician
   *     the player to be removed
   */
  public void removeTactician(String tactician) {
    for (int i=0; i<tacticians.size();i++){
      if (tactician.equals(turnOwner.getName())){
        endTurn();
      }
      if(tactician.equals(tacticians.get(i).getName())){
        tacticians.remove(i);
      }
    }
  }

  /**
   * Starts the game.
   * @param maxRounds
   *  the maximum number of turns the game can last
   */
  public void initGame(final int maxRounds) {
    this.maxRounds = maxRounds;
    roundNumber = 0;
    newRoundSequence();
    turnOwner = roundSequence.get(0);
  }


  /**
   * Starts a game without a limit of turns.
   */
  public void initEndlessGame() {
    this.maxRounds = -1;
    roundNumber = 0;
    newRoundSequence();
    turnOwner = roundSequence.get(0);
  }

  /**
   * @return the winner of this game, if the match ends in a draw returns a list of all the winners
   */
  public List<String> getWinners() {
    return null;
  }

  /**
   * @return the current player's selected unit
   */
  public IUnit getSelectedUnit() { return turnOwner.getSelectedUnit(); }

  /**
   * Selects a unit in the game map
   *
   * @param x
   *     horizontal position of the unit
   * @param y
   *     vertical position of the unit
   */
  public void selectUnitIn(int x, int y) { turnOwner.selectUnitIn(map.getCell(x,y).getUnit()); }

  /**
   * @return the max amount of units per tactician
   */
  public int getMaxUnits() { return maxUnits; }

  /**
   * @param unit, the turn owner will add this unit
   */
  public void addUnit(IUnit unit) {
    if (turnOwner.getUnits().size()<maxUnits){
      turnOwner.addUnit(unit);
    }
  }

  /**
   * Moves the turn owner selected unit
   * @param x the x axis
   * @param y the y axis
   */
  public void moveUnitTo(int x, int y){ turnOwner.moveUnitTo(map.getCell(x,y)); }

  /**
   * @return the inventory of the currently selected unit.
   */
  public List<IEquipableItem> getItems() { return turnOwner.getItems(); }

  /**
   *
   * @param item the item to be saved  in the tactician selected unit
   */
  public void saveItem(IEquipableItem item) { turnOwner.saveItem(item); }

  /**
   * Equips an item from the inventory to the currently selected unit.
   *
   * @param index
   *     the location of the item in the inventory.
   */
  public void equipItem(int index) { turnOwner.equipItem(index); }

  /**
   * Uses the equipped item on a target
   *
   * @param x
   *     horizontal position of the target
   * @param y
   *     vertical position of the target
   */
  public void useItemOn(int x, int y) { turnOwner.useItemOn(map.getCell(x,y).getUnit()); }

  /**
   * Selects an item from the selected unit's inventory.
   *
   * @param index
   *     the location of the item in the inventory.
   */
  public void selectItem(int index) { turnOwner.selectItem(index); }

  /**
   * Gives the selected item to a target unit.
   * @param x
   *     horizontal position of the target
   * @param y
   *     vertical position of the target
   */
  public void giveItemTo(int x, int y) { turnOwner.giveItemTo(map.getCell(x,y).getUnit()); }

  /**
   * @return the turnOwner selected unit HP
   */
  public double getSelectedUnitHP(){ return turnOwner.getSelectedUnitHP(); }

  /**
   * @return the turnOwner selected unit max HP
   */
  public double getSelectedUnitMaxHP(){ return turnOwner.getSelectedUnitMaxHP(); }

  /**
   * @return the turnOwner selected item power
   */
  public double getSelectedUnitItemPower(){ return turnOwner.getSelectedUnitItemPower(); }


}


