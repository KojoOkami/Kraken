package krakenwriter.backend.krakenjava;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.accessibility.Accessible;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Connor
 */

public class JEditableLabel extends JComponent implements SwingConstants, Accessible, MouseListener, KeyListener {

    JLabel label;
    JTextField field;
    
    private boolean firstClick;

    public JEditableLabel() {
        this.label = new JLabel();
        this.field = new JTextField();
        this.firstClick = false;
    }
    
    public JEditableLabel(String text) {
        this.label = new JLabel(text);
        this.field = new JTextField(text);
        this.firstClick = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (firstClick) {
            changeToField();
        } else {
            firstClick = true;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 10) {
            changeToLabel();
        }
    }

    private void changeToField() {
    }
    
    private void changeToLabel() {
    }
    
}
