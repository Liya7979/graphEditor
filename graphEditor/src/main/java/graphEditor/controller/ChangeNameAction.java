package graphEditor.controller;

import graphEditor.model.GraphModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

public class ChangeNameAction extends AbstractAction implements Observer {
    private GraphModel graphModel;

    ChangeNameAction(GraphModel graphModel) {
        super("Change vertex name");
        this.graphModel = graphModel;
        graphModel.addObserver(this);
        fixEnabled();
    }

    @Override
    public void update(Observable observable, Object o) {
        fixEnabled();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        graphModel.getManager().addEdit(new ChangeNameActionUndoable(graphModel));
        graphModel.update();
    }

    private void fixEnabled() {
        if (graphModel.getSelectedVertex() != null) {
            setEnabled(true);
        } else {
            setEnabled(false);
        }
    }
}
