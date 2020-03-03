package backend_models;

/**
 *
 * @author Connor
 */
public class ID {

    private static int instances = 0;
    
    private String identifier; //DOC (Document), LBL (Label), CLNN (Connection Line)
    private int code; //Hex Code (0x??????)

    public ID(Obj object) {
        if (object instanceof ExternalDocument) {
            identifier = "DOC";
        } else if (object instanceof Label) {
            identifier = "LBL";
        } else if (object instanceof ConnectionLine) {
            identifier = "CL";
        } else {
            new RuntimeException("Failed to give object identifier").printStackTrace();
        }
        
        do {
           code = instances++;
        } while (!checkID());
    }

    public String id() {
        return identifier + Integer.toHexString(code);
    }
    
    private boolean checkID() {
        return VisualSpace.getObject(id()) != null;
    } 

}
