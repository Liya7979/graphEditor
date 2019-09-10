package graphEditor.controller;

import graphEditor.model.GraphModel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AddVertexAction extends AbstractAction {
    private GraphModel graphModel;

    AddVertexAction(GraphModel graphModel) {
        super("Add a vertex");
        this.graphModel = graphModel;

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        graphModel.getManager().addEdit(new AddVertexActionUndoable(graphModel));
        graphModel.update();
    }
}