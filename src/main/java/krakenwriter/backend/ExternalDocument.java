package krakenwriter.backend;

public class ExternalDocument extends VisualObject {

    private InternalDocument internalDoc;

    public ExternalDocument() {
        super("Untitled", "Example", -1, -1, -1, -1, null, null);
        this.internalDoc = new InternalDocument(this);
    }
    
    public ExternalDocument(VisualObject parent) {
        super("Untitled", "Example", -1, -1, -1, -1, null, null);
        this.internalDoc = new InternalDocument(this);
        parent.append(this);
    }

    public ExternalDocument(String title, String description, int x, int y, int width, int height, String id, ID[] parents, ID[] children) {
        super(title, description, x, y, width, height, id, parents, children);
        this.internalDoc = new InternalDocument(this);
    }
    
    public ExternalDocument(String title, String description, int x, int y, int width, int height, ID id, ID[] parents, ID[] children) {
        super(title, description, x, y, width, height, ID.toString(id), parents, children);
        this.internalDoc = new InternalDocument(this);
    }

    public InternalDocument getInternal() {
        return internalDoc;
    }
}
