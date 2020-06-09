package krakenwriter.frontend;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import krakenwriter.backend.ExternalDocument;
import krakenwriter.backend.Label;
import krakenwriter.backend.VisualObject;

/**
 *
 * @author Connor
 */
public class MainWindow extends JFrame {

    /**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 983097105017369062L;
	
	JDesktopPane desktop;

    public MainWindow() {
        super(VisualSpace.projectName);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.initComponents();
        
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
            	super.componentResized(e);
                VisualSpace.setDimensions(((JFrame)e.getSource()).getWidth(), ((JFrame)e.getSource()).getHeight());
            }
            @Override
            public void componentMoved(ComponentEvent e) {
            	super.componentMoved(e);
            	VisualSpace.setPos(((JFrame)e.getSource()).getX(), ((JFrame)e.getSource()).getY());
            }
        });
    }

    private void initComponents() {
        int inset = 50;
        setBounds(inset, inset,
                VisualSpace.width(),
                VisualSpace.height());

        desktop = new JDesktopPane() {
			private static final long serialVersionUID = 8280329506314401645L;
			
			@Override
			public void paint(Graphics g) {
				super.paint(g);
				Graphics2D realm1 = (Graphics2D) g;
            	Iterator<VisualObject> i = VisualSpace.getObjectIterator();
            	while (i.hasNext()) {
            		VisualObject obj = i.next();
            		ArrayList<VisualObject> children = obj.getChildren();
            		if (children != null && children.size() > 0 && children.get(0) != null) {
        	    		for (VisualObject o : children) {
        	    			realm1.drawLine(obj.x() + obj.width(), obj.y() + obj.height(), o.x() + o.width(), o.y());
        	    		}
            		}
            	}
			}
        };
        //scroll = new JScrollPane(desktop);
        setContentPane(desktop); //setContentPane(scroll);
        setJMenuBar(createMenuBar());

        setVisible(true);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        newMenu(menuBar,
                "Project",
                KeyEvent.VK_P,
                new String[]{"Save Project", "New Project", "Open Project", "Delete Project"},
                new int[]{KeyEvent.VK_S, -1, KeyEvent.VK_O, -1});

        newMenu(menuBar,
                "Objects",
                KeyEvent.VK_D,
                new String[]{"New Document", "New Label"},
                new int[]{KeyEvent.VK_N, -1});

        return menuBar;
    }

    private void newMenu(JMenuBar menuBar, String menuName, int keyEvent, String[] menuItems, int[] keyEvents) {
        JMenu menu = new JMenu(menuName);
        if (keyEvent != -1) {
            menu.setMnemonic(keyEvent);
        }
        menuBar.add(menu);

        for (int i = 0; i < Math.min(menuItems.length, keyEvents.length); i++) {
            JMenuItem menuItem = new JMenuItem(menuItems[i]);
            if (keyEvents[i] != -1) {
                menuItem.setMnemonic(keyEvents[i]);
                menuItem.setAccelerator(KeyStroke.getKeyStroke(
                        keyEvents[i], ActionEvent.CTRL_MASK));
            }
            menuItem.setActionCommand(menuItems[i]);
            menuItem.addActionListener(new MenuListener());
            menu.add(menuItem);
        }
    }

    protected void createFrame(VisualObject object) {
        InternalWindow frame = new InternalWindow(object);
        frame.setLocation(MouseInfo.getPointerInfo().getLocation().x - getLocationOnScreen().x - 5, MouseInfo.getPointerInfo().getLocation().y - getLocationOnScreen().y - 52);
        frame.setVisible(true);
        desktop.add(frame);
        try {
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {}
    }
    
    private class MenuListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
        	if ("Save Project".equals(e.getActionCommand())) { //New Project
            	VisualSpace.saveProject();
            } else if ("New Project".equals(e.getActionCommand())) { //New Project
            	String name = NewProjectPanel.createAndShowGui();
            	if (name.equals("")) {
            		System.out.println("Operation Canceled or No Text Entered");
            	} else {
            		VisualSpace.createNewProject(name);
            	}
            } else if ("Open Project".equals(e.getActionCommand())) { //Open Project
            	File project = OpenProjectPanel.createAndShowGui();
            	if (project != null) {
                	VisualSpace.loadProject(project.getName());
            	} else {
            		System.out.println("Operation Canceled");
            	}
            } else if ("Delete Project".equals(e.getActionCommand())) { //Delete Project
            	if (DeleteProjectPanel.createAndShowGui()) {
            		VisualSpace.deleteProject();
            	}
            } else if ("New Document".equals(e.getActionCommand())) { //New Object
                createFrame(VisualSpace.createNewObject(new ExternalDocument()));
            } else if ("New Label".equals(e.getActionCommand())) { //New Object
                createFrame(VisualSpace.createNewObject(new Label()));
            }
        }
    }
}
