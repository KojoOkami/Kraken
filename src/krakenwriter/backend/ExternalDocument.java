package krakenwriter.backend;

public class ExternalDocument extends VisualObject {

    private InternalDocument internalDoc;

    public ExternalDocument(String title, String description, int x, int y, int width, int height, VisualObject[] parents, VisualObject[] children) {
        super(title, description, x, y, width, height, parents, children);
        this.internalDoc = new InternalDocument(this);
    }

    public ExternalDocument(String title, String description, int x, int y, int width, int height, String id, VisualObject[] parents, VisualObject[] children) {
        super(title, description, x, y, width, height, id, parents, children);
        this.internalDoc = new InternalDocument(this);
    }

    public InternalDocument getInternal() {
        return internalDoc;
    }
}
