package model;

import controller.GameController;
import model.items.IEquipableItem;
import model.map.Field;
import model.units.Archer;
import model.units.IUnit;

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

    private String name;
    private List<IUnit> units =  new ArrayList<>();
    private IUnit selectedUnit;
    private IEquipableItem selectedItem;
    private int maxUnits = 4;
    private GameController controller;

    /**
     * Creates a Tactician, a Game Player
     * @param name the name of the Tactician
     */
    public Tactician(String name){ this.name=name; }

    /**
     * set the Tactician controller
     * @param controller
     */
    public void setController(GameController controller) { this.controller = controller; }

    /**
     *  @return the Tactician Name
     */
    public String getName(){ return name; }

    /**
     * @param unit a unit the tactician is going to play with
     */
    public void addUnit(IUnit unit) { if (units.size()<=maxUnits) {units.add(unit); }}

    /**
     * @return the tactician units
     */
    public List<IUnit> getUnits(){ return units; }

    /**
     * Select n unit in the map
     * @param x the x axis
     * @param y the y axis
     */
    public void selectUnitIn(int x, int y){ selectedUnit = controller.getGameMap().getCell(x,y).getUnit(); }

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

    public List<IEquipableItem> getItems() {
        if(units.contains(selectedUnit)){
            return selectedUnit.getItems();
        } else return null;
    }


    public void saveItem(IEquipableItem item) { selectedUnit.saveItem(item);}

    /**
     *
     * @param index of the item the unit will equip
     */
    public void equipItem(int index) {
        if (units.contains(selectedUnit)) {
            selectedUnit.equipItem(selectedUnit.getItems().get(index));
        }
    }

    /**
     * @param index select a unit item
     */
    public void selectItem(int index) {
        if (units.contains(selectedUnit)){
            selectedItem = selectedUnit.getItems().get(index);
        }
    }

    /**
     * Give the selected item to the unit in the coordinate
     * @param x the x axis
     * @param y the y axis
     */
    public void getItemTo(int x, int y) {
        if (units.contains(controller.getGameMap().getCell(x,y).getUnit())){
            selectedUnit.giveItem(controller.getGameMap().getCell(x,y).getUnit(),selectedItem);
        }
    }
}
