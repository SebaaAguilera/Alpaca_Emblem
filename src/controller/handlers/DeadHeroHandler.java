package controller.handlers;

import controller.GameController;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DeadHeroHandler implements PropertyChangeListener {

    private final GameController controller;

    public DeadHeroHandler(GameController controller){
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        controller.removeTactician(evt.getPropertyName());
    }
}
