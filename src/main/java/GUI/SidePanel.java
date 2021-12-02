package GUI;

import api.DirectedWeightedGraphAlgorithms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SidePanel extends JPanel implements ActionListener,NodeSelectedListener {
    private int src;
    private int dest;

    private JButton isConnected;
    private JButton shortestPath;
    private JLabel status;
    private JLabel selectedNodes;
    private DirectedWeightedGraphAlgorithms ga;

        SidePanel(DirectedWeightedGraphAlgorithms graphAlgorithms){
            ga = graphAlgorithms;

            status = new JLabel();
            selectedNodes = new JLabel();

            src = -1;
            dest = -1;

            isConnected = new JButton("isConnected");
            shortestPath = new JButton("shortestPath");
            isConnected.addActionListener(this);
            shortestPath.addActionListener(this);

            GridLayout layout = new GridLayout(800/50,1);
            layout.setVgap(5);
            setLayout(layout);
            this.add(isConnected);
            this.add(shortestPath);
            this.add(selectedNodes);
            this.add(status);

        }


    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == isConnected){
            status.setText("Is connected:" + String.valueOf(ga.isConnected()));
        }else if(e.getSource() == shortestPath){
            status.setText("shortest path dist:" + String.valueOf(ga.shortestPathDist(src,dest)));
        }
    }

    @Override
    public void selectNode(int node) {
        if(node == -1){
            src = -1;
            dest = -1;
            selectedNodes.setText("");
            return;
        }else if(src == -1){
            src = node;
            selectedNodes.setText("src:" + String.valueOf(node));
        }else {
            dest = node;
            selectedNodes.setText("<html>src:" + String.valueOf(src)  + "<br/>dest:" + String.valueOf(dest) + "</html>");
        }
    }
}
