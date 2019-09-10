package graphEditor.controller;

import graphEditor.model.GraphModel;

import javax.swing.*;

class AddVertexButton extends JButton {
    AddVertexButton(GraphModel g) {
        super(new AddVertexAction(g));
    }
}
