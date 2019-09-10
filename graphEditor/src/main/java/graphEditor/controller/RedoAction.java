package graphEditor.controller;

import graphEditor.model.GraphModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

public class RedoAction extends AbstractAction implements Observer {
    private final GraphModel graphModel;

    RedoAction(GraphModel model) {
        super("Redo");
        this.graphModel = model;
        model.addObserver(this);
        fixEnabled();
    }

    @Override
    public void update(Observable observable, Object o) {
        fixEnabled();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        graphModel.getManager().redo();

    }

    private void fixEnabled() {
        if (graphModel.getManager().canRedo()) {
            setEnabled(true);
        } else {
            setEnabled(false);
        }
    }
}
