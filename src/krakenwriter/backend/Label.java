package krakenwriter.backend;

public class Label extends VisualObject {

    public Label() {
        super("Untitled", null, -1, -1, -1, -1, null, null);
    }

    public Label(VisualObject parent) {
        super("Untitled", null, -1, -1, -1, -1, new VisualObject[]{parent}, null);
    }
    
    public Label(String title, int x, int y, int width, int height, String id, VisualObject[] parents, VisualObject[] children) {
        super(title, null, x, y, width, height, id, parents, children);
    }
    
}
