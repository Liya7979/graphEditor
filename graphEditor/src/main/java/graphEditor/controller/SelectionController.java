package graphEditor.controller;

import graphEditor.model.GraphModel;
import graphEditor.model.GraphVertex;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class SelectionController implements MouseListener, MouseMotionListener {
    private GraphModel graphModel;


    public SelectionController(GraphModel graphModel) {
        this.graphModel = graphModel;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        // select vertex if mouseclick is inside the vertex's area
        for (int i = graphModel.getVertices().size() - 1; i >= 0; i--) {
            GraphVertex vertex = graphModel.getVertices().get(i);
            Rectangle r = vertex.getRectangle();
            if ((r.getX() <= mouseEvent.getX())
                    && ((r.getX() + r.getWidth()) >= mouseEvent.getX())) {
                if ((r.getY() <= mouseEvent.getY())
                        && ((r.getY() + r.getHeight()) >= mouseEvent.getY())) {
                    graphModel.setSelectedVertex(vertex);
                    return;
                }
            }
        }
        graphModel.setSelectedVertex(null);


    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        if (graphModel.getSelectedVertex() == null) return;

        if (graphModel.getSelectedVertex() != null) {
            graphModel.getSelectedVertex().getRectangle().setLocation(mouseEvent.getX() -
                            (int) graphModel.getSelectedVertex().getRectangle().getWidth() / 2,
                    mouseEvent.getY() - (int) graphModel.getSelectedVertex().getRectangle().getHeight() / 2);
            graphModel.update();
        }

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
    }


    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
    }
}
