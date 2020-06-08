package krakenwriter.frontend;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.*;

public class DeleteProjectPanel extends JPanel {
	/**
	* 
	*/
	private static final long serialVersionUID = 6356929686225612099L;

	JTextField textField;

	public DeleteProjectPanel() {
		setLayout(new GridBagLayout());
		
		setBorder(BorderFactory.createTitledBorder("Confirm Project Deletion"));
	}

	public static GridBagConstraints createGbc(int x, int y) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.weightx = 1.0;
		gbc.weighty = gbc.weightx;
		if (x == 0) {
			gbc.anchor = GridBagConstraints.LINE_START;
			gbc.fill = GridBagConstraints.BOTH;
			gbc.insets = new Insets(3, 3, 3, 8);
		} else {
			gbc.anchor = GridBagConstraints.LINE_END;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.insets = new Insets(3, 3, 3, 3);
		}
		return gbc;
	}

	public static boolean createAndShowGui() {
		DeleteProjectPanel mainPanel = new DeleteProjectPanel();

		int optionType = JOptionPane.DEFAULT_OPTION;
		int messageType = JOptionPane.PLAIN_MESSAGE;
		Icon icon = null;
		String[] options = { "Confirm", "Cancel" };
		Object initialValue = options[0];
		int reply = JOptionPane.showOptionDialog(null, mainPanel, "New Project", optionType, messageType, icon,
				options, initialValue);
		
		return reply == 0;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				System.out.println(createAndShowGui());
			}
		});
	}
}