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
import javax.swing.SwingUtilities;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import krakenwriter.backend.ExternalDocument;
import krakenwriter.backend.VisualObject;

/**
 *
 * @author Connor
 */
public class InternalWindow extends JInternalFrame implements InternalFrameListener {

    /**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = -3811493956504322783L;

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
        setResizable(true);
        
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
        name.setEditable(false);
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
        description.setEditable(false);
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

        if (obj instanceof ExternalDocument) {
	        openBtn = new JButton();
	        openBtn.setText("Open");
	        openBtn.addActionListener(new OpenBtnListener((ExternalDocument) obj));
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
        }
        
        editBtn = new JButton();
        ImageIcon imageIcon = new ImageIcon("./bin/edit.png");
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);
        editBtn.setIcon(new ImageIcon(newimg));
        editBtn.addActionListener(new EditBtnListener(this));
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
    public void setLocation(int x, int y) {
    	super.setLocation(x, y);
    	obj.setPos(x, y);
    }
    
    @Override
    public void setSize(int width, int height) {
    	super.setSize(width, height);
    	obj.setDimensions(width, height);
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

    	private ExternalDocument doc;
    	
    	public OpenBtnListener(ExternalDocument doc) {
    		this.doc = doc;
		}
    	
        @Override
        public void actionPerformed(ActionEvent e) {
        	SwingUtilities.invokeLater(new Runnable() {
    			public void run() {
    				new DocWindow(doc);
    			}
    		});
        }

    }

    private class EditBtnListener implements ActionListener {

    	private InternalWindow window;
    	
    	public EditBtnListener(InternalWindow window) {
    		this.window = window;
    	}
    	
        @Override
        public void actionPerformed(ActionEvent e) {
        	if (window.name.isEditable()) {
        		System.out.println("Not editable");
        		window.name.setEditable(false);
        		window.description.setEditable(false);
        	} else {
        		System.out.println("Editable");
        		window.name.setEditable(true);
        		window.description.setEditable(true);
        	}
        }

    }

}
