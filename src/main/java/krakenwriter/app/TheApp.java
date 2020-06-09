package krakenwriter.app;

import krakenwriter.frontend.InitWindow;
import krakenwriter.frontend.VisualSpace;

public class TheApp implements Runnable {

    @Override
    public void run() {
        VisualSpace.iw = new InitWindow();
        
        VisualSpace.iw.setVisible(true);
    }
}
