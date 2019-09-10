package graphEditor.controller;

import graphEditor.model.GraphModel;
import graphEditor.model.GraphVertex;

import javax.swing.undo.AbstractUndoableEdit;

public class AddVertexActionUndoable extends AbstractUndoableEdit {
    private GraphModel graphModel;
    private GraphVertex vertex;

    AddVertexActionUndoable(GraphModel graphModel) {
        this.graphModel = graphModel;
        graphModel.addDefaultVertex();
        vertex = graphModel.getLastVertex();
    }

    @Override
    public void undo() {
        if (graphModel.getLastVertex() == graphModel.getSelectedVertex()) {
            graphModel.setSelectedVertex(null);
        }
        graphModel.removeVertex(vertex);
        graphModel.update();
    }

    @Override
    public void redo() {
        graphModel.getVertices().add(vertex);
        graphModel.update();
    }

    public boolean canUndo() {
        return true;
    }

    public boolean canRedo() {
        return true;
    }
}
