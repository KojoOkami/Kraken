package krakenwriter.backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import krakenwriter.frontend.VisualSpace;

public class ComputerFile {

    public final static String PROJECT_PATH = "C:/Users/" + System.getProperty("user.name") + "/Documents/KrakenProjects/";

    /*
     * Properties File:
     * 
     * width,height
     */
    
    /*
     * File Contents:
     * 
     * Title
     * Description
     * x,y
     * width,height
     * parent ids
     * child ids
     * text (For documents)
     */

    public static ArrayList<VisualObject> load(String projectName) {
    	VisualSpace.updateStatus("Loading properties file");
    	File folder = loadProperties(projectName);
    	VisualSpace.updateStatus("Loading object files");
    	File[] files = folder.listFiles();
    	ArrayList<VisualObject> objects = new ArrayList<VisualObject>();
    	for (File f : files) {
    		if (f.getName().startsWith("DOC") || f.getName().startsWith("LBL")) {
    			objects.add(getObj(f));
    		}
    	}
    	return objects;
    }
    
    private static File loadProperties(String projectName) {
    	String path = PROJECT_PATH + projectName.strip().replaceAll(" ", "_");
    	File folder = new File(path);
    	File properties = new File(path + "/project-properties.txt");
    	if (!folder.exists()) {
    		System.err.println("Project directory not found");
    		return null;
    	}
    	if (!properties.exists()) {
    		System.err.println("Properties file not found for project");
    		return null;
    	}
    	VisualSpace.projectName = projectName;
    	try {
			Scanner sc = new Scanner(properties);
			sc.useDelimiter(",");
	    	int width = Integer.parseInt(sc.next());
	    	int height = Integer.parseInt(sc.next());
	    	
	    	sc.close();
	    	
	    	VisualSpace.setDimensions(width, height);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    	return folder;
    }
    
    private static VisualObject getObj(File object) { 
        Scanner sc = null;
        try {
            sc = new Scanner(object);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ComputerFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        String title = sc.nextLine(), description = sc.nextLine();
        int x = sc.nextInt(), y = sc.nextInt(), width = sc.nextInt(), height = sc.nextInt();
        String parentIDs = sc.nextLine(), childIDs = sc.nextLine();
        ID id = new ID(object.getName().substring(0,3), Integer.parseInt(object.getName().substring(3)));
               
        VisualObject newObj;
        
        if (id.getIdentifier().equals("DOC")) {
            String text = sc.nextLine();
            
            newObj = new ExternalDocument(title, description, x, y, width, height, id, ID.toID(parentIDs.split(",")), ID.toID(childIDs.split(",")));
            ((ExternalDocument)newObj).getInternal().getInternalText().setRawText(text);
        } else if (id.getIdentifier().equals("LBL")) {
    		newObj = new Label(title, description, x, y, width, height, id, null, null);
    	} else {
    		System.err.println("ID Identifier not recognized: " + object.getName());
    		newObj = null;
    	}
        sc.close();
		return newObj;
    }

    public static void save() {
    	VisualSpace.updateStatus("Saving properties file");
    	saveProperties();
    	VisualSpace.updateStatus("Saving object files");
    	Iterator<VisualObject> objects = VisualSpace.getObjectIterator();
    	while (objects.hasNext()) {
    		saveObj(objects.next());
    	}
    }
    
    public static void saveProperties() {
    	String path = PROJECT_PATH + VisualSpace.projectName.strip().replaceAll(" ", "_");
    	File folder = new File(path);
    	File properties = new File(path + "/project-properties.txt");
    	if (!folder.exists()) {
    		folder.mkdirs();
    		System.out.println("Project directory created");
    	}
    	try {
    		if (properties.exists()) {
    			properties.delete();
    		}
    		properties.createNewFile();
    		
			FileWriter fw = new FileWriter(properties);
			PrintWriter pw = new PrintWriter(fw);
			
			pw.print(VisualSpace.width() + "," + VisualSpace.height());
			
			pw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    private static void saveObj(VisualObject obj) {
    	System.out.println(obj.title);
    	FileWriter fw = null;
		try {
			fw = new FileWriter(PROJECT_PATH + VisualSpace.projectName + "/" + obj.getID() + ".txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
    	PrintWriter pw = new PrintWriter(fw);
    	
    	String parentIDs = Arrays.toString(obj.getParentIDs());
    	parentIDs = parentIDs.substring(1, parentIDs.length()-1);
    	
    	String childIDs = Arrays.toString(obj.getChildIDs());
    	childIDs = childIDs.substring(1, childIDs.length()-1);
    	
    	pw.write(obj.title + "\n"
    			+ obj.description + "\n"
    			+ obj.x() + "," + obj.y() + "\n"
    			+ obj.width() + "," + obj.height() + "\n"
    			+ parentIDs + "\n"
    			+ childIDs);
    	
    	if (obj instanceof ExternalDocument) {
    		pw.write("\n" + ((ExternalDocument)obj).getInternal().getInternalText().getRawText().replaceAll("\n", "\\n"));
    	}
    	pw.close();
    	try {
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public static void deleteProject(String projectName) {
    	String path = PROJECT_PATH + VisualSpace.projectName.strip().replaceAll(" ", "_");
    	File folder = new File(path);
    	folder.delete();
    }
}
