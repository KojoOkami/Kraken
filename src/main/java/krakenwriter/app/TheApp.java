package krakenwriter.app;

import krakenwriter.frontend.MainWindow;

public class TheApp implements Runnable {

    @Override
    public void run() {
        MainWindow theMainWindow = new MainWindow();

        theMainWindow.setVisible(true);
    }
}
