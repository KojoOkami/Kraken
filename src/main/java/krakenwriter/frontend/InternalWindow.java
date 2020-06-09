package krakenwriter.frontend;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Image;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

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
	JTextPane description;
	JButton openBtn;
	JButton editBtn;
	JButton addChild;
	JButton deleteBtn;

	VisualObject obj;

	InternalWindow(VisualObject object) {
		super(object.title, true, // resizable
				true, // closable
				true, // maximizable
				true);
		this.obj = object;
		setResizable(true);
		
		if (obj instanceof ExternalDocument) {
			setTitle("Document");
		} else {
			setTitle("Label");
		}
		
		setMaximizable(false);
		setIconifiable(false);
		setMinimumSize(new Dimension(300, 125));
		removeMinMaxClose(this);
		initComponents();
		setSize(300, 125);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
		c.weighty = 1;
		pane.add(name, c);

		description = new JTextPane();
		StyledDocument doc = description.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		description.setText(obj.description);
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
		c.insets = new Insets(0, 0, 3, 0);
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 10;
		c.weighty = 1;
		pane.add(description, c);

		if (obj instanceof ExternalDocument) {
			openBtn = new JButton();
			openBtn.setText("Open");
			openBtn.addActionListener(new OpenBtnListener(this));
			c.gridx = 0;
			c.gridy = 2;
			c.gridwidth = 1;
			c.gridheight = 1;
			c.fill = GridBagConstraints.BOTH;
			c.ipadx = 0;
			c.ipady = 0;
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
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		c.ipadx = 0;
		c.ipady = 0;
		c.insets = new Insets(0, 0, 0, 0);
		c.anchor = GridBagConstraints.LAST_LINE_END;
		c.weightx = 1;
		c.weighty = 10;
		pane.add(editBtn, c);

		addChild = new JButton();
		imageIcon = new ImageIcon("./bin/add.png");
		image = imageIcon.getImage();
		newimg = image.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);
		addChild.setIcon(new ImageIcon(newimg));
		addChild.addActionListener(new AddChildListener());
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		c.ipadx = 0;
		c.ipady = 0;
		c.insets = new Insets(0, 0, 0, 0);
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 1;
		c.weighty = 10;
		pane.add(addChild, c);
		
		deleteBtn = new JButton();
		imageIcon = new ImageIcon("./bin/delete.png");
		image = imageIcon.getImage();
		newimg = image.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);
		deleteBtn.setIcon(new ImageIcon(newimg));
		deleteBtn.addActionListener(new DeleteBtnListener(this));
		c.gridx = 2;
		c.gridy = 3;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		c.ipadx = 0;
		c.ipady = 0;
		c.insets = new Insets(0, 0, 0, 0);
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 1;
		c.weighty = 10;
		pane.add(deleteBtn, c);
		
		add(pane);

		this.pack();
	}

	public void removeMinMaxClose(Component comp) {
		if (comp instanceof AbstractButton) {
			comp.getParent().remove(comp);
		}
		if (comp instanceof Container) {
			Component[] comps = ((Container) comp).getComponents();
			for (int x = 0, y = comps.length; x < y; x++) {
				removeMinMaxClose(comps[x]);
			}
		}
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
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void internalFrameClosing(InternalFrameEvent e) {
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void internalFrameClosed(InternalFrameEvent e) {
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void internalFrameIconified(InternalFrameEvent e) {
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void internalFrameDeiconified(InternalFrameEvent e) {
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void internalFrameActivated(InternalFrameEvent e) {
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void internalFrameDeactivated(InternalFrameEvent e) {
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose Tools | Templates.
	}

	private class DeleteBtnListener implements ActionListener {

		InternalWindow window;
		
		public DeleteBtnListener(InternalWindow window) {
			this.window = window;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Container mw = window.getParent();
			mw.remove(window);
			mw.repaint();
			VisualSpace.deleteObject(window.obj);
		}
		
	}
	
	private class AddChildListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
		}
		
	}
	
	private class OpenBtnListener implements ActionListener {

		private InternalWindow doc;

		public OpenBtnListener(InternalWindow doc) {
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
				window.name.setEditable(false);
				window.description.setEditable(false);
				window.obj.title = window.name.getText();
				window.obj.description = window.description.getText();
			} else {
				window.name.setEditable(true);
				window.description.setEditable(true);
			}
		}

	}

}
