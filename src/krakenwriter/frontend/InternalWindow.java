package krakenwriter.frontend;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
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

    JTextField name;
    JTextField description;
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
        pane.setBackground(Color.white);

        pane.setLayout(new GridBagLayout());
        GridBagConstraints c;

        name = new JTextField();
        name.setText(obj.title);
        name.setHorizontalAlignment(JTextField.CENTER);
        name.setBackground(null);
        name.setBorder(null);
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        c.gridheight = 1;
        c.fill = GridBagConstraints.BOTH;
        c.ipadx = 3;
        c.ipady = 3;
        c.insets = new Insets(0, 0, 0, 0);
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 10;
        //c.weighty = 1;
        pane.add(name, c);

        description = new JTextField();
        description.setText(obj.description);
        description.setHorizontalAlignment(JTextField.CENTER);
        description.setBackground(null);
        description.setBorder(null);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 3;
        c.gridheight = 1;
        c.fill = GridBagConstraints.BOTH;
        c.ipadx = 3;
        c.ipady = 3;
        c.insets = new Insets(0, 0, 0, 0);
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 10;
        //c.weighty = 1;
        pane.add(description, c);

        openBtn = new JButton();
        openBtn.setText("Open");
        openBtn.addActionListener(new OpenBtnListener());
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        //c.gridheight = 1;
        c.fill = GridBagConstraints.BOTH;
        c.ipadx = 0;
        //c.ipady = 0;
        c.insets = new Insets(0, 0, 0, 0);
        c.anchor = GridBagConstraints.LAST_LINE_START;
        c.weightx = 1;
        c.weighty = 10;
        pane.add(openBtn, c);

        editBtn = new JButton();
        ImageIcon imageIcon = new ImageIcon("./bin/edit.png");
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);
        editBtn.setIcon(new ImageIcon(newimg));
        openBtn.addActionListener(new EditBtnListener());
        c.gridx = 2;
        c.gridy = 2;
        c.gridwidth = 1;
        //c.gridheight = 1;
        c.fill = GridBagConstraints.BOTH;
        c.ipadx = 0;
        //c.ipady = 0;
        c.insets = new Insets(0, 0, 0, 0);
        c.anchor = GridBagConstraints.LAST_LINE_END;
        c.weightx = 1;
        c.weighty = 10;
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
