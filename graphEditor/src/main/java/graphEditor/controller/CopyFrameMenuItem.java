package graphEditor.controller;

import graphEditor.view.GraphFrame;

import javax.swing.*;

class CopyFrameMenuItem extends JMenuItem {
    CopyFrameMenuItem(GraphFrame graphFrame) {
        super(new CopyFrameAction(graphFrame));
    }
}
