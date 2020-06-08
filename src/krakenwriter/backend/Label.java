package krakenwriter.backend;

public class Label extends VisualObject {

    public Label() {
        super("Untitled", "Example", -1, -1, -1, -1, null, null);
    }

    public Label(VisualObject parent) {
        super("Untitled", "Example", -1, -1, -1, -1, new ID[]{parent.getID()}, null);
    }
    
    public Label(String title, String description, int x, int y, int width, int height, String id, ID[] parents, ID[] children) {
        super(title, description, x, y, width, height, id, parents, children);
    }
    
    public Label(String title, String description, int x, int y, int width, int height, ID id, ID[] parents, ID[] children) {
        super(title, description, x, y, width, height, ID.toString(id), parents, children);
    }
    
}
