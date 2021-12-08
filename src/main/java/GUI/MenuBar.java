package GUI;

import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.NodeData;
import implentations.GeoLocationImpl;
import implentations.NodeDataImpl;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

//TODO: add graph menu (edit node/edge// add node/edge // delete node/edge )
//TODO: add an about menu
public class MenuBar extends JMenuBar implements ActionListener {

    JMenuItem load;
    JMenuItem save;
    JMenuItem exit;
    JMenuItem tsp;
    JSeparator sep;
    JMenuItem shortestPathDist;
    JMenuItem shortestPath;
    JMenuItem connect;
    JMenuItem isConnected;
    JMenuItem center;
    JMenuItem addVertex;
    JMenuItem deleteVertex;
    JMenuItem addEdge;
    JMenuItem deleteEdge;

    DirectedWeightedGraphAlgorithms ga;

    MenuBar(DirectedWeightedGraphAlgorithms graphAlgorithms){
        super();
        ga = graphAlgorithms;
        JMenu file = new JMenu("file");
        JMenu algo = new JMenu("algo");
        JMenu graph = new JMenu("graph");
        JMenu edges = new JMenu("Edges");
        JMenu vertex = new JMenu("Vertex");

        load = new JMenuItem("load");
        save = new JMenuItem("save");
        sep = new JSeparator();
        exit = new JMenuItem("exit");

        addVertex = new JMenuItem("Add");
        deleteVertex = new JMenuItem("Delete");
        addEdge = new JMenuItem("Add");
        deleteEdge = new JMenuItem("Delete");
        tsp = new JMenuItem("tsp");
        shortestPathDist = new JMenuItem("ShortestPathDist");
        shortestPath = new JMenuItem("ShortestPath");
        connect = new JMenuItem("Connect");
        isConnected = new JMenuItem("Is Connected");
        center = new JMenuItem("Center");

        addVertex.addActionListener(this);
        deleteVertex.addActionListener(this);
        addEdge.addActionListener(this);
        deleteEdge.addActionListener(this);
        load.addActionListener(this);
        save.addActionListener(this);
        exit.addActionListener(this);
        tsp.addActionListener(this);
        shortestPathDist.addActionListener(this);
        shortestPath.addActionListener(this);
        connect.addActionListener(this);
        isConnected.addActionListener(this);
        center.addActionListener(this);

        file.add(load);
        file.add(save);
        file.add(sep);
        file.add(exit);

        algo.add(tsp);
        algo.add(shortestPathDist);
        algo.add(shortestPath);
        algo.add(connect);
        algo.add(isConnected);
        algo.add(center);

        vertex.add(addVertex);
        vertex.add(deleteVertex);
        edges.add(addEdge);
        edges.add(deleteEdge);

        graph.add(vertex);
        graph.add(edges);

        add(file);
        add(algo);
        add(graph);
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit) {
            System.exit(0);
        } else if (e.getSource() == load) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            fileChooser.showOpenDialog(null);
            if (ga.load(fileChooser.getSelectedFile().getPath())) {
                System.out.println("loaded");
                getTopLevelAncestor().repaint();
            }
        } else if (e.getSource() == save) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            fileChooser.showOpenDialog(null);
            if (ga.save(fileChooser.getSelectedFile().getPath())) {
                System.out.println("saved");
                getTopLevelAncestor().repaint();
            }
        } else if (e.getSource() == tsp) {
            tspWindow window = new tspWindow(ga, getTopLevelAncestor());
            window.setSize(300, 100);
            window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            window.setVisible(true);
        } else if (e.getSource() == isConnected) {
            boolean connection = ga.isConnected();
            String message = Boolean.toString(connection);
            JOptionPane.showMessageDialog(null, message);
        } else if (e.getSource() == connect || e.getSource() == shortestPath || e.getSource() == shortestPathDist) {
            int src = -1;
            int dest = -1;
            double weight=1;
            JPanel srcDestPanel = new JPanel(new GridLayout(3, 2));
            JPanel destPanel = new JPanel(new GridLayout(2, 2));
            JPanel weightPanel = new JPanel(new GridLayout(2, 2));

            JTextField srcField = createFilteredTextField(false);
            JTextField destField = createFilteredTextField(false);
            srcField.setColumns(5);
            destField.setColumns(5);
            srcDestPanel.add(new JLabel("Source: "));
            srcDestPanel.add(srcField);
            srcDestPanel.add(new JLabel("Destination: "));
            srcDestPanel.add(destField);
            srcDestPanel.add(new JLabel("Natural numbers Only")).setForeground(Color.red);

            JTextField destOnlyField = createFilteredTextField(false);
            destPanel.add(new JLabel("Destination: "));
            destOnlyField.setColumns(5);
            destPanel.add(destOnlyField);
            destPanel.add(new JLabel("Natural numbers Only")).setForeground(Color.red);

            JTextField weightField = createFilteredTextField(true);
            weightField.setColumns(10);
            weightPanel.add(new JLabel("Weight: "));
            weightPanel.add(weightField);
            weightPanel.add(new JLabel("Real numbers Only")).setForeground(Color.red);

            if (e.getSource() == connect) {
                int res = JOptionPane.showConfirmDialog(null, srcDestPanel,
                        "Please Enter Source and Destination Values (Natural Numbers only)", JOptionPane.OK_CANCEL_OPTION);
                if (res == JOptionPane.OK_OPTION) {
                    String srcString = srcField.getText();
                    String destString = destField.getText();
                    if (srcString.isEmpty() || destString.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Empty Text Field");
                        return;
                    }
                    src = Integer.parseInt(srcField.getText());
                    dest = Integer.parseInt(destField.getText());
                    if (ga.getGraph().getNode(src) == null || ga.getGraph().getNode(dest) == null) {
                        JOptionPane.showMessageDialog(null, "Invalid Vertex key");
                    }
                }
                res=JOptionPane.showConfirmDialog(null,weightPanel,"Enter Weight (Real Numbers only)",JOptionPane.OK_CANCEL_OPTION);
                if(res==JOptionPane.OK_OPTION){
                    String weightString=weightField.getText();
                    if(weightString.isEmpty()){
                        JOptionPane.showMessageDialog(null,"Didnt put any value generating automatic value of '1'");
                    }else{
                        weight=Double.parseDouble(weightString);
                    }
                }
                ga.getGraph().connect(src, dest, weight);
                getTopLevelAncestor().repaint();
            }else if(e.getSource()==shortestPath) {
                int res = JOptionPane.showConfirmDialog(null, srcDestPanel,
                        "Please Enter Source and Destination Values (Natural Numbers only)", JOptionPane.OK_CANCEL_OPTION);
                if (res == JOptionPane.OK_OPTION) {
                    String srcString = srcField.getText();
                    String destString = destField.getText();
                    if (srcString.isEmpty() || destString.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Empty Text Field");
                        return;
                    }
                    src = Integer.parseInt(srcField.getText());
                    dest = Integer.parseInt(destField.getText());
                    if (ga.getGraph().getNode(src) == null || ga.getGraph().getNode(dest) == null) {
                        JOptionPane.showMessageDialog(null, "Invalid Vertex key");
                        return;
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
            }else{
                int res = JOptionPane.showConfirmDialog(null, srcDestPanel,
                        "Please Enter Source and Destination Values (Natural Numbers only)", JOptionPane.OK_CANCEL_OPTION);
                if (res == JOptionPane.OK_OPTION) {
                    String srcString = srcField.getText();
                    String destString = destField.getText();
                    if (srcString.isEmpty() || destString.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Empty Text Field");
                        return;
                    }
                    src = Integer.parseInt(srcField.getText());
                    dest = Integer.parseInt(destField.getText());
                    if (ga.getGraph().getNode(src) == null || ga.getGraph().getNode(dest) == null) {
                        JOptionPane.showMessageDialog(null, "Invalid Vertex key");
                        return;
                    }
                }
                String shortestDistAnswer="<html>shortest path dist: <br/>" + String.valueOf(ga.shortestPathDist(src, dest))+" </html>";
                JOptionPane.showMessageDialog(null,shortestDistAnswer);
            }
        }else if(e.getSource()==center){
            NodeData n = ga.center();
            if(n != null){
                n.setTag(Color.red.getRGB());
                String cen="<html>Center is:<br/>" + n.getKey()+" </html>";
                JOptionPane.showMessageDialog(null,cen);
                getTopLevelAncestor().repaint();
            }
        }else if(e.getSource()==addVertex){
            JPanel keyLocationPanel = new JPanel(new GridLayout(3, 2));
            JTextField inputKey = createFilteredTextField(false);
            JTextField inputX =createFilteredTextField(true);
            JTextField inputY =createFilteredTextField(true);
            inputX.setColumns(5);
            inputY.setColumns(5);
            inputKey.setColumns(5);
            keyLocationPanel.add(new JLabel("Key: "));
            keyLocationPanel.add(inputKey);
            keyLocationPanel.add(new JLabel("x: "));
            keyLocationPanel.add(inputX);
            keyLocationPanel.add(new JLabel("y: "));
            keyLocationPanel.add(inputY);
            int k=-1;
            double xI=-1;
            double yI=-1;

            int res = JOptionPane.showConfirmDialog(null, keyLocationPanel,
                    "Add Vertex", JOptionPane.OK_CANCEL_OPTION);
            if(res==JOptionPane.OK_OPTION){
                k=Integer.parseInt(inputKey.getText());
                xI=Double.parseDouble(inputX.getText());
                yI=Double.parseDouble(inputY.getText());
                GeoLocationImpl xy= new GeoLocationImpl(xI,yI,0);
                NodeDataImpl temp= new NodeDataImpl(k,xy);
                ga.getGraph().addNode(temp);
                getTopLevelAncestor().repaint();
                ////needs to fix geo location to screen size
            }
        }else if(e.getSource()==deleteVertex){

        }else if(e.getSource()==addEdge){

        }else if(e.getSource()==deleteEdge){

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
