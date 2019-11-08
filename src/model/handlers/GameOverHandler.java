package model.handlers;

import controller.GameController;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GameOverHandler implements PropertyChangeListener {

    private final GameController controller;

    public GameOverHandler (GameController controller){
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        controller.removeTactician(evt.getPropertyName());

        if (controller.getTurnOwner().getName().equals(evt.getPropertyName())){
            controller.endTurn();
        }
    }
}
