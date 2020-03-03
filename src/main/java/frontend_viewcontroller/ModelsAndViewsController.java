package frontend_viewcontroller;

public class ModelsAndViewsController {
    //Instance vars to reference the other frontend classes
    BackendModelSetup theBackendModel;
    MainViewDisplay theMainViewDisplay;

    //This is where the classes that carry out the code that runs when the GUI is interacted with

    public ModelsAndViewsController(BackendModelSetup aBackend, MainViewDisplay aMainViewDisplay) {
        this.theBackendModel = aBackend;
        this.theMainViewDisplay = aMainViewDisplay;
        this.initController();
    }

    private void initController() {
        //Initializes all of the buttons and intractable actions on the GUI to classes in this file
    }
}
