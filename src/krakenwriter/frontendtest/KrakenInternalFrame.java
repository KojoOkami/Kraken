package krakenwriter.frontendtest;

import javax.swing.*;
import krakenwriter.backend.VisualObject;

public class KrakenInternalFrame extends JInternalFrame {
    static int openFrameCount = 0;
    static final int X_OFFSET = 30, Y_OFFSET = 30;

    private final krakenwriter.backend.VisualObject linkedObject;

    JLabel aTitle;


    public KrakenInternalFrame(VisualObject obj) {
       super();
        
       System.out.println("Constructor");

       this.linkedObject = obj;
       
       initComponents();
    }
    
    private void initComponents() {
        aTitle = new JLabel(linkedObject.title);
        getContentPane().add(aTitle);

        setSize(linkedObject.width(), linkedObject.height());

        setLocation(linkedObject.x(), linkedObject.y());
        
        pack();
    }
    
    
}