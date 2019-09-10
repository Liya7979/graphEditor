package graphEditor.controller;

import graphEditor.model.GraphModel;
import graphEditor.model.GraphVertex;

import javax.swing.undo.AbstractUndoableEdit;

public class PasteVertexActionUndoable extends AbstractUndoableEdit {
    private GraphModel graphModel;
    private GraphVertex pastedVertex;

    PasteVertexActionUndoable(GraphModel graphModel) {
        this.graphModel = graphModel;
        pastedVertex = new GraphVertex(graphModel.getCopiedVertex());
        graphModel.addVertex(pastedVertex);
    }

    @Override
    public void undo() {
        graphModel.removeVertex(pastedVertex);
        graphModel.update();
    }

    @Override
    public void redo() {
        graphModel.addVertex(pastedVertex);
        graphModel.update();
    }

    public boolean canUndo() {
        return true;
    }

    public boolean canRedo() {
        return true;
    }
}
