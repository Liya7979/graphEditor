package graphEditor.model;

import graphEditor.view.GraphPanel;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class GraphModel extends Observable {
    private List<GraphVertex> vertices;
    private List<GraphEdge> edges;
    private GraphVertex selectedVertex;
    private GraphVertex copiedVertex;
    private GraphEdge selectedEdge;
    private UndoManager manager;
    private GraphPanel panel;

    public GraphModel() {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
        manager = new UndoManager();
    }

    public GraphModel(List<GraphVertex> vertices, List<GraphEdge> edges) {
        this.vertices = vertices;
        this.edges = edges;
    }

    public List<GraphVertex> getVertices() {
        return this.vertices;
    }

    public GraphVertex getLastVertex() {
        return vertices.get(vertices.size() - 1);
    }

    public GraphVertex getCopiedVertex() {
        return copiedVertex;
    }

    public void setCopiedVertex(GraphVertex copiedVertex) {
        this.copiedVertex = copiedVertex;
    }

    public GraphVertex getSelectedVertex() {
        return selectedVertex;
    }

    public void setSelectedVertex(GraphVertex selectedVertex) {
        this.selectedVertex = selectedVertex;
        update();
    }

    private static GraphVertex getVertexByName(List<GraphVertex> vertices, String name) {
        for (GraphVertex v : vertices) {
            if (v.getName().equals(name)) {
                return v;
            }
        }
        return null;

    }

    public List<GraphEdge> getEdges() {
        return this.edges;
    }

    public GraphEdge getSelectedEdge() {
        return selectedEdge;
    }

    public void setSelectedEdge(GraphEdge selectedEdge) {
        this.selectedEdge = selectedEdge;
    }

    public UndoManager getManager() {
        return manager;
    }

    public GraphPanel getPanel() {
        return panel;
    }


    public void setPanel(GraphPanel panel) {
        this.panel = panel;
    }


    public void addDefaultVertex() {
        // adds default size vertex of 70x70 size and custom name
        JTextField name = new JTextField();
        final JComponent[] inputs = new JComponent[]{new JLabel("Choose the name of new vertex"), name};
        int result = JOptionPane.showConfirmDialog(null, inputs,
                "Add a vertex", JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            GraphVertex vertex = new GraphVertex(70, 70, 70, 70, name.getText());
            vertices.add(vertex);
            update();
        }

    }

    public void addVertex(GraphVertex vertex) {
        vertices.add(vertex);
        update();
    }


    public void addEdge(GraphEdge edge) {
        edges.add(edge);
        notifyObservers();
    }

    public void removeVertex(GraphVertex vertex) {
        List<GraphEdge> edgesToRemove = new ArrayList<>();
        vertices.remove(vertex);
        for (GraphEdge edge : edges) {
            if (edge.getVertexOne() == vertex || edge.getVertexTwo() == vertex) {
                edgesToRemove.add(edge);
            }
        }
        edges.removeAll(edgesToRemove);

    }

    public void removeEdge(GraphEdge edge) {
        for (GraphEdge e : edges) {
            if ((e.getVertexOne() == edge.getVertexOne() && e.getVertexTwo() == edge.getVertexTwo()) ||
                    e.getVertexOne() == edge.getVertexTwo() && e.getVertexTwo() == edge.getVertexOne()) {
                edges.remove(e);
                break;
            }
        }
    }


    public static void save(GraphModel graph, File file) {
        // saves all the data line by line
        // all objects are saved using toString() method

        try (PrintWriter printWriter = new PrintWriter(file)) {
            printWriter.println(graph.getVertices().size() + " " + graph.getEdges().size());
            for (GraphVertex v : graph.getVertices()) {
                printWriter.println(v.toString());
            }
            for (GraphEdge e : graph.getEdges()) {
                printWriter.println(e.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save(GraphModel graph, String filename) {
        // similar method, but string filename is used
        try (PrintWriter printWriter = new PrintWriter(new File(filename))) {
            printWriter.println(graph.getVertices().size() + " " + graph.getEdges().size());
            for (GraphVertex v : graph.getVertices()) {
                printWriter.println(v.toString());
            }
            for (GraphEdge e : graph.getEdges()) {
                printWriter.println(e.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static GraphModel load(File file) {
        // loads by parsing the saved file line by line
        System.out.println(file.getAbsolutePath());
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            String[] words = line.split(" ");
            int vertexCount = Integer.parseInt(words[0]);
            int edgeCount = Integer.parseInt(words[1]);
            List<GraphVertex> vertices = new ArrayList<>(vertexCount);
            List<GraphEdge> edges = new ArrayList<>(edgeCount);

            for (int i = 0; i < vertexCount; i++) {
                line = reader.readLine();
                words = line.split(" ");
                vertices.add(new GraphVertex(Integer.parseInt(words[1]), Integer.parseInt(words[2]),
                        Integer.parseInt(words[3]), Integer.parseInt(words[4]), words[0]));
            }

            for (int i = 0; i < edgeCount; i++) {
                line = reader.readLine();
                words = line.split(" ");
                GraphVertex v1 = getVertexByName(vertices, words[0]);
                GraphVertex v2 = getVertexByName(vertices, words[1]);
                if (v1 != null && v2 != null) {
                    edges.add(new GraphEdge(v1, v2));
                }
            }

            return new GraphModel(vertices, edges);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Could not open the file", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("The file could not be opened. Reason: " + e.getMessage());
            return null;
        }
    }

    public void loadModel(GraphModel model) {
        if (model == null) {
            JOptionPane.showMessageDialog(null, "Could not laod model", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        this.vertices = model.vertices;
        this.edges = model.edges;
        setChanged();
        notifyObservers();
    }


    public void update() {
        setChanged();
        notifyObservers();
    }
}
