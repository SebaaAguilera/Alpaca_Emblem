package controller.handlers;

import controller.GameController;
import model.Tactician;
import model.units.IUnit;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DeadUnitHandler implements PropertyChangeListener {

    private final GameController controller;

    public DeadUnitHandler(GameController controller){
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        Tactician t = (Tactician) evt.getSource();
        t.getUnits().get((int) evt.getOldValue()).getLocation().setUnit(null);
        t.removeUnit(t.getUnits().get((int) evt.getOldValue()));

        if (t.getUnits().size()==0) {
            controller.removeTactician(t.getName());
        }
    }
}
