package backend_models;

import java.util.ArrayList;

public class LoadFiles {
    public static String saveStatus; //This will be updated constantly in save() to be used by VisualSpace to tell what stage of saving the program is at

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
