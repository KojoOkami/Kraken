package krakenwriter.app;

import krakenwriter.frontend.BackendModelSetup;
import krakenwriter.frontend.ModelsAndViewsController;
import krakenwriter.frontend.MainViewDisplay;

public class TheApp implements Runnable {

    @Override
    public void run() {

        BackendModelSetup theBackendModel = new BackendModelSetup();
        MainViewDisplay theMainViewDisplay = new MainViewDisplay(theBackendModel);
        ModelsAndViewsController theMainViewsController = new ModelsAndViewsController(theBackendModel, theMainViewDisplay);

        theMainViewDisplay.setVisible(true);
    }
}