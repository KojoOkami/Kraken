package krakenwriter.backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ComputerFile {

    private final static String PROJECT_PATH = "../../../../../../Projects/";
    protected static ArrayList<ComputerFile> files = new ArrayList();

    private String PATH;
    public Object object;
    public static String status; //This will be updated constantly in save() to be used by VisualSpace to tell what stage of saving the program is at

    /*
    File Contents:
    
    Title
    Description
    x,y
    width,height
    parents id
    children id
    
    (For Documents)
    Text
    Referenced Documents IDs
    */
    
    public ComputerFile(Obj object) {
        files.add(this);
        this.PATH = PROJECT_PATH + VisualSpace.projectName.strip().replaceAll(" ", "_") + "\\" + object.getID() + object.title.strip().replaceAll(" ", "_") + ".txt";
        this.object = object;
        this.status = null;
    }

    public static Object getDocument(ID id) {
        File folder = new File(PROJECT_PATH + VisualSpace.projectName.strip().replaceAll(" ", "_"));
        File object = null;
        for (File f : folder.listFiles()) {
            if (f.getName().substring(0, 9).equals(id.toString())) {
                object = f;
            }
        }
        
        Scanner sc = null;
        try {
            sc = new Scanner(object);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ComputerFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        String title = sc.nextLine(), description = sc.nextLine();
        int x = sc.nextInt(), y = sc.nextInt(), width = sc.nextInt(), height = sc.nextInt();
        String parentIDs = sc.nextLine(), childIDs = sc.nextLine();
        String ID = object.getName().substring(0,9);
        if (object.getName().substring(0, 3).equals("DOC")) {
            String text = sc.nextLine();
            String refDocsIDs = sc.nextLine();
            
        }
        
        
        
        return null;
    }

    public void saveDocument() {

    }

    public static ArrayList<Object>[] load(String projectName) {

        //Will find all of the files for the specified project
        //All objects found in the data will be parsed into the respective object type and returned in an ArrayList
        return null;
    }

    public static void save(String projectName, ArrayList<Object>[] objects) {
        //Will find the location for the files for the specified project if it exists
        //Will create or overwrite the data for each object in the ArrayList
        //Uses ComputerFile.getData() to get the data and parse it
    }
}
