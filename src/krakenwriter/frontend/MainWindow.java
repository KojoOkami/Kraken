package krakenwriter.frontend;

import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import krakenwriter.backend.ExternalDocument;
import krakenwriter.backend.Label;
import krakenwriter.backend.VisualObject;
import krakenwriter.backend.VisualSpace;

/**
 *
 * @author Connor
 */
public class MainWindow extends JFrame {

    JDesktopPane desktop;
    //JScrollPane scroll;

    public MainWindow() {
        super(VisualSpace.projectName);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.initComponents();
    }

    private void initComponents() {
        int inset = 50;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(inset, inset,
                screenSize.width - inset * 2,
                screenSize.height - inset * 2);

        desktop = new JDesktopPane();
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
                new String[]{"New Project", "Open Project", "Delete Project"},
                new int[]{-1, KeyEvent.VK_O, -1});

        newMenu(menuBar,
                "Edit",
                -1,
                new String[]{"Undo", "Redo"},
                new int[]{KeyEvent.VK_Z, KeyEvent.VK_Y});

        newMenu(menuBar,
                "Objects",
                KeyEvent.VK_D,
                new String[]{"New Document", "New Label", "Edit Object", "Delete Object"},
                new int[]{KeyEvent.VK_N, -1, KeyEvent.VK_E, KeyEvent.VK_DELETE});

        newMenu(menuBar,
                "Windows",
                KeyEvent.VK_W,
                new String[]{"Navigator"},
                new int[]{KeyEvent.VK_F});

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
        } catch (java.beans.PropertyVetoException e) {
        }
    }
    
    private class MenuListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if ("New Project".equals(e.getActionCommand())) { //New Project

            } else if ("Open Project".equals(e.getActionCommand())) { //Open Project

            } else if ("Delete Project".equals(e.getActionCommand())) { //Delete Project

            } else if ("Undo".equals(e.getActionCommand())) { //Undo

            } else if ("Redo".equals(e.getActionCommand())) { //Redo

            } else if ("New Document".equals(e.getActionCommand())) { //New Object
                createFrame(VisualSpace.createNewObject(new ExternalDocument()));
            } else if ("New Label".equals(e.getActionCommand())) { //New Object
                createFrame(VisualSpace.createNewObject(new Label()));
            } else if ("Edit Object".equals(e.getActionCommand())) { //Edit Object

            } else if ("Delete Object".equals(e.getActionCommand())) { //Delete Object

            } else if ("Navigator".equals(e.getActionCommand())) { //Navigator

            }
        }
    }
    
}
