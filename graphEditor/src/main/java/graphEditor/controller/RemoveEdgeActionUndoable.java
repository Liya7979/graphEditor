package graphEditor.controller;

import graphEditor.model.GraphModel;

import javax.swing.undo.AbstractUndoableEdit;

public class RemoveEdgeActionUndoable extends AbstractUndoableEdit {
    private GraphModel graphModel;

    RemoveEdgeActionUndoable(GraphModel graphModel) {
        this.graphModel = graphModel;
        graphModel.removeEdge(graphModel.getSelectedEdge());
        graphModel.update();
    }

    @Override
    public void undo() {
        graphModel.addEdge(graphModel.getSelectedEdge());
        graphModel.update();
    }

    @Override
    public void redo() {
        graphModel.removeEdge(graphModel.getSelectedEdge());
        graphModel.update();
    }

    public boolean canUndo() {
        return true;
    }

    public boolean canRedo() {
        return true;
    }
}
