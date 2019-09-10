package graphEditor.controller;

import graphEditor.model.GraphModel;
import graphEditor.model.GraphVertex;

import javax.swing.*;
import javax.swing.undo.AbstractUndoableEdit;

public class ChangeNameActionUndoable extends AbstractUndoableEdit {
    private GraphModel graphModel;
    private GraphVertex vertex;
    private String previousName;
    private String currentName;

    ChangeNameActionUndoable(GraphModel graphModel) {
        this.graphModel = graphModel;
        this.vertex = graphModel.getSelectedVertex();

        createName();
    }

    private void createName() {
        if (vertex != null) {
            JTextField name = new JTextField();
            final JComponent[] inputs = new JComponent[]{new JLabel("Choose new name for vertex"), name};
            int result = JOptionPane.showConfirmDialog(null,
                    inputs, "New name", JOptionPane.PLAIN_MESSAGE);
            // only change the name if confirmed by the user
            if (result == JOptionPane.OK_OPTION) {
                // do not add empty name
                if (!name.getText().isEmpty()) {
                    previousName = vertex.getName();
                    currentName = name.getText();
                    vertex.setName(name.getText());
                    graphModel.getPanel().resizeVertex(vertex);
                    graphModel.update();
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Vertices should have a name", "Error: Wrong name", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    @Override
    public void undo() {
        vertex.setName(previousName);
        graphModel.update();
    }

    @Override
    public void redo() {
        vertex.setName(currentName);
        graphModel.update();
    }

    public boolean canUndo() {
        return true;
    }

    public boolean canRedo() {
        return true;
    }
}
