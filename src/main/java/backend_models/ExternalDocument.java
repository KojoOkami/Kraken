package backend_models;

public class ExternalDocument extends VisualObject {

    private InternalDocument internalDoc;

    public ExternalDocument(String title, String description, int x, int y, int width, int height, String id) {
        super(title, description, x, y, width, height, id);
        this.internalDoc = new InternalDocument(this);
    }

    public ExternalDocument(String title, String description, int x, int y, int width, int height, VisualObject[] parentObjects, String id) {
        super(title, description, x, y, width, height, parentObjects, id);
        this.internalDoc = new InternalDocument(this);
    }

    @Override
    public String generateID() { //Generates ID but with a document identifier on the front
        return "DOC" + super.generateID();
    }

    public InternalDocument getInternal() { return internalDoc; }
}
