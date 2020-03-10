package krakenwriter.frontend;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import krakenwriter.backend.VisualObject;

/**
 *
 * @author Connor
 */
public class InternalWindow extends JInternalFrame implements InternalFrameListener {

    JPanel pane;

    JLabel name;
    JLabel description;
    JButton openBtn;
    JButton editBtn;

    VisualObject obj;

    InternalWindow(VisualObject object) {
        super(object.title,
                true, //resizable
                true, //closable
                true, //maximizable
                true);
        this.obj = object;
        
        initComponents();
    }

    private void initComponents() {
        pane = new JPanel();

        pane.setLayout(new GridBagLayout());
        GridBagConstraints c;

        name = new JLabel(obj.title);
        name.addMouseListener(l);
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
        description.addMouseListener(l);
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

    @Override
    public void internalFrameOpened(InternalFrameEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void internalFrameClosing(InternalFrameEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void internalFrameClosed(InternalFrameEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void internalFrameIconified(InternalFrameEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void internalFrameDeiconified(InternalFrameEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void internalFrameActivated(InternalFrameEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void internalFrameDeactivated(InternalFrameEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private class editTextListener implements MouseListener {

        JLabel label;
        JTextField field;
        
        private boolean singleClicked;
        
        public editTextListener(JLabel label, JTextField field) {
            this.label = label;
            this.field = field;
            this.singleClicked = false;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (singleClicked) {
                label.setVisible(false);
                field.setEnabled(true);
                field.setVisible(true);
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
        
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
