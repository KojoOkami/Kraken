package krakenwriter.frontend;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;

import krakenwriter.backend.ComputerFile;
import krakenwriter.backend.ConnectionLine;
import krakenwriter.backend.ID;
import krakenwriter.backend.VisualObject;

public class VisualSpace {
	
	@SuppressWarnings("unused")
	private static String status;
	
	public static InitWindow iw;
	public static MainWindow mw;
	
	private static int x;
	private static int y;
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
    	if (mw != null) {
    		mw.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    		mw.dispose();
    	}
    	mw = new MainWindow();
    	mw.setTitle(projectName.replace("_", " "));
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
    	if (mw != null) {
    		mw.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    		mw.dispose();
    	}
    	mw = new MainWindow();
    	mw.setTitle(projectName.replace("_", " "));
    	
    	objects = ComputerFile.load(projectName.replace("_", " "));
    	for (VisualObject o : objects) {
        	mw.createFrame(o);
    	}
    	
    	updateStatus("Done");
    	mw.setVisible(true);
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
    
    public static void deleteObject(VisualObject obj) {
    	objects.remove(obj);
    }
    
    public static void updateStatus(String status) {
    	VisualSpace.status = status;
    	if (iw != null) {
    		iw.setTitle(status);
    	}
    }
    
    public static void setDimensions(int width, int height) {
    	VisualSpace.width = width;
    	VisualSpace.height = height;
    	mw.setBounds(mw.getX(), mw.getY(), width, height);
    }
    
    public static int width() {
    	return width;
    }
    
    public static int height() {
    	return height;
    }
    
    public static void setPos(int x, int y) {
    	VisualSpace.x = x;
    	VisualSpace.y = y;
    	mw.setBounds(x, y, mw.getWidth(), mw.getHeight());
    }
    
    public static int x() {
    	return x;
    }
    
    public static int y() {
    	return y;
    }
    
    public static Iterator<VisualObject> getObjectIterator() {
    	return objects.iterator();
    }
    
    public static void addConnection(ConnectionLine cl) {
    	lines.add(cl);
    }

	public static void deleteProject() {
		mw.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mw.dispose();
		ComputerFile.deleteProject(projectName);
		iw = new InitWindow();
        iw.setVisible(true);
	}
}
