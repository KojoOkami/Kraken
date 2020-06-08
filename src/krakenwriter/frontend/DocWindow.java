package krakenwriter.frontend;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.LineBorder;

import krakenwriter.backend.ExternalDocument;
import krakenwriter.backend.Text;

public class DocWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3578353296691731958L;

	private Text text;
	
	private JPanel pane;
	private JTextField titleField;
	private JEditorPane textArea;
	
	public DocWindow(ExternalDocument doc) {
		this.text = doc.getInternal().getInternalText();
		
		setJMenuBar(createMenuBar());
		initComponents(doc.title);
	}
	
	private void initComponents(String title) {
        int inset = 50;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(inset, inset,
                screenSize.width - inset * 2,
                screenSize.height - inset * 2);
        
        pane = new JPanel();
        pane.setBackground(Color.LIGHT_GRAY);

        pane.setLayout(new GridBagLayout());
        GridBagConstraints c;
        
        titleField = new JTextField();
        titleField.setText(title);
        titleField.setHorizontalAlignment(JTextField.CENTER);
        titleField.setBackground(null);
        titleField.setBorder(new LineBorder(Color.BLACK));
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = GridBagConstraints.NONE;
        c.ipadx = 100;
        c.ipady = 3;
        c.insets = new Insets(0, 0, 0, 0);
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.weightx = 5;
        c.weighty = 0;
        pane.add(titleField, c);
        
        textArea = new JEditorPane();
        textArea.setContentType("text/html");
        textArea.setText(text.getRawText());                   
        textArea.setBackground(Color.WHITE);
        textArea.setBorder(new LineBorder(Color.BLACK));
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.fill = GridBagConstraints.BOTH;
        c.ipadx = 3;
        c.ipady = 3;
        c.insets = new Insets(0, 0, 10, 0);
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 10;
        c.weighty = 1;
        pane.add(textArea, c);
        
        setBackground(Color.LIGHT_GRAY);
        add(pane);
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
	
	private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        newMenu(menuBar,
                "File",
                KeyEvent.VK_F,
                new String[]{"Save Document"},
                new int[]{KeyEvent.VK_S});

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
            menuItem.addActionListener(new MenuListener(this));
            menu.add(menuItem);
        }
    }
    
    private void saveDoc() {
    	String text = textArea.getText();
    	text = text.substring(text.indexOf("<body>")+8, text.lastIndexOf("</body>")).replaceAll("&lt;", "<").replaceAll("&gt;", ">");
    	this.text.setRawText(text);
    	textArea.setText(text);
    }
    
    private class MenuListener implements ActionListener {

    	private DocWindow window;
    	
    	public MenuListener(DocWindow window) {
    		this.window = window;
		}
    	
        @Override
        public void actionPerformed(ActionEvent e) {
        	if ("Save Document".equals(e.getActionCommand())) { //New Project
            	window.saveDoc();
            }
        }
    }
	
}
