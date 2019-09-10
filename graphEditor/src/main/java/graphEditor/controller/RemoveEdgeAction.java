package graphEditor.controller;

import graphEditor.model.GraphEdge;
import graphEditor.model.GraphModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class RemoveEdgeAction extends AbstractAction implements Observer {
    private GraphModel graphModel;

    RemoveEdgeAction(GraphModel graphModel) {
        super("Remove an edge");
        this.graphModel = graphModel;
        graphModel.addObserver(this);
        fixEnabled();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (graphModel.getSelectedVertex() != null) {
            List<GraphEdge> edges = graphModel.getEdges();
            if (edges.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No edges to remove",
                        "Message", JOptionPane.ERROR_MESSAGE);
                return;
            }
            JComboBox<String> selector = new JComboBox<>(setupEdgesNames(edges));
            JOptionPane.showMessageDialog(null,
                    selector, "Edges to remove", JOptionPane.QUESTION_MESSAGE);

            int choice = selector.getSelectedIndex();
            graphModel.setSelectedEdge(edges.get(choice));
            graphModel.getManager().addEdit(new RemoveEdgeActionUndoable(graphModel));
            graphModel.update();
        }
    }

    private String[] setupEdgesNames(List<GraphEdge> edges) {
        String[] names = new String[edges.size()];
        int i = 0;
        for (GraphEdge e : edges) {
            names[i] = e.toString();
            i++;
        }
        return names;
    }

    private void fixEnabled() {
        if (graphModel.getSelectedVertex() != null) {
            setEnabled(true);
        } else {
            setEnabled(false);
        }
    }

    @Override
    public void update(Observable observable, Object o) {
        fixEnabled();
    }
}
