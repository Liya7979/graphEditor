package graphEditor.controller;

import graphEditor.model.GraphModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Objects;

public class OpenAction extends AbstractAction {
    private GraphModel graphModel;

    OpenAction(GraphModel graphModel) {
        super("Open");
        this.graphModel = graphModel;

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int success = chooser.showOpenDialog(null);
        if (success == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            graphModel.loadModel(Objects.requireNonNull(GraphModel.load(file)));
        }
    }
}
