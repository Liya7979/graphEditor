package graphEditor;

import graphEditor.model.GraphModel;
import graphEditor.view.GraphFrame;

import java.io.File;

public class GraphEditor {

    public static void main(String[] args) {
        GraphModel g = new GraphModel();
        if (args.length == 1) {
            GraphModel loaded = GraphModel.load(new File(args[0]));
            g.loadModel(loaded);
        }
        new GraphFrame(g);

    }

}
