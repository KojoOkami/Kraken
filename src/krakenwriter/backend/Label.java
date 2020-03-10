package krakenwriter.backend;

public class Label extends VisualObject {

    public Label(int x, int y, int width, int height) {
        super("Untitled", null, x, y, width, height, null, null);
    }

    public Label(int x, int y, int width, int height, VisualObject parent) {
        super("Untitled", null, x, y, width, height, new VisualObject[]{parent}, null);
    }
    
    public Label(String title, int x, int y, int width, int height, String id, VisualObject[] parents, VisualObject[] children) {
        super(title, null, x, y, width, height, id, parents, children);
    }
    
}
