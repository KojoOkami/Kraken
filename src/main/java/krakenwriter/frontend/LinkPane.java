package krakenwriter.frontend;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

public class LinkPane extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3459577607052968387L;

	private Point startPoint;
	private Point endPoint;

	public LinkPane(JInternalFrame parent, JInternalFrame child) {

		setOpaque(false);

		Point p1 = parent.getLocation();
		Point p2 = child.getLocation();

		startPoint = new Point();
		endPoint = new Point();

		Point from = new Point();
		Point to = new Point();

		if (p1.x < p2.x) {
			from.x = p1.x + (parent.getWidth() / 2);
			to.x = p2.x + (child.getWidth() / 2);
		} else {
			from.x = p2.x + (child.getWidth() / 2);
			to.x = p1.x + (parent.getWidth() / 2);
			startPoint.x = p2.x;
		}
		if (p1.y < p2.y) {
			from.y = p1.y + (parent.getHeight() / 2);
			to.y = p2.y + (child.getHeight() / 2);

		} else {
			from.y = p2.y + (child.getHeight() / 2);
			to.y = p1.y + (parent.getHeight() / 2);
		}

		int width = Math.max(1, to.x - from.x);
		int height = Math.max(1, to.y - from.y);

		setLocation(from);
		setSize(width, height);

		System.out.println(getBounds());

		if (p1.x < p2.x) {
			startPoint.x = 0;
			endPoint.x = getWidth();
		} else {
			startPoint.x = getWidth();
			endPoint.x = 0;
		}
		if (p1.y < p2.y) {
			startPoint.y = 0;
			endPoint.y = getHeight();
		} else {
			startPoint.y = getHeight();
			endPoint.y = 0;
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		g2d.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
		g2d.dispose();
	}
}
