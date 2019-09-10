package graphEditor.view;

import graphEditor.controller.SelectionController;
import graphEditor.model.GraphEdge;
import graphEditor.model.GraphModel;
import graphEditor.model.GraphVertex;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class GraphPanel extends JPanel implements Observer {
    private GraphModel graphModel;
    private final Font font = new Font("TimesRoman", Font.PLAIN, 20);
    private final FontMetrics fontMetrics = getFontMetrics(font);


    GraphPanel(GraphModel graphModel, SelectionController controller) {
        this.graphModel = graphModel;
        graphModel.addObserver(this);
        setBackground(new Color(24, 176, 203));
        setVisible(true);
        setOpaque(true);
        addMouseListener(controller);
        addMouseMotionListener(controller);
    }

    private void drawVertices(Graphics g) {
        for (GraphVertex v : graphModel.getVertices()) {
            // if selected, red rectangle is drawn behind the main rectangle, otherwise black
            if (v == graphModel.getSelectedVertex()) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.BLACK);
            }
            g.fillRect((int) v.getRectangle().getX() - 2, (int) v.getRectangle().getY() - 2,
                    (int) v.getRectangle().getWidth() + 4, (int) v.getRectangle().getHeight() + 4);
            g.setColor(Color.WHITE);
            g.fillRect((int) v.getRectangle().getX(), (int) v.getRectangle().getY(),
                    (int) v.getRectangle().getWidth(), (int) v.getRectangle().getHeight());
            g.setColor(Color.BLACK);
            Font font = new Font("Verdana", Font.BOLD, 12);
            g.setFont(font);
            int x = (int) (v.getRectangle().getX() + (v.getRectangle().getWidth() / 2)) - fontMetrics.stringWidth(v.getName()) / 2;
            int y = (int) (v.getRectangle().getY() + (v.getRectangle().getHeight() / 2)) + fontMetrics.getHeight() / 3;
            g.drawString(v.getName(), x, y);
        }


    }

    private void drawEdges(Graphics g) {
        for (GraphEdge e : graphModel.getEdges()) {
            g.drawLine((int) (e.getVertexOne().getRectangle().getX() + (e.getVertexOne().getRectangle().getWidth()) / 2),
                    (int) (e.getVertexOne().getRectangle().getY() + (e.getVertexOne().getRectangle().getHeight()) / 2),
                    (int) (e.getVertexTwo().getRectangle().getX() + (e.getVertexTwo().getRectangle().getWidth()) / 2),
                    (int) (e.getVertexTwo().getRectangle().getY() + (e.getVertexTwo().getRectangle().getHeight()) / 2));
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(font);
        drawEdges(g);
        drawVertices(g);
    }

    // resizes vertex to match string length
    public void resizeVertex(GraphVertex v) {
        Rectangle r = v.getRectangle();
        int x = r.x + (int) r.getWidth() / 2;
        int y = r.y + (int) r.getHeight() / 2;
        int height = r.height;
        r.setBounds(x, y, fontMetrics.stringWidth(v.getName()) + 100, height);
    }

    @Override
    public void update(Observable observable, Object o) {
        repaint();
    }
}
