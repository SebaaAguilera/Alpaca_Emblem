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
    private int maxUnits = 4;
    private GameController controller;

    public Tactician(String name){ this.name=name; }

    public void setController(GameController controller) { this.controller = controller; }

    public String getName(){ return name; }

    public void addUnit(IUnit unit) { units.add(unit); }

    public List<IUnit> getUnits(){ return units; }

    public void selectUnitIn(int x, int y){ selectedUnit = controller.getGameMap().getCell(x,y).getUnit(); }

    public IUnit getSelectedUnit() { return selectedUnit; }

    private double getSelectedUnitHP(){
        return 0;
    }

    private double getSelectedUnitMaxHP(){
        return 0;
    }

    private String getSelectedUnitName(){
        return "uwu";
    }

    private double getSelectedUnitItemPower(){
        return 0;
    }


    public List<IEquipableItem> getItems() {
        if(units.contains(selectedUnit)){
            return selectedUnit.getItems();
        } else return null;
    }

    /**
     *
     * @param index of the item the unit will equip
     */
    public void equipItem(int index) {
        if (units.contains(selectedUnit)) {
            selectedUnit.equipItem(selectedUnit.getItems().get(index));
        }
    }
}
