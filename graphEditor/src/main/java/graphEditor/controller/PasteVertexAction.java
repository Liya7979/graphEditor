package graphEditor.controller;

import graphEditor.model.GraphModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

public class PasteVertexAction extends AbstractAction implements Observer {
    private GraphModel graphModel;

    PasteVertexAction(GraphModel graphModel) {
        super("Paste vertex");
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
        graphModel.getManager().addEdit(new PasteVertexActionUndoable(graphModel));
        graphModel.update();
    }

    private void fixEnabled() {
        if (graphModel.getCopiedVertex() != null) {
            setEnabled(true);
        } else {
            setEnabled(false);
        }
    }

}
