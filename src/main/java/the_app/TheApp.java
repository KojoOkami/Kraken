package the_app;

import frontend_viewcontroller.*;

public class TheApp implements Runnable {

    @Override
    public void run() {

        BackendModelSetup theBackendModel = new BackendModelSetup();
        MainViewDisplay theMainViewDisplay = new MainViewDisplay(theBackendModel);
        ModelsAndViewsController theMainViewsController = new ModelsAndViewsController(theBackendModel, theMainViewDisplay);

        theMainViewDisplay.setVisible(true);
    }
}