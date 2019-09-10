package graphEditor.controller;

import graphEditor.model.GraphModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

class RedoMenuItem extends JMenuItem {
    RedoMenuItem(GraphModel graphModel) {
        super(new RedoAction(graphModel));
        setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
    }
}
