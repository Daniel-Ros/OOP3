package GUI;

import api.DirectedWeightedGraphAlgorithms;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SidePanel extends JPanel implements ActionListener {


    JButton isConnected;
    DirectedWeightedGraphAlgorithms ga;

        SidePanel(DirectedWeightedGraphAlgorithms graphAlgorithms){
            ga = graphAlgorithms;

            isConnected = new JButton("isConnected");
            isConnected.addActionListener(this);
            this.add(isConnected);
        }


    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == isConnected){
            System.out.println(ga.isConnected());
        }
    }
}
