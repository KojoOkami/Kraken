package krakenwriter.backend;

public class InternalDocument extends Obj {

    private ExternalDocument externalDoc;
    private Text internalText;

    public InternalDocument(ExternalDocument externalDoc) {
        super(externalDoc.title, externalDoc.description, externalDoc.getID());
        this.externalDoc = externalDoc;
        this.internalText = new Text("Use HTML Markdown to Format Text. (Eg. &lt;b&gt; This is bold text &lt;/b&gt; --> <b>This is bold text</b> )."
        		+ "<br>Save (CTRL + S) to update the text after writing in HTML Markdown."
        		+ "<br>Because of this you will also have to use &lt;br&gt; instead of just pressing enter for line breaks.");
    }
    
    public ExternalDocument getExternal() {
        return externalDoc;
    }
    
    public Text getInternalText() {
        return internalText;
    }
}
