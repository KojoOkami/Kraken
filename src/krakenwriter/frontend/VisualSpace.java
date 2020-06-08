package krakenwriter.frontend;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import krakenwriter.backend.ComputerFile;
import krakenwriter.backend.ConnectionLine;
import krakenwriter.backend.ID;
import krakenwriter.backend.VisualObject;

public class VisualSpace {
	
	private static String status;
	
    private static int width;
    private static int height;
    public static String projectName;
    private static ArrayList<VisualObject> objects = new ArrayList<VisualObject>();
    private static ArrayList<ConnectionLine> lines = new ArrayList<ConnectionLine>();

    /**
     * Sets all variables to default for a new project.
     *
     * @param projectName Name for the new Project
     */
    public static void createNewProject(String projectName) {
    	if (checkFolderForExistingProject(projectName)) {
    		System.err.println("ProjectAlreadyExistsError: " + projectName);
    	}
    	VisualSpace.projectName = projectName;
    	VisualSpace.objects = new ArrayList<VisualObject>();
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    	VisualSpace.width = screenSize.width;
    	VisualSpace.height = screenSize.height;
		ComputerFile.saveProperties();
    }
    
    private static boolean checkFolderForExistingProject(String projectName) {
    	File folder = new File(ComputerFile.PROJECT_PATH);
    	File[] projects = folder.listFiles();
    	if (projects == null) {
    		return false;
    	}
    	for (File f : projects) {
    		if (f.getName().equals(projectName)) {
    			return true;
    		}
    	}
    	return false;
    }

    /**
     * Loads an existing project from file.
     * 
     * @param projectName name of the project to load
     */
    public static void loadProject(String projectName) { 	
    	objects = ComputerFile.load(projectName.replace("_", " "));
    	//Something about a call to the frontend to render the new objects
    	
    	updateStatus("Assigning hierarchy");
    	createHieracrchyFromIDs();
    	
    	updateStatus("Creating connections");
    	applyConnections();
    	//Something about a call to the frontend to render the new lines
    	
    	updateStatus("Done");
    }

    /**
     * Saves the current project to a file.
     */
    public static void saveProject() {
        ComputerFile.save();
        updateStatus("Done");
    }

    /**
     * Creates a new object in the visual space.
     *
     * @param newObject the name of the new object ("Document", "Label")
     * @return the object that was just created
     */
    public static VisualObject createNewObject(VisualObject obj) {
        objects.add(obj);
        System.out.println("Created new object " + obj.title + " with id " + obj.getID().id());
        return obj;
    }

    /**
     * Gets an object in the project by the ID of the object.
     *
     * @param id the ID of the object
     * @return the object with the specified ID
     */
    public static VisualObject getObject(ID id) {
        for (VisualObject object : objects) {
            if (object.getID().equals(id)) {
                return object;
            }
        }
        return null;
    }

    /**
     * Gets an object in the project by the title of the object. CAUTION: This
     * is not a reliable way to find objects and will return all objects with
     * the specified title if there are multiple.
     *
     * @param title the title of the object
     * @return all objects with that title in the project
     */
    public static ArrayList<VisualObject> getObject(String title) {
        ArrayList<VisualObject> list = new ArrayList<VisualObject>();
        for (VisualObject object : objects) {
            if (object.title.equals(title)) {
                list.add(object);
            }
        }
        if (list.size() > 0) {
            return list;
        }
        return null;
    }
    
    public static void updateStatus(String status) {
    	VisualSpace.status = status;
    	//Something about updating frontend
    }
    
    public static void setDimensions(int width, int height) {
    	VisualSpace.width = width;
    	VisualSpace.height = height;
    }
    
    public static int width() {
    	return width;
    }
    
    public static int height() {
    	return height;
    }
    
    public static Iterator<VisualObject> getObjectIterator() {
    	return objects.iterator();
    }
    
    /**
     * Sets up the project after load and convert the IDs of the object's parents and children to objects
     */
    private static void createHieracrchyFromIDs() {
    	for (VisualObject o : objects) {
    		ArrayList<VisualObject> parents = new ArrayList<VisualObject>(1);
    		ArrayList<VisualObject> children = new ArrayList<VisualObject>(3);
    		ID[] ids = o.getParentIDs();
    		for (ID i : ids) {
    			parents.add(VisualSpace.getObject(i));
    		}
    		ids = o.getChildIDs();
    		for (ID i : ids) {
    			children.add(VisualSpace.getObject(i));
    		}
    		o.initParents(parents);
    		o.initChildren(children);
    	}
    }

    /**
     * Creates and applies Connections lines between an object and its child
     * objects.
     */
    private static void applyConnections() {
    	for (VisualObject o : objects) {
    		ArrayList<VisualObject> children = o.getChildren();
    		for (VisualObject c : children) {
    			lines.add(new ConnectionLine(o,c));
    		}
    	}
    }
    

}
