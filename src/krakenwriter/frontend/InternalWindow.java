package krakenwriter.frontend;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import krakenwriter.backend.VisualObject;

/**
 *
 * @author Connor
 */
public class InternalWindow extends JInternalFrame {

    JPanel pane;

    JLabel name;
    JLabel description;
    JButton openBtn;
    JButton editBtn;

    VisualObject obj;

    static int openFrameCount = 0;
    static final int X_OFFSET = 30, Y_OFFSET = 30;

    InternalWindow(VisualObject object) {
        super(object.title + (++openFrameCount),
                true, //resizable
                true, //closable
                true, //maximizable
                true);
        this.obj = object;

        setLocation(X_OFFSET * openFrameCount, Y_OFFSET * openFrameCount);

        initComponents();
    }

    private void initComponents() {
        pane = new JPanel();

        pane.setLayout(new GridBagLayout());
        GridBagConstraints c;

        name = new JLabel(obj.title);
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 0;
        //c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        //c.ipadx = 0;
        c.ipady = 0;
        c.insets = new Insets(0, 0, 0, 0);
        c.anchor = GridBagConstraints.PAGE_START;
        //c.weightx = 0;
        c.weighty = 1.0;
        pane.add(name, c);

        description = new JLabel(obj.description);
        c.gridx = 1;
        c.gridy = 1;
        //c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        //c.ipadx = 0;
        c.ipady = 0;
        c.insets = new Insets(0, 0, 0, 0);
        c.anchor = GridBagConstraints.CENTER;
        //c.weightx = 0;
        c.weighty = 1.0;
        pane.add(description, c);

        openBtn = new JButton();
        openBtn.setText("Open");
        openBtn.addActionListener(new OpenBtnListener());
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        //c.gridheight = 1;
        c.fill = GridBagConstraints.VERTICAL;
        c.ipadx = 0;
        //c.ipady = 0;
        c.insets = new Insets(0, 0, 0, 0);
        c.anchor = GridBagConstraints.LAST_LINE_START;
        c.weightx = 1.0;
        //c.weighty = 0;
        pane.add(openBtn, c);
        
        editBtn = new JButton();
        ImageIcon imageIcon = new ImageIcon("./bin/edit.png");
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(15, 15,  java.awt.Image.SCALE_SMOOTH); 
        editBtn.setIcon(new ImageIcon(newimg));
        openBtn.addActionListener(new EditBtnListener());
        c.gridx = 2;
        c.gridy = 2;
        c.gridwidth = 1;
        //c.gridheight = 1;
        c.fill = GridBagConstraints.VERTICAL;
        c.ipadx = 0;
        //c.ipady = 0;
        c.insets = new Insets(0, 0, 0, 0);
        c.anchor = GridBagConstraints.LAST_LINE_END;
        c.weightx = 1.0;
        //c.weighty = 0;
        pane.add(editBtn, c);

        add(pane);
        
        this.pack();
    }

    private class OpenBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Open Btn");
        }

    }

    private class EditBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Edit Btn");
        }

    }

}
