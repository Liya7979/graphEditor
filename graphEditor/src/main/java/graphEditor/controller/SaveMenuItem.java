package graphEditor.controller;

import graphEditor.model.GraphModel;

import javax.swing.*;

class SaveMenuItem extends JMenuItem {
    SaveMenuItem(GraphModel graphModel) {
        super(new SaveAction(graphModel));
    }
}
