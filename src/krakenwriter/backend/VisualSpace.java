package krakenwriter.backend;

import java.util.ArrayList;

public class VisualSpace {

    public static boolean initialized = false; //This is true if a project has been loaded or created, false if not.
    private static int width;
    private static int height;
    public static String projectName;
    private static ArrayList<VisualObject> objects;

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
        //Will take a new object, put it in the objects ArrayList
        //And then place it visually on the space
        //(It will make sure to check the size and position of the space and object and increase the size of the Visual Space if necessary)
        //It will then call applyConnections() using the the object as a parameter
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
     * @param Title the title of the object
     * @return all objects with that title in the project
     */
    public static ArrayList<VisualObject> getObject(String Title) {

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
