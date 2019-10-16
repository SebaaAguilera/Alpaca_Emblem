package model;

import controller.GameController;
import model.items.IEquipableItem;
import model.map.Field;
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
    private GameController controller;

    public Tactician(String name){
        this.name=name;
    }

    public void setController(GameController controller) { this.controller = controller; }

    public String getName(){ return name; }

    protected List<IUnit> getUnits(){ return units; }

    public Field getGameMap(){ return controller.getGameMap(); }

    public IUnit getSelectedUnit(){ return selectedUnit; }

    private double getSelectedUnitHP(){
        return 0;
    }

    private double getSelectedUnitMaxHP(){
        return 0;
    }

    private String getSelectedUnitName(){
        return "uwu";
    }

    private List<IEquipableItem> getSelectedUnitItems(){
        return null;
    }

    private double getSelectedUnitItemPower(){
        return 0;
    }





}
