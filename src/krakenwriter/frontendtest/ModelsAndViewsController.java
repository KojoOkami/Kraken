package krakenwriter.frontendtest;

import java.awt.event.ActionEvent;

public class ModelsAndViewsController {
    //Instance vars to reference the other frontend classes
    BackendModelSetup theBackendModel;
    MainViewDisplay theMainViewDisplay;

    //This is where the classes that carry out the code that runs when the GUI is interacted with

    public ModelsAndViewsController(MainViewDisplay aMainViewDisplay) {
        this.theMainViewDisplay = aMainViewDisplay;
        this.initController();
    }
    
    //React to menu selections.
    public void actionPerformed(ActionEvent e) {
        if ("New Project".equals(e.getActionCommand())) { //New Project

        } else if ("Open Project".equals(e.getActionCommand())) { //Open Project

        } else if ("Delete Project".equals(e.getActionCommand())) { //Delete Project

        }  else if ("Undo".equals(e.getActionCommand())) { //Undo

        } else if ("Redo".equals(e.getActionCommand())) { //Redo

        } else if ("New Document".equals(e.getActionCommand())) { //New Object
            theMainViewDisplay.createFrame("Document");
        } else if ("New Label".equals(e.getActionCommand())) { //New Object
            theMainViewDisplay.createFrame("Label");
        } else if ("Edit Object".equals(e.getActionCommand())) { //Edit Object

        } else if ("Delete Object".equals(e.getActionCommand())) { //Delete Object
            theMainViewDisplay.quit();
        } else if ("Navigator".equals(e.getActionCommand())) { //Navigator

        }
    }

    private void initController() {
        //Initializes all of the buttons and intractable actions on the GUI to classes in this file
    }
}
