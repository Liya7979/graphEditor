package graphEditor.model;

public class GraphEdge {
    private GraphVertex vertexOne;
    private GraphVertex vertexTwo;

    public GraphEdge(GraphVertex vertexOne, GraphVertex vertextTwo) {
        this.vertexOne = vertexOne;
        this.vertexTwo = vertextTwo;
    }

    public GraphVertex getVertexOne() {
        return vertexOne;
    }

    public GraphVertex getVertexTwo() {
        return vertexTwo;
    }

    @Override
    public String toString() {
        return vertexOne.getName() + " " + vertexTwo.getName();
    }

    // checks whether the edge is connecting given vertices
    public boolean joins(GraphVertex v1, GraphVertex v2) {
        return (v1 == vertexOne && v2 == vertexTwo) ||
                (v1 == vertexTwo && v2 == vertexOne);
    }

}
