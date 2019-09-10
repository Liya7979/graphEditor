package graphEditor.controller;

import graphEditor.model.GraphModel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SaveAction extends AbstractAction {
    private GraphModel graphModel;

    SaveAction(GraphModel graphModel) {
        super("Save");
        this.graphModel = graphModel;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JTextField name = new JTextField();
        final JComponent[] inputs = new JComponent[]{new JLabel("Choose new name for vertex"), name};
        int result = JOptionPane.showConfirmDialog(null, inputs, "New name",
                JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            graphModel.save(graphModel, name.getText());
        } else {
            System.out.println("Cancelled");
        }
    }
}
