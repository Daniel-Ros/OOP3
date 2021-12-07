package GUI;

import api.DirectedWeightedGraphAlgorithms;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MenuBar extends JMenuBar implements ActionListener {

    JMenuItem load;
    JMenuItem save;
    JMenuItem exit;
    JSeparator sep;

    DirectedWeightedGraphAlgorithms ga;

    MenuBar(DirectedWeightedGraphAlgorithms graphAlgorithms){
        super();
        ga = graphAlgorithms;
        JMenu file = new JMenu("file");

        load = new JMenuItem("load");
        save = new JMenuItem("save");
        sep = new JSeparator();
        exit = new JMenuItem("exit");

        load.addActionListener(this);
        save.addActionListener(this);
        exit.addActionListener(this);

        file.add(load);
        file.add(save);
        file.add(sep);
        file.add(exit);

        add(file);

    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == exit){
            System.exit(0);
        }else if(e.getSource() == load){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            fileChooser.showOpenDialog(null);
            if(ga.load(fileChooser.getSelectedFile().getPath())){
                System.out.println("loaded");
                getTopLevelAncestor().repaint();
            }
        }else if(e.getSource() == save){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            fileChooser.showOpenDialog(null);
            if(ga.save(fileChooser.getSelectedFile().getPath())){
                System.out.println("saved");
                getTopLevelAncestor().repaint();
            }
        }
    }
}
