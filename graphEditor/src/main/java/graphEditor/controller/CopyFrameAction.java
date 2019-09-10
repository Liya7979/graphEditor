package graphEditor.controller;

import graphEditor.view.GraphFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CopyFrameAction extends AbstractAction {
    private GraphFrame graphFrame;

    CopyFrameAction(GraphFrame graphFrame) {
        super("Copy frame");
        this.graphFrame = graphFrame;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        new GraphFrame(graphFrame.getModel());
    }
}
