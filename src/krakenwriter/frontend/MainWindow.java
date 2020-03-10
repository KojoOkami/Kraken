package krakenwriter.frontend;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import krakenwriter.backend.VisualObject;
import krakenwriter.backend.VisualSpace;

/**
 *
 * @author Connor
 */

public class MainWindow extends JFrame implements ActionListener {

    JDesktopPane desktop;

    public MainWindow() {
        super(VisualSpace.projectName);
        initComponents();
    }

    private void initComponents() {
        int inset = 50;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(inset, inset,
                screenSize.width  - inset*2,
                screenSize.height - inset*2);

        desktop = new JDesktopPane();
        setContentPane(desktop);
        setJMenuBar(createMenuBar());

        setVisible(true);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        newMenu(menuBar, "Project", KeyEvent.VK_P, new String[]{"New Project", "Open Project", "Delete Project"}, new int[]{-1, KeyEvent.VK_O, -1});
        newMenu(menuBar, "Edit", -1, new String[]{"Undo", "Redo"}, new int[]{KeyEvent.VK_Z, KeyEvent.VK_Y});
        newMenu(menuBar, "Objects", KeyEvent.VK_D, new String[]{"New Document", "New Label", "Edit Object", "Delete Object"}, new int[]{KeyEvent.VK_N, -1, KeyEvent.VK_E, KeyEvent.VK_DELETE});
        newMenu(menuBar, "Windows", KeyEvent.VK_W, new String[]{"Navigator"}, new int[]{KeyEvent.VK_F});

        return menuBar;
    }
    
    private void newMenu(JMenuBar menuBar, String menuName, int keyEvent, String[] menuItems, int[] keyEvents) {
        //Set up the menu.
        JMenu menu = new JMenu(menuName);
        if (keyEvent != -1) {
            menu.setMnemonic(keyEvent);
        }
        menuBar.add(menu);

        for (int i = 0; i < Math.min(menuItems.length, keyEvents.length); i++ ) {
            JMenuItem menuItem = new JMenuItem(menuItems[i]);
            if (keyEvents[i] != -1) {
                menuItem.setMnemonic(keyEvents[i]);
                menuItem.setAccelerator(KeyStroke.getKeyStroke(
                        keyEvents[i], ActionEvent.CTRL_MASK));
            }
            menuItem.setActionCommand(menuItems[i]);
            menuItem.addActionListener(this);
            menu.add(menuItem);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
    protected void createFrame(VisualObject object) {
        InternalWindow frame = new InternalWindow(object);
        desktop.add(frame);
        try {
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {}
    }
    
    protected void quit() {
        System.exit(0);
    }
}
