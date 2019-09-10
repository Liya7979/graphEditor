package graphEditor.controller;

import graphEditor.model.GraphModel;

import javax.swing.*;

class RemoveEdgeButton extends JButton {
    RemoveEdgeButton(GraphModel graphModel) {
        super(new RemoveEdgeAction(graphModel));
    }
}
