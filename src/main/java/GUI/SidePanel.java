package GUI;

import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.NodeData;
import org.w3c.dom.Node;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class SidePanel extends JPanel implements ActionListener,NodeSelectedListener {
    private int src;
    private int dest;

    private JButton isConnected;
    private JButton shortestPath;
    private JButton shortestPathDist;
    private JButton connect;
    private JButton center;
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
            shortestPathDist = new JButton("shortestPathDist");
            connect = new JButton("connect");
            center = new JButton("center");
            isConnected.addActionListener(this);
            shortestPath.addActionListener(this);
            shortestPathDist.addActionListener(this);
            connect.addActionListener(this);
            center.addActionListener(this);

            GridLayout layout = new GridLayout(800/50,1);
            layout.setVgap(5);
            setLayout(layout);
            this.add(isConnected);
            this.add(shortestPathDist);
            this.add(shortestPath);
            this.add(connect);
            this.add(center);
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
        if (e.getSource() == isConnected) {
            status.setText("Is connected:" + String.valueOf(ga.isConnected()));
        } else if (e.getSource() == shortestPathDist) {
            if (src == -1 || dest == -1) {
                return;
            }
            status.setText("shortest path dist:" + String.valueOf(ga.shortestPathDist(src, dest)));
        } else if (e.getSource() == connect) {
            if (src == -1 || dest == -1) {
                return;
            }
            ga.getGraph().connect(src, dest, 1);
            src = -1;
            dest = -1;
            selectedNodes.setText("");
            getTopLevelAncestor().repaint();
        } else if (e.getSource() == shortestPath) {
            if (src == -1 || dest == -1) {
                return;
            }
            List<NodeData> nodes = ga.shortestPath(src, dest);
            if (nodes == null)
                return ;
            nodes.get(0).setTag(Color.red.getRGB());
            for (int i = 1; i < nodes.size(); i++) {
                nodes.get(i).setTag(Color.red.getRGB());
                EdgeData edge = ga.getGraph().getEdge(nodes.get(i - 1).getKey(), nodes.get(i).getKey());
                edge.setTag(Color.red.getRGB());
            }
            getTopLevelAncestor().repaint();
        }
        else if (e.getSource() == center) {
            NodeData n = ga.center();
            if(n != null){
                n.setTag(Color.red.getRGB());
                status.setText("Center is:" + n.getKey());
                getTopLevelAncestor().repaint();
            }
        }
    }

    @Override
    public void selectNode(int node) {
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

        getTopLevelAncestor().repaint();

        if(node == -1){
            src = -1;
            dest = -1;
            selectedNodes.setText("");
            return;
        }else if(src == -1){
            src = node;
            selectedNodes.setText("src:" + String.valueOf(node));
        }else {
            if(node == src){
                return;
            }
            dest = node;
            selectedNodes.setText("<html>src:" + String.valueOf(src)  + "<br/>dest:" + String.valueOf(dest) + "</html>");
        }
    }
}
