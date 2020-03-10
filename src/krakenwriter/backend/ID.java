package krakenwriter.backend;

/**
 *
 * @author Connor
 */
public class ID {

    private static int instances = 0;
    
    private String identifier; //DOC (Document), LBL (Label), CLN (Connection Line)
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
        } while (checkID());
    }
    
    private ID(String identifier, int code) {
        this.identifier = identifier;
        this.code = code;
    }
    
    public String getIdentifier() {
        return identifier;
    }

    public String id() {
        return identifier + Integer.toHexString(code);
    }
    
    public static ID toID(String id) {
        switch(id.substring(0,3)) {
            case "DOC":
                return new ID("DOC", Integer.parseInt(id.substring(3, 9)));
            case "LBL":
                return new ID("LBL", Integer.parseInt(id.substring(3, 9)));
            case "CLN":
                return new ID("CLN", Integer.parseInt(id.substring(3, 9)));
        }
        return null;
    }
    
    public static String toString(ID id) {
        return id.identifier + Integer.toHexString(id.code);
    }
    
    private boolean checkID() {
        return VisualSpace.getObject(this) != null;
    }

}
