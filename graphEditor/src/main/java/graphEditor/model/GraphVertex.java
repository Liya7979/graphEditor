package graphEditor.model;

import java.awt.*;

public class GraphVertex {
    private String name;
    private Rectangle rectangle;

    // used for pasting vertex next to copied vertex
    public GraphVertex(GraphVertex another) {
        this.name = another.name;
        this.rectangle = new Rectangle(another.getRectangle().x + 20, another.getRectangle().y + 20,
                another.getRectangle().width, another.getRectangle().height);
    }

    public GraphVertex(int x, int y, int width, int height, String name) {
        this.name = name;
        rectangle = new Rectangle(x, y, width, height);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    @Override
    public String toString() {
        return name + " " + rectangle.x + " " + rectangle.y + " " + rectangle.width + " " + rectangle.height;
    }
}
