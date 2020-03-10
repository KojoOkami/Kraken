package krakenwriter.frontend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import krakenwriter.backend.VisualObject;

/**
 *
 * @author Connor
 */

public class InternalWindow extends JInternalFrame implements ActionListener {

    JInternalFrame internalWindow;

    JLabel title;
    JLabel description;
    JButton openBtn;
    JButton editBtn;
    
    VisualObject obj;
    
    InternalWindow(VisualObject object) {
        super(object.title);
        this.obj = object;
        initComponents();
    }

    private void initComponents() {
        int inset = 50;
        setBounds(inset, inset,
                obj.width()  - inset*2,
                obj.height() - inset*2);

        internalWindow = new JInternalFrame();
        
        title = new JLabel(obj.title);
        
        description = new JLabel(obj.description);
        
        openBtn = new JButton();
        openBtn.setText("Open");
        
        editBtn = new JButton();
        editBtn.setIcon(new ImageIcon("./bin/edit.png"));
        
        SpringLayout layout = new SpringLayout();
        internalWindow.setLayout(layout);
        internalWindow.add(editBtn);
        
        internalWindow.pack();
        setVisible(true);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}
