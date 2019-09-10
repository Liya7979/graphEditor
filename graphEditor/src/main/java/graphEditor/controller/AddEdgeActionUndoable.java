package graphEditor.controller;

import graphEditor.model.GraphModel;

import javax.swing.undo.AbstractUndoableEdit;

public class AddEdgeActionUndoable extends AbstractUndoableEdit {
    private GraphModel graphModel;

    AddEdgeActionUndoable(GraphModel graphModel) {
        this.graphModel = graphModel;
        graphModel.addEdge(graphModel.getSelectedEdge());
    }

    @Override
    public void undo() {
        graphModel.removeEdge(graphModel.getSelectedEdge());
        graphModel.update();
    }

    @Override
    public void redo() {
        graphModel.addEdge(graphModel.getSelectedEdge());
        graphModel.update();
    }

    public boolean canUndo() {
        return true;
    }

    public boolean canRedo() {
        return true;
    }

}
