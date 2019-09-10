package graphEditor.controller;

import graphEditor.model.GraphModel;

import javax.swing.*;

class RemoveVertexButton extends JButton {
    RemoveVertexButton(GraphModel g) {
        super(new RemoveVertexAction(g));
    }
}
