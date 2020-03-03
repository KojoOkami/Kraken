package backend_models;

import java.util.ArrayList;

public class Text {
    private String rawText;
    private ArrayList<InternalDocument> referencedDocuments;

    public Text(String text) {
        this.rawText = text;
    }
    public String getRawText() { return rawText; }

    public String getRawText(int iLine, int iChar, int fLine, int fChar) { 
        String[] lines = rawText.split("\n");
        String text = lines[iLine].substring(iChar);
        for (int i = iLine; i < fLine - 1; i++) {
            text += lines[i];
        }
        text += lines[fLine].substring(0, fChar);
        return text;
    }

    public void addReferencedDocument(InternalDocument referencedDocument) { this.referencedDocuments.add(referencedDocument); }

    public ArrayList<InternalDocument> getReferencedDocument() { return referencedDocuments; }
    
    public void setRawText(String rawText) {
        this.rawText = rawText;
    }
    
}
