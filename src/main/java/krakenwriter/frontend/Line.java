package krakenwriter.frontend;

import java.awt.Graphics;

import javax.swing.JPanel;

import krakenwriter.backend.ConnectionLine;

public class Line extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8491313386277193078L;
	
	ConnectionLine line;

	public Line(ConnectionLine line) {
		this.line = line;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawLine(line.getParentObj().x(), line.getParentObj().y(), line.getChildObj().x(), line.getChildObj().y());
	}

}
