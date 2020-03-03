package backend_models;

public class InternalDocument extends Obj {

    private ExternalDocument externalDoc;
    private Text internalText;

    public InternalDocument(ExternalDocument externalDoc) {
        super(externalDoc.getTitle(), externalDoc.getDescription(), externalDoc.getID());
        this.externalDoc = externalDoc;
    }
    
    public ExternalDocument getExternal() {
        return externalDoc;
    }
    
    public Text getInternalText() {
        return internalText;
    }
}
