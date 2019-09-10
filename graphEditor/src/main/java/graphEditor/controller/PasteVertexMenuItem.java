package graphEditor.controller;

import graphEditor.model.GraphModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

class PasteVertexMenuItem extends JMenuItem {
    PasteVertexMenuItem(GraphModel graphModel) {
        super(new PasteVertexAction(graphModel));
        setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
    }
}
