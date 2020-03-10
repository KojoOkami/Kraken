package krakenwriter.backend;

import java.util.ArrayList;

public class VisualSpace {

    public static boolean initialized = false; //This is true if a project has been loaded or created, false if not.
    private static int width;
    private static int height;
    public static String projectName;
    private static ArrayList<VisualObject> objects = new ArrayList();

    /**
     * Sets all variables to default for a new project.
     *
     * @param projectName Name for the new Project
     */
    public static void createNewProject(String projectName) {
        //This will check if the projectName already exists..
        //If it does not, create a new project with default sizes
        //If it does, load the project from the files using loadProject()
    }

    /**
     * Loads an existing project from file.
     */
    public static void loadProject() {
        //This will get all of the saved data in the project name file
        //Then it will render all of the objects on the visual space

        //This will call LoadFiles.load(projectName);
    }

    /**
     * Saves the current project to a file.
     */
    public static void saveProject() {
        //This will tell the LoadFiles class to save all of the info stored in this class
        //Will constantly check the saveStatus variable in LoadFiles to see what stage the saving is at

        //This will call LoadFiles.save(projectName, objects);
    }

    /**
     * Creates a new object in the visual space.
     *
     * @param newObject the name of the new object ("Document", "Label")
     * @return the object that was just created
     */
    public static VisualObject createNewObject(String newObject) {
        System.out.println("cno");
        VisualObject obj;
        if (newObject.equals("Document")) {
            objects.add(obj = new ExternalDocument(0, 0, 300, 300));
            return obj;
        } else if (newObject.equals("Label")) {
            objects.add(obj = new Label(0, 0, 300, 300));
            return obj;
        }
        return null;
    }

    /**
     * Creates a new object in the visual space.
     *
     * @param newObject the name of the new object ("Document", "Label")
     * @param parent the parent of the new object
     * @return the object that was just created
     */
    public static VisualObject createNewObject(String newObject, VisualObject parent) {
        System.out.println("cno");
        VisualObject obj;
        switch (newObject) {
            case "Document":
                objects.add(obj = new ExternalDocument(0, 0, 300, 300, parent));
                return obj;
            case "Label":
                objects.add(obj = new Label(0, 0, 300, 300, parent));
                return obj;
        }
        return null;
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
        ArrayList<VisualObject> list = new ArrayList();
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

    /**
     * Creates and applies Connections lines between an object and its child
     * objects.
     *
     * @param object the parent object
     */
    public static void applyConnections(Object object) {

    }

}
