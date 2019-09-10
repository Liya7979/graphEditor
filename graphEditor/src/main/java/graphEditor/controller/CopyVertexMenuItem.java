package graphEditor.controller;

import graphEditor.model.GraphModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

class CopyVertexMenuItem extends JMenuItem {
    CopyVertexMenuItem(GraphModel graphModel) {
        super(new CopyVertexAction(graphModel));
        setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
    }
}
