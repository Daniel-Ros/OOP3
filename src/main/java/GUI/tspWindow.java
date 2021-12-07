package GUI;

import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.NodeData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class tspWindow extends JFrame implements ActionListener {
    DirectedWeightedGraphAlgorithms ga;

    JLabel label;
    JTextField text;
    JButton submit;

    Container window;

    public tspWindow(DirectedWeightedGraphAlgorithms graphAlgorithms, Container w){
        window = w;
        ga = graphAlgorithms;

        label = new JLabel("<html>Enter the cities in this format:<br/>id1,id2,id3..</html>");
        text = new JTextField();
        submit = new JButton("Submit");
        submit.addActionListener(this);
        submit.setMaximumSize(new Dimension(200,80));;
        submit.setPreferredSize(new Dimension(200,80));;
        //submit.setMaximumSize(new Dimension(200,80));;

        GridLayout layout = new GridLayout(1,3);
        setLayout(layout);
        add(label);
        add(text);
        add(submit);

    }


    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submit){
            Iterator<NodeData> it = ga.getGraph().nodeIter();
            it.forEachRemaining(new Consumer<NodeData>() {
                @Override
                public void accept(NodeData nodeData) {
                    nodeData.setTag(0);
                }
            });
            Iterator<EdgeData> eit = ga.getGraph().edgeIter();
            eit.forEachRemaining(new Consumer<EdgeData>() {
                @Override
                public void accept(EdgeData edgeData) {
                    edgeData.setTag(0);
                }
            });
            String s = text.getText();
            String[] keys = s.trim().split(",");
            List<NodeData> cites = new ArrayList<>();
            for(String c: keys){
                cites.add(ga.getGraph().getNode(Integer.valueOf(c)));
            }
            List<NodeData> r = ga.tsp(cites);
            if (r == null)
                return ;
            r.get(0).setTag(Color.red.getRGB());
            for (int i = 1; i < r.size(); i++) {
                r.get(i).setTag(Color.red.getRGB());
                EdgeData edge = ga.getGraph().getEdge(r.get(i - 1).getKey(), r.get(i).getKey());
                edge.setTag(Color.red.getRGB());
            }
            window.repaint();
        }
    }
}
