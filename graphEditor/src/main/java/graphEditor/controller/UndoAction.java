package graphEditor.controller;

import graphEditor.model.GraphModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

public class UndoAction extends AbstractAction implements Observer {
    private final GraphModel graphModel;

    UndoAction(GraphModel model) {
        super("Undo");
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
        graphModel.getManager().undo();

    }

    private void fixEnabled() {
        if (graphModel.getManager().canUndo()) {
            setEnabled(true);
        } else {
            setEnabled(false);
        }
    }
}
