package controller.handlers;

import controller.GameController;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Dead Unit handler
 * This class will be used when the tactician declares a dead hero, then the controller will delete it
 * @author Sebastián Aguilera Valenzueña
 * @since 2.5
 */
public class DeadHeroHandler implements PropertyChangeListener {

    private final GameController controller;

    /**
     * @param controller that will receive the message
     */
    public DeadHeroHandler(GameController controller){
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
        controller.removeTactician(evt.getPropertyName());
    }
}
