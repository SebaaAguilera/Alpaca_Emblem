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
  private List<Tactician> roundSequence = new ArrayList<>();

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
    for (int i=0; i<numberOfPlayers;i++){
      tacticians.add(new Tactician("Player " + i));
      tacticians.get(i).subscribeToHandlers(this);
    }

    map = new Field(mapSize,seed);

    maxUnits = mapSize^2/numberOfPlayers;

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
  private void newRoundSequence(){
    roundSequence.clear();
    int index;
    if (turnOwner!=null){
      do {
        index = random.nextInt(tacticians.size());
      } while(tacticians.get(index).getName().equals(turnOwner.getName()));
      roundSequence.add(tacticians.get(index));
    }
    while (roundSequence.size()!=tacticians.size()){
      do {
        index = random.nextInt(tacticians.size());
      } while(roundSequence.contains(tacticians.get(index)));
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
    int actualTurn = roundSequence.indexOf(turnOwner);
    if (tacticians.size()==1) {
      //End of the game
      turnOwner = tacticians.get(0);
    } else if (roundNumber==maxRounds && actualTurn==tacticians.size()-1){
      //End of the game
    } else if (actualTurn<tacticians.size()-1){
      turnOwner = roundSequence.get(roundSequence.indexOf(turnOwner)+1);
    } else {
      newRoundSequence();
      roundNumber+=1;
      turnOwner = roundSequence.get(0);
    }
  }

    /**
     * Removes an specific unit from the game
     * @param tactician the name of the owner of the unit
     * @param index the index of the unit in the list
     */
  public void removeUnit(Tactician tactician, int index){
      IUnit unit = tactician.getUnits().get(index);
      unit.getLocation().setUnit(null);
      tactician.removeUnit(unit);

      if (tactician.getUnits().size()==0) {
          removeTactician(tactician.getName());
      }
  }

  /**
   * Removes all the tactician units from the map and tactician's list
   * @param tactician with the units to be removed
   */
  private void removeUnits(Tactician tactician){
    for (IUnit unit : tactician.getUnits()){
      unit.getLocation().setUnit(null);
    }
    tactician.removeUnits();
  }

  /**
   * Removes a tactician and it units from the game.
   *
   * @param tactician
   *     the player to be removed
   */
  public void removeTactician(String tactician) {
    for (int i=0; i<tacticians.size();i++) {
      if (tactician.equals(tacticians.get(i).getName())) {
        removeUnits(tacticians.get(i));
        tacticians.remove(i);
        break;
      }
    }
    for (int i=0; i<roundSequence.size();i++){
      if(tactician.equals(roundSequence.get(i).getName())){
        roundSequence.remove(i);
        break;
      }
    }
    if (turnOwner.getName().equals(tactician)){
      endTurn();
    }
  }

  /**
   * Starts the game.
   * @param maxRounds
   *  the maximum number of turns the game can last
   */
  public void initGame(int maxRounds) {
    this.maxRounds = maxRounds;
    roundNumber = 1;
    newRoundSequence();
    turnOwner = roundSequence.get(0);
  }


  /**
   * Starts a game without a limit of turns.
   */
  public void initEndlessGame() {
    initGame(-1);
  }

  /**
   * @return the winner of this game, if the match ends in a draw returns a list of all the winners
   */
  public List<String> getWinners() {
    List<String> winners = new ArrayList<>();
    for (Tactician tactician : tacticians) {
      winners.add(tactician.getName());
    }
    if ((roundNumber==maxRounds && roundSequence.indexOf(turnOwner)==tacticians.size()-1)
        || (tacticians.size()==1)) {
      return winners;
    } else {
      return new ArrayList<>();
    }
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
   * @param unit, the turn owner will add this unit if it can
   */
  public void addUnit(IUnit unit) {
    if (turnOwner.getUnits().size()<maxUnits && unit.getCurrentHitPoints()>0){
      turnOwner.addUnit(unit);
    }
  }

  /**
   *
   *  Moves the turn owner selected unit
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
   * @return the turnOwner selectedItem
   */
  public IEquipableItem getSelectedItem() { return turnOwner.getSelectedItem(); }

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


