package GUI;

import api.NodeData;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class NodeWindow extends JFrame implements KeyListener {
    JLabel id;
    JLabel pos;
    JLabel tag;

    JPanel infoPanel;
    JLabel info;
    JTextField textField;

    NodeWindow(NodeData n){
        GridLayout layout = new GridLayout(4,1);

        setLayout(layout);
        id = new JLabel("id:" + n.getKey());
        pos = new JLabel("pos:" + n.getLocation().x() + "," + n.getLocation().y() + "," +n.getLocation().z());
        tag = new JLabel("tag:" + n.getTag());

        infoPanel = new JPanel();
        info = new JLabel("info:");
        textField = new JTextField(n.getInfo());
        infoPanel.add(info);
        infoPanel.add(textField);
        textField.setPreferredSize(new Dimension(300,30));
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                n.setInfo(textField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                n.setInfo(textField.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
        textField.addKeyListener(this);

        add(id);
        add(pos);
        add(tag);
        add(infoPanel);

        addKeyListener(this);
    }

    /**
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key typed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key pressed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            this.dispose();
        }
    }

    /**
     * Invoked when a key has been released.
     * See the class description for {@link KeyEvent} for a definition of
     * a key released event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
