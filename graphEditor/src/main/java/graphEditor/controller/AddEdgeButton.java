package graphEditor.controller;

import graphEditor.model.GraphModel;

import javax.swing.*;

class AddEdgeButton extends JButton {
    AddEdgeButton(GraphModel graphModel) {
        super(new AddEdgeAction(graphModel));
    }
}
