package graphEditor.controller;

import graphEditor.model.GraphModel;
import graphEditor.model.GraphVertex;

import javax.swing.undo.AbstractUndoableEdit;

public class RemoveVertexActionUndoable extends AbstractUndoableEdit {
    private GraphModel graphModel;
    private GraphVertex vertex;

    RemoveVertexActionUndoable(GraphModel graphModel) {
        this.graphModel = graphModel;
        vertex = graphModel.getSelectedVertex();
        removeVertex(graphModel);
    }

    private void removeVertex(GraphModel graphModel) {
        if (graphModel.getSelectedVertex() != null) {
            graphModel.removeVertex(graphModel.getSelectedVertex());
            graphModel.update();
            graphModel.setSelectedVertex(null);
        }
    }

    @Override
    public void undo() {
        if (vertex != null) {
            graphModel.addVertex(vertex);
            graphModel.setSelectedVertex(vertex);
        }
        graphModel.update();
    }

    @Override
    public void redo() {
        graphModel.removeVertex(vertex);
        graphModel.update();
    }

    public boolean canUndo() {
        return true;
    }

    public boolean canRedo() {
        return true;
    }
}
