package krakenwriter.frontend;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import krakenwriter.backend.ComputerFile;

public class InitWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7829124276661390602L;

	JPanel pane;
	
	JButton createNewButton;
	JButton[] projectButtons;
	
	public InitWindow() {
		super("Select Project");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
		setMinimumSize(new Dimension(300, 125));
		setResizable(true);
		setExtendedState(JFrame.NORMAL);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int)(screenSize.getWidth()/2)-150, (int)(screenSize.getHeight()/2)-63);
        
        this.initComponents();
	}

	private void initComponents() {
		pane = new JPanel();
        pane.setBackground(Color.LIGHT_GRAY);

        pane.setLayout(new GridBagLayout());
		GridBagConstraints c;
		
		createNewButton = new JButton("Create New Project");
		createNewButton.addActionListener(new NewProjectListener());
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = 0;
		c.ipady = 0;
		c.insets = new Insets(0, 0, 0, 0);
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 0;
		c.weighty = 0;
		pane.add(createNewButton, c);
		
		File folder = new File(ComputerFile.PROJECT_PATH);
		File[] projects = folder.listFiles();
		projectButtons = new JButton[projects.length];
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = 0;
		c.ipady = 0;
		c.insets = new Insets(0, 0, 0, 0);
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 0;
		c.weighty = 0;
		for (int i = 0; i < projects.length; i++) {
			projectButtons[i] = new JButton(projects[i].getName().replaceAll("_", " "));
			projectButtons[i].addActionListener(new OpenProjectListner());
			c.gridy = i+1;
			pane.add(projectButtons[i], c);
		}
		
		setSize(300, projects.length*50 + 50);
		setBackground(Color.LIGHT_GRAY);
		add(pane);
        setVisible(true);
	}
	
	public class OpenProjectListner implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			VisualSpace.loadProject(((JButton)e.getSource()).getText().replaceAll(" ", "_"));
			VisualSpace.iw.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			VisualSpace.iw.dispose();
			VisualSpace.iw = null;
		}
		
	}
	
	public class NewProjectListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String name = NewProjectPanel.createAndShowGui();
			if (name != null) {
				VisualSpace.createNewProject(NewProjectPanel.createAndShowGui());
				VisualSpace.iw.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				VisualSpace.iw.dispose();
				VisualSpace.iw = null;
			}
		}
		
	}
}
