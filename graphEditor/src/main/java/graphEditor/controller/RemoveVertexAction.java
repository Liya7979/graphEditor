package graphEditor.controller;

import graphEditor.model.GraphModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

public class RemoveVertexAction extends AbstractAction implements Observer {
    private GraphModel graphModel;

    RemoveVertexAction(GraphModel graphModel) {
        super("Remove a vertex");
        this.graphModel = graphModel;
        graphModel.addObserver(this);
        fixEnabled();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        graphModel.getManager().addEdit(new RemoveVertexActionUndoable(graphModel));
        graphModel.update();
    }

    private void fixEnabled() {
        if (graphModel.getSelectedVertex() != null) {
            setEnabled(true);
        } else {
            setEnabled(false);
        }
    }

    @Override
    public void update(Observable observable, Object o) {
        fixEnabled();
    }
}
