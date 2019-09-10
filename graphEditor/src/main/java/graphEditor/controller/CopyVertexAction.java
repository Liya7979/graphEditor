package graphEditor.controller;

import graphEditor.model.GraphModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

public class CopyVertexAction extends AbstractAction implements Observer {
    private GraphModel graphModel;

    CopyVertexAction(GraphModel graphModel) {
        super("Copy vertex");
        this.graphModel = graphModel;
        graphModel.addObserver(this);
        fixEnabled();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        graphModel.setCopiedVertex(graphModel.getSelectedVertex());
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
