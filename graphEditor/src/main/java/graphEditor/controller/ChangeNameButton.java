package graphEditor.controller;

import graphEditor.model.GraphModel;

import javax.swing.*;

class ChangeNameButton extends JButton {
    ChangeNameButton(GraphModel g) {
        super(new ChangeNameAction(g));
    }
}
