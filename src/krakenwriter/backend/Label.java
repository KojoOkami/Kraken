package krakenwriter.backend;

public class Label extends VisualObject {

    public Label(String title, int x, int y, int width, int height, VisualObject[] parents, VisualObject[] children) {
        super(title, null, x, y, width, height, parents, children);
    }
    
    public Label(String title, int x, int y, int width, int height, String id, VisualObject[] parents, VisualObject[] children) {
        super(title, null, x, y, width, height, id, parents, children);
    }
    
}
