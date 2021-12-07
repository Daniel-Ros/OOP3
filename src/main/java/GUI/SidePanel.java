package GUI;

import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.NodeData;
import org.w3c.dom.Node;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class SidePanel extends JPanel implements ActionListener,NodeSelectedListener {
    private int src;
    private int dest;
    private double weight;

    private JButton isConnected;
    private JButton shortestPath;
    private JButton shortestPathDist;
    private JButton connect;
    private JButton center;
    private JButton tsp;
    private JLabel status;
    private JLabel selectedNodes;
    private DirectedWeightedGraphAlgorithms ga;

    boolean isSelectingTspNodes;
    List<NodeData> tspNodes;

    SidePanel(DirectedWeightedGraphAlgorithms graphAlgorithms){

            ga = graphAlgorithms;

            status = new JLabel();
            selectedNodes = new JLabel();

        src = -1;
        dest = -1;
        weight=1;

            isConnected = new JButton("isConnected");
            shortestPath = new JButton("shortestPath");
            shortestPathDist = new JButton("shortestPathDist");
            connect = new JButton("connect");
            center = new JButton("center");
            tsp = new JButton("tsp");
            isConnected.addActionListener(this);
            shortestPath.addActionListener(this);
            shortestPathDist.addActionListener(this);
            connect.addActionListener(this);
            center.addActionListener(this);
            tsp.addActionListener(this);

            GridLayout layout = new GridLayout(800/50,1);
            layout.setVgap(5);
            setLayout(layout);
            this.add(isConnected);
            this.add(shortestPathDist);
            this.add(shortestPath);
            this.add(connect);
            this.add(center);
            this.add(tsp);
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
        JPanel srcDestPanel =new JPanel(new GridLayout(3,2));
        JPanel destPanel = new JPanel(new GridLayout(2,2));
        JPanel weightPanel = new JPanel(new GridLayout(2,2));

        JTextField srcField=createFilteredTextField(false);
        JTextField destField=createFilteredTextField(false);
        srcField.setColumns(5);
        destField.setColumns(5);
        srcDestPanel.add(new JLabel("Source: "));
        srcDestPanel.add(srcField);
        srcDestPanel.add(new JLabel("Destination: "));
        srcDestPanel.add(destField);
        srcDestPanel.add(new JLabel("Natural numbers Only")).setForeground(Color.red);

        JTextField destOnlyField=createFilteredTextField(false);
        destPanel.add(new JLabel("Destination: "));
        destOnlyField.setColumns(5);
        destPanel.add(destOnlyField);
        destPanel.add(new JLabel("Natural numbers Only")).setForeground(Color.red);

        JTextField weightField=createFilteredTextField(true);
        weightField.setColumns(10);
        weightPanel.add(new JLabel("Weight: "));
        weightPanel.add(weightField);
        weightPanel.add(new JLabel("Real numbers Only")).setForeground(Color.red);

        if (e.getSource() == isConnected) {
            status.setText("Is connected:" + String.valueOf(ga.isConnected()));
        } else if (e.getSource() == shortestPathDist) {
            if (src == -1 || dest == -1) {
                if(src==dest){
                    int res = JOptionPane.showConfirmDialog(null, srcDestPanel,
                            "Please Enter Source and Destination Values (Natural Numbers only)", JOptionPane.OK_CANCEL_OPTION);
                    if(res==JOptionPane.OK_OPTION){
                        String srcString = srcField.getText();
                        String destString = destField.getText();
                        if(srcString.isEmpty() || destString.isEmpty()){
                            JOptionPane.showMessageDialog(null,"Empty Text Field");
                            return;
                        }
                        src=Integer.parseInt(srcField.getText());
                        dest=Integer.parseInt(destField.getText());
                        if(ga.getGraph().getNode(src)==null || ga.getGraph().getNode(dest)==null){
                            src=-1;
                            dest=-1;
                            JOptionPane.showMessageDialog(null,"Invalid Vertex key");
                            return;
                        }
                    }else{
                        return;
                    }
                }else{
                    int res = JOptionPane.showConfirmDialog(null,destPanel,"Enter Destination (Natural Numbers only)",JOptionPane.OK_CANCEL_OPTION);
                    if(res==JOptionPane.OK_OPTION){
                        String destString = destOnlyField.getText();
                        if(destString.isEmpty()){
                            JOptionPane.showMessageDialog(null,"Empty Text Field");
                            return;
                        }
                        dest=Integer.parseInt(destString);
                        if(ga.getGraph().getNode(dest)==null){
                            src=-1;
                            dest=-1;
                            JOptionPane.showMessageDialog(null,"Invalid Vertex key");
                            return;
                        }
                    }
                }
            }
            status.setText("shortest path dist:" + String.valueOf(ga.shortestPathDist(src, dest)));
        } else if (e.getSource() == connect) {
            if (src == -1 || dest == -1) {
                if(src==dest){
                    int res = JOptionPane.showConfirmDialog(null, srcDestPanel,
                            "Please Enter Source and Destination Values (Natural Numbers only)", JOptionPane.OK_CANCEL_OPTION);
                    if(res==JOptionPane.OK_OPTION){
                        String srcString = srcField.getText();
                        String destString = destField.getText();
                        if(srcString.isEmpty() || destString.isEmpty()){
                            JOptionPane.showMessageDialog(null,"Empty Text Field");
                            return;
                        }
                        src=Integer.parseInt(srcField.getText());
                        dest=Integer.parseInt(destField.getText());
                        if(ga.getGraph().getNode(src)==null || ga.getGraph().getNode(dest)==null){
                            src=-1;
                            dest=-1;
                            JOptionPane.showMessageDialog(null,"Invalid Vertex key");
                            return;
                        }
                    }else{
                        return;
                    }
                }else{
                    int res = JOptionPane.showConfirmDialog(null,destPanel,"Enter Destination (Natural Numbers only)",JOptionPane.OK_CANCEL_OPTION);
                    if(res==JOptionPane.OK_OPTION){
                        String destString = destOnlyField.getText();
                        if(destString.isEmpty()){
                            JOptionPane.showMessageDialog(null,"Empty Text Field");
                            return;
                        }
                        dest=Integer.parseInt(destString);
                        if(ga.getGraph().getNode(dest)==null){
                            src=-1;
                            dest=-1;
                            JOptionPane.showMessageDialog(null,"Invalid Vertex key");
                            return;
                        }
                    }
                }
            }
            int res=JOptionPane.showConfirmDialog(null,weightPanel,"Enter Weight (Real Numbers only)",JOptionPane.OK_CANCEL_OPTION);
            if(res==JOptionPane.OK_OPTION){
                String weightString=weightField.getText();
                if(weightString.isEmpty()){
                    weight=1;
                    JOptionPane.showMessageDialog(null,"Didnt put any value generating automatic value of '1'");
                }else{
                    weight=Double.parseDouble(weightString);
                }
            }
            ga.getGraph().connect(src, dest, weight);
            src = -1;
            dest = -1;
            selectedNodes.setText("");
            getTopLevelAncestor().repaint();
        } else if (e.getSource() == shortestPath) {
            if (src == -1 || dest == -1) {
                if(src==dest){
                    int res = JOptionPane.showConfirmDialog(null, srcDestPanel,
                            "Please Enter Source and Destination Values (Natural Numbers only)", JOptionPane.OK_CANCEL_OPTION);
                    if(res==JOptionPane.OK_OPTION){
                        String srcString = srcField.getText();
                        String destString = destField.getText();
                        if(srcString.isEmpty() || destString.isEmpty()){
                            JOptionPane.showMessageDialog(null,"Empty Text Field");
                            return;
                        }
                        src=Integer.parseInt(srcField.getText());
                        dest=Integer.parseInt(destField.getText());
                        if(ga.getGraph().getNode(src)==null || ga.getGraph().getNode(dest)==null){
                            src=-1;
                            dest=-1;
                            JOptionPane.showMessageDialog(null,"Invalid Vertex key");
                            return;
                        }
                    }else{
                        return;
                    }
                }else{
                    int res = JOptionPane.showConfirmDialog(null,destPanel,"Enter Destination (Natural Numbers only)",JOptionPane.OK_CANCEL_OPTION);
                    if(res==JOptionPane.OK_OPTION){
                        String destString = destOnlyField.getText();
                        if(destString.isEmpty()){
                            JOptionPane.showMessageDialog(null,"Empty Text Field");
                            return;
                        }
                        dest=Integer.parseInt(destString);
                        if(ga.getGraph().getNode(dest)==null){
                            src=-1;
                            dest=-1;
                            JOptionPane.showMessageDialog(null,"Invalid Vertex key");
                            return;
                        }
                    }
                }
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
        }else if (e.getSource() == tsp) {
            if(!isSelectingTspNodes){
                status.setText("<html>Click on the wanted nodes<br/>When done, Click on the tsp button again</html>");
                tspNodes = new ArrayList<>();
                isSelectingTspNodes = true;
            }else {
                List<NodeData> r = ga.tsp(tspNodes);
                if (r == null)
                    return;
                r.get(0).setTag(Color.red.getRGB());
                for (int i = 1; i < r.size(); i++) {
                    r.get(i).setTag(Color.red.getRGB());
                    EdgeData edge = ga.getGraph().getEdge(r.get(i - 1).getKey(), r.get(i).getKey());
                    edge.setTag(Color.red.getRGB());
                }
                getTopLevelAncestor().repaint();
                isSelectingTspNodes = false;
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
        if(isSelectingTspNodes){
            if(node != -1){
                tspNodes.add(ga.getGraph().getNode(node));
                ga.getGraph().getNode(node).setTag(Color.red.getRGB());
                getTopLevelAncestor().repaint();
            }
            return;
        }
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

    private JTextField createFilteredTextField(boolean flag){
        JTextField field =new JTextField();
        AbstractDocument doc =(AbstractDocument) field.getDocument();
        final int maxCharacters =1000;
        doc.setDocumentFilter(new DocumentFilter(){
            public void replace(FilterBypass fb, int offset, int len, String str, AttributeSet a) throws BadLocationException {
                String regexType;
                if(flag){
                    regexType="^[1]?[0-9]{1,9999999}([.][0-9]{0,7})?$";
                }else{
                    regexType="^[1]?[0-9]{1,9999999}$";
                }
                String text= fb.getDocument().getText(0,fb.getDocument().getLength());
                text+=str;
                if((fb.getDocument().getLength()+str.length()-len)<=maxCharacters  && text.matches(regexType)){
                    super.replace(fb, offset, len, str, a);
                }else{
                    Toolkit.getDefaultToolkit().beep();
                }
            }
            public void insertString(FilterBypass fb,int offset,String str,AttributeSet a) throws BadLocationException {
                String regexType;
                if(flag){
                    regexType="^[1]?[0-9]{1,9999999}([.][0-9]{0,7})?$";
                }else{
                    regexType="^[1]?[0-9]{1,9999999}$";
                }
                String text=fb.getDocument().getText(0,fb.getDocument().getLength());
                text += str;
                if ((fb.getDocument().getLength() + str.length()) <= maxCharacters
                        && text.matches(regexType)) {
                    super.insertString(fb, offset, str, a);
                    }else{
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });
        return field;
    }

}