package graphEditor.controller;

import graphEditor.model.GraphModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

class UndoMenuItem extends JMenuItem {
    UndoMenuItem(GraphModel graphModel) {
        super(new UndoAction(graphModel));
        setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
    }
}
