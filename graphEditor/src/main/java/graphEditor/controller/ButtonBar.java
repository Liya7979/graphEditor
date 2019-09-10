package graphEditor.controller;

import graphEditor.view.GraphFrame;

import javax.swing.*;

public class ButtonBar extends JMenuBar {
    public ButtonBar(GraphFrame graphFrame) {
        setupFileMenu(graphFrame);
        setupEditMenu(graphFrame);
        add(new AddVertexButton(graphFrame.getModel()));
        add(new RemoveVertexButton(graphFrame.getModel()));
        add(new ChangeNameButton(graphFrame.getModel()));
        add(new AddEdgeButton(graphFrame.getModel()));
        add(new RemoveEdgeButton((graphFrame.getModel())));

    }
    // creates a menu for file options (save, open, exit)
    private void setupFileMenu(GraphFrame graphFrame) {
        JMenu file = new JMenu("File");
        file.add(new OpenMenuItem(graphFrame.getModel()));
        file.add(new SaveMenuItem(graphFrame.getModel()));
        file.add(new CopyFrameMenuItem(graphFrame));
        file.add(new ExitMenuItem());
        file.addSeparator();
        add(file);
    }
    // creates a menu for edit options (undo, redo, copy, paste)
    private void setupEditMenu(GraphFrame graphFrame) {
        JMenu edit = new JMenu("Edit");
        edit.add(new UndoMenuItem(graphFrame.getModel()));
        edit.add(new RedoMenuItem(graphFrame.getModel()));
        edit.add(new CopyVertexMenuItem(graphFrame.getModel()));
        edit.add(new PasteVertexMenuItem(graphFrame.getModel()));
        add(edit);
    }
}
