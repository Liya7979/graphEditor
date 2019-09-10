package graphEditor.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ExitAction extends AbstractAction {
    ExitAction() {
        super("Exit");
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.exit(0);
    }
}
