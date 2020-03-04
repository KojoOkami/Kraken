package backend_models;

import java.util.ArrayList;

public class VisualSpace {
    public static boolean initialized = false; //This is true if a project has been loaded or created, false if not.
    private static int width;
    private static int height;
    public static String projectName;
    private static ArrayList<VisualObject> objects;

    public static void createNewProject(String projectName){
        //This will check if the projectName already exists..
        //If it does not, create a new project with default sizes
        //If it does, load the project from the files using loadProject()
    }

    public static VisualObject createNewObject(String newObject){
        //Will take a new object, put it in the objects ArrayList
        //And then place it visually on the space
        //(It will make sure to check the size and position of the space and object and increase the size of the Visual Space if necessary)
        //It will then call applyConnections() using the the object as a parameter
        return null;
    }
    
    public static VisualObject getObject(ID id) {
        for (VisualObject object : objects) {
            if (object.getID().equals(id)) {
              return object;
            }
        }
        return null;
    }
    
    public static VisualObject getObject(String Title) {
        
        return null;
    }

    private static void applyConnections(Object object){
        //This will check the parents and children objects of the object parameter
        //Then it will create new ConnectionLine objects and connect them to the objects in question if there is not a line already there
    }

    private static void loadProject() {
        //This will get all of the saved data in the project name file
        //Then it will render all of the objects on the visual space

        //This will call LoadFiles.load(projectName);
    }

    private static void saveProject(){
        //This will tell the LoadFiles class to save all of the info stored in this class
        //Will constantly check the saveStatus variable in LoadFiles to see what stage the saving is at

        //This will call LoadFiles.save(projectName, objects);
    }
}
