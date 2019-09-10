package graphEditor.controller;

import graphEditor.model.GraphModel;

import javax.swing.*;

class OpenMenuItem extends JMenuItem {
    OpenMenuItem(GraphModel graphModel) {
        super(new OpenAction(graphModel));
    }
}
