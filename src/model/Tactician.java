package model;

import model.items.IEquipableItem;
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

    public Tactician(String name){
        this.name=name;
    }

    public String getName(){ return name; }

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
