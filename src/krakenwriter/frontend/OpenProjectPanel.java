package krakenwriter.frontend;

import java.io.File;
import javax.swing.*;
import krakenwriter.backend.ComputerFile;

public class OpenProjectPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2245300596968586661L;

	public static File createAndShowGui() {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setCurrentDirectory(new File(ComputerFile.PROJECT_PATH));
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
        	return chooser.getSelectedFile();
        }
        return null;
	}
}