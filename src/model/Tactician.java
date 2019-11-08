package model;

import controller.GameController;
import model.handlers.GameOverHandler;
import model.items.IEquipableItem;
import model.map.Location;
import model.units.IUnit;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

/**
 * Tactician of the Game
 * It have units
 *
 * @author Sebastián Aguilera Valenzuela
 * @since 2.5
 */
public class Tactician {

    private final String name;
    private List<IUnit> units =  new ArrayList<>();
    private List<IUnit> movedUnits =  new ArrayList<>();
    private IUnit selectedUnit;
    private IEquipableItem selectedItem;
    private PropertyChangeSupport support;

    /**
     * Creates a Tactician, a Game Player
     * @param name the name of the Tactician
     */
    public Tactician(String name){ this.name=name; }

    /**
     *  @return the Tactician Name
     */
    public String getName(){ return name; }

    /**
     * @param unit a unit the tactician is going to play with
     */
    public void addUnit(IUnit unit) {
        units.add(unit);
        unit.setTactician(this);
    }

    /**
     * @return the tactician units
     */
    public List<IUnit> getUnits(){ return units; }

    /**
     * Select an unit in the map
     * @param unit the unit to be selected
     */
    public void selectUnitIn(IUnit unit){
        if (units.contains(unit)){
            selectedUnit = unit;
        }
    }

    /**
     * @return the selected unit
     */
    public IUnit getSelectedUnit() { return selectedUnit; }

    /**
     * @return the selected unit current HP
     */
    public double getSelectedUnitHP(){ return selectedUnit.getCurrentHitPoints(); }

    /**
     * @return the selected unit max HP
     */
    public double getSelectedUnitMaxHP(){ return selectedUnit.getMaxHitPoints(); }

    /**
     * @return the tactician selected item power
     */
    public double getSelectedUnitItemPower(){ return selectedItem.getPower(); }

    /**
     * @return the selected unit items
     */
    public List<IEquipableItem> getItems() { return selectedUnit.getItems(); }

    /**
     * Moves the tactician selected unit
     * @param location the target location
     */
    public void moveUnitTo(Location location){
        if (movedUnits.contains(selectedUnit)) return;
        movedUnits.add(selectedUnit);
        selectedUnit.moveTo(location);
    }

    /**
     * clear the moved units when the turn has finished
     */
    public void clearMovedUnits() { movedUnits.clear(); }

    /**
     * @param item to be saved in the unit inventory
     */
    public void saveItem(IEquipableItem item) { selectedUnit.saveItem(item);}

    /**
     *
     * @param index of the item the unit will equip
     */
    public void equipItem(int index) { selectedUnit.equipItem(selectedUnit.getItems().get(index)); }


    /**
     * @param index select a unit item
     */
    public void selectItem(int index) { selectedItem = selectedUnit.getItems().get(index); }

    /**
     * @return the tactician selected item
     */
    public IEquipableItem getSelectedItem() { return selectedItem; }

    /**
     * Give the selected item to the unit
     * @param unit the unit will receive the item
     */
    public void giveItemTo(IUnit unit) {
        if (units.contains(unit)){
            selectedUnit.giveItem(unit,selectedItem);
        }
    }

    /**
     * the selected unit will use it equipped item on the unit
     * @param unit this unit
     */
    public void useItemOn(IUnit unit) { selectedUnit.useItemOn(unit); }

    /**
     * removes the tactician and its units from THE GAME
     */
    public void gameOver() {
        for (IUnit unit : getUnits()){
            unit.getLocation().setUnit(null);
            units.remove(unit);
        }
        support.firePropertyChange(new PropertyChangeEvent(this,getName(),null,null));
    }

    /**
     * @param unit to be removed from the units list if it die
     *             if no unit is left the tactician will lose
     */
    public void removeUnit(IUnit unit){
        units.remove(unit);
        if (units.size()==0) this.gameOver(); //esto debiese cambiarse
    }

    public void subscribeToGOHandler(GameController controller) {
        support = new PropertyChangeSupport(controller);
        support.addPropertyChangeListener(new GameOverHandler(controller));
    }
}
