package graphEditor.view;

import graphEditor.controller.ButtonBar;
import graphEditor.controller.SelectionController;
import graphEditor.model.GraphModel;

import javax.swing.*;
import java.awt.*;

public class GraphFrame extends JFrame {

    private GraphModel model;

    public GraphFrame(GraphModel model) {
        this.model = model;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GraphPanel panel = new GraphPanel(model, new SelectionController(model));
        model.setPanel(panel);
        add(panel);

        setJMenuBar(new ButtonBar(this));
        setPreferredSize(new Dimension(800, 600));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public GraphModel getModel() {
        return model;
    }

    public void setModel(GraphModel model) {
        this.model = model;
    }
}
