package graphEditor.controller;

import graphEditor.model.GraphEdge;
import graphEditor.model.GraphModel;
import graphEditor.model.GraphVertex;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class AddEdgeAction extends AbstractAction implements Observer {
    private GraphModel graphModel;

    AddEdgeAction(GraphModel graphModel) {
        super("Add an edge");
        this.graphModel = graphModel;
        graphModel.addObserver(this);
        fixEnabled();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // Adds an edge to the selected vertex if possible
        if (graphModel.getSelectedVertex() != null) {
            ArrayList<GraphEdge> edges = setupEdges();
            if (edges.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "No vertices to join", "Cannot add edge", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String[] edgesNames = setEdgesNames(edges);
            JComboBox<String> selector = new JComboBox<>(edgesNames);
            JOptionPane.showMessageDialog(null,
                    selector, "Edges to add", JOptionPane.QUESTION_MESSAGE);
            int choice = selector.getSelectedIndex();
            graphModel.setSelectedEdge(edges.get(choice));
            graphModel.getManager().addEdit(new AddEdgeActionUndoable(graphModel));
            graphModel.update();
        }
    }
    // creates a list of edges that don't exist at the moment
    private ArrayList<GraphEdge> setupEdges() {
        ArrayList<GraphEdge> edges = new ArrayList<>();
        GraphVertex selected = graphModel.getSelectedVertex();
        for (GraphVertex v : graphModel.getVertices()) {
            if (v != selected) {
                boolean verticesConnected = false;
                for (GraphEdge edge : graphModel.getEdges()) {
                    if (edge.joins(selected, v)) {
                        verticesConnected = true;
                        break;
                    }
                }
                if (!verticesConnected) edges.add(new GraphEdge(selected, v));
            }
        }
        return edges;
    }

    private String[] setEdgesNames(ArrayList<GraphEdge> edges) {
        String[] names = new String[edges.size()];
        int i = 0;
        for (GraphEdge e : edges) {
            names[i] = e.toString();
            i++;
        }
        return names;
    }

    @Override
    public void update(Observable observable, Object o) {
        fixEnabled();
    }

    // used for enabling/disabling the button depending on relevance
    private void fixEnabled() {
        if (graphModel.getSelectedVertex() != null) {
            setEnabled(true);
        } else {
            setEnabled(false);
        }
    }
}
