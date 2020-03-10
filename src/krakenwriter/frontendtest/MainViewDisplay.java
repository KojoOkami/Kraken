package krakenwriter.frontendtest;

import krakenwriter.backend.VisualSpace;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class MainViewDisplay extends JFrame {
    //Instance variable to reference everything in backend
    BackendModelSetup theBackendModel;

    JDesktopPane desktop;

    //Constructor
    public MainViewDisplay(BackendModelSetup aBackend) {
        super(VisualSpace.projectName);
        this.theBackendModel = aBackend;
        this.initComponents();
    }


    private void initComponents() {

        //Make the big window be indented 50 pixels from each edge of the screen.
        int inset = 50;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(inset, inset,
                screenSize.width  - inset*2,
                screenSize.height - inset*2);

        //Set up the GUI.
        desktop = new JDesktopPane(); //a specialized layered pane
        setContentPane(desktop);
        setJMenuBar(createMenuBar());

        setVisible(true);
    }

    protected JMenuBar createMenuBar() {
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
            menuItem.addActionListener(ModelsAndViewsController);
            menu.add(menuItem);
        }
    }

    //Create a new internal frame.
    protected void createFrame(String object) {
        System.out.print("NewFrame");
        KrakenInternalFrame frame = new KrakenInternalFrame(VisualSpace.createNewObject(object));
        frame.setVisible(true); //necessary as of 1.3
        desktop.add(frame);
        try {
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {}
    }

    //Quit the application.
    protected void quit() {
        System.exit(0);
    }
}
