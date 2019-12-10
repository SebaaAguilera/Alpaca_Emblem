package controller.handlers;

import controller.GameController;
import model.Tactician;
import model.units.IUnit;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Dead Unit handler
 * This class will be used when the tactician declares a dead unit, then the controller will delete it
 * @author Sebastián Aguilera Valenzueña
 * @since 2.5
 */
public class DeadUnitHandler implements PropertyChangeListener {

    private final GameController controller;

    /**
     * @param controller that will receive the message
     */
    public DeadUnitHandler(GameController controller){
        this.controller = controller;
    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt
     *     A PropertyChangeEvent object describing the event source
     *     and the property that has changed.
     */
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
