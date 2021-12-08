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
    JMenuItem editVertex;
    JMenuItem editEdge;


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


        editVertex = new JMenuItem("Edit");
        editEdge = new JMenuItem("Edit");
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

        editEdge.addActionListener(this);
        editVertex.addActionListener(this);
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
        vertex.add(editVertex);
        edges.add(addEdge);
        edges.add(deleteEdge);
        edges.add(editEdge);

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
            SrcDestWindow srcDestPanel = new SrcDestWindow();
            WeightWindow weightPanel = new WeightWindow();

            if (e.getSource() == connect) {
                int res = JOptionPane.showConfirmDialog(null, srcDestPanel,
                        "Please Enter Source and Destination Values (Natural Numbers only)", JOptionPane.OK_CANCEL_OPTION);
                if (res == JOptionPane.OK_OPTION) {
                    String srcString = srcDestPanel.srcInput.getText();
                    String destString = srcDestPanel.destInput.getText();
                    if (srcString.isEmpty() || destString.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Empty Text Field");
                        return;
                    }
                    src = Integer.parseInt(srcDestPanel.srcInput.getText());
                    dest = Integer.parseInt(srcDestPanel.destInput.getText());
                    if (ga.getGraph().getNode(src) == null || ga.getGraph().getNode(dest) == null) {
                        JOptionPane.showMessageDialog(null, "Invalid Vertex key");
                    }
                }
                res=JOptionPane.showConfirmDialog(null,weightPanel,"Enter Weight (Real Numbers only)",JOptionPane.OK_CANCEL_OPTION);
                if(res==JOptionPane.OK_OPTION){
                    String weightString=weightPanel.weightInput.getText();
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
                    String srcString = srcDestPanel.srcInput.getText();
                    String destString = srcDestPanel.destInput.getText();
                    if (srcString.isEmpty() || destString.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Empty Text Field");
                        return;
                    }
                    src = Integer.parseInt(srcDestPanel.srcInput.getText());
                    dest = Integer.parseInt(srcDestPanel.destInput.getText());
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
                    String srcString = srcDestPanel.srcInput.getText();
                    String destString = srcDestPanel.destInput.getText();
                    if (srcString.isEmpty() || destString.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Empty Text Field");
                        return;
                    }
                    src = Integer.parseInt(srcDestPanel.srcInput.getText());
                    dest = Integer.parseInt(srcDestPanel.destInput.getText());
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
            KeyLocationWindow keyLocationPanel = new KeyLocationWindow();
            int k=-1;
            double xI=-1;
            double yI=-1;

            int res = JOptionPane.showConfirmDialog(null, keyLocationPanel,
                    "Add Vertex", JOptionPane.OK_CANCEL_OPTION);
            if(res==JOptionPane.OK_OPTION){
                k=Integer.parseInt(keyLocationPanel.keyInput.getText());
                xI=Double.parseDouble(keyLocationPanel.xInput.getText());
                yI=Double.parseDouble(keyLocationPanel.yInput.getText());
                GeoLocationImpl xy= new GeoLocationImpl(xI,yI,0);
                NodeDataImpl temp= new NodeDataImpl(k,xy);
                ga.getGraph().addNode(temp);
                getTopLevelAncestor().repaint();
                ////needs to fix geo location to screen size
            }
        }else if(e.getSource()==deleteVertex){
            KeyWindow keyPanel = new KeyWindow();
            int key=-1;
            int res = JOptionPane.showConfirmDialog(null, keyPanel,
                    "Choose key to delete", JOptionPane.OK_CANCEL_OPTION);
            if (res== JOptionPane.OK_OPTION){
                key=Integer.parseInt(keyPanel.keyInput.getText());
                if(ga.getGraph().getNode(key)!=null){
                    ga.getGraph().removeNode(key);
                }else{
                    JOptionPane.showMessageDialog(null,"Wrong Input");
                }
            }
            getTopLevelAncestor().repaint();
        }else if(e.getSource()==addEdge){
            SrcDestWindow getSrcDestWeightPanel = new SrcDestWindow();
            WeightWindow weightPanel = new WeightWindow();
            int res = JOptionPane.showConfirmDialog(null, getSrcDestWeightPanel,
                    "Add Edge", JOptionPane.OK_CANCEL_OPTION);
            if (res== JOptionPane.OK_OPTION){
                int src= Integer.parseInt(getSrcDestWeightPanel.srcInput.getText());
                int dest= Integer.parseInt(getSrcDestWeightPanel.destInput.getText());
                JOptionPane.showConfirmDialog(null,weightPanel,"Weight",JOptionPane.OK_OPTION);
                double weight= Double.parseDouble(weightPanel.weightInput.getText());
                if(weight>0 && ga.getGraph().getNode(src)!=null && ga.getGraph().getNode(dest)!=null){
                    ga.getGraph().connect(src,dest,weight);
                }else{
                    JOptionPane.showMessageDialog(null,"Wrong Input");
                }
            }
            getTopLevelAncestor().repaint();

//        }else if(e.getSource()==deleteEdge){
//            JPanel getSrcDestPanel = new JPanel(new GridLayout(3,2));
//            inputSrc.setColumns(5);
//            inputDest.setColumns(5);
//            getSrcDestPanel.add(new JLabel("Edge Source: "));
//            getSrcDestPanel.add(inputSrc);
//            getSrcDestPanel.add(new JLabel("Edge Destination: "));
//            getSrcDestPanel.add(inputDest);
//            int res = JOptionPane.showConfirmDialog(null, getSrcDestPanel,
//                    "Choose key to delete", JOptionPane.OK_CANCEL_OPTION);
//            if (res== JOptionPane.OK_OPTION){
//                int src= Integer.parseInt(inputSrc.getText());
//                int dest= Integer.parseInt(inputDest.getText());
//                if(ga.getGraph().getEdge(src,dest)!=null && ga.getGraph().getNode(src)!=null && ga.getGraph().getNode(dest)!=null){
//                    ga.getGraph().removeEdge(src,dest);
//                }else{
//                    JOptionPane.showMessageDialog(null,"Wrong Input");
//                }
//            }
//            getTopLevelAncestor().repaint();
//        }else if(e.getSource()==editEdge){
//            JPanel getSrcDestWeightPanel = new JPanel(new GridLayout(5,2));
//            JCheckBox editWeightCheckBox = new JCheckBox("Keep Weight: ");
//            inputSrc.setColumns(5);
//            inputDest.setColumns(5);
//            inputWeight.setColumns(5);
//            getSrcDestWeightPanel.add(new JLabel("Edge Source: "));
//            getSrcDestWeightPanel.add(inputSrc);
//            getSrcDestWeightPanel.add(new JLabel("Edge Destination: "));
//            getSrcDestWeightPanel.add(inputDest);
//            getSrcDestWeightPanel.add(new JLabel("New Edge Source: "));
//            getSrcDestWeightPanel.add(inputSrcNew);
//            getSrcDestWeightPanel.add(new JLabel("New Edge Destination: "));
//            getSrcDestWeightPanel.add(inputDestNew);
//            getSrcDestWeightPanel.add(editWeightCheckBox);
//            int res = JOptionPane.showConfirmDialog(null, getSrcDestWeightPanel,
//                    "Edit Edge", JOptionPane.OK_CANCEL_OPTION);
//            if (res== JOptionPane.OK_OPTION){
//                int src= Integer.parseInt(inputSrc.getText());
//                int dest= Integer.parseInt(inputDest.getText());
//                boolean keepWeight= editWeightCheckBox.isSelected();
//                double weight=1;
//                if(ga.getGraph().getNode(src)!=null && ga.getGraph().getNode(dest)!=null && ga.getGraph().getEdge(src,dest)!=null){
//                    weight=ga.getGraph().getEdge(src,dest).getWeight();
//                    if(!keepWeight){
//                        JPanel getNewWeight = new JPanel(new GridLayout(1,2));
//                        getNewWeight.add(new JLabel("New Weight :"));
//                        getNewWeight.add(inputWeight);
//                        int resW = JOptionPane.showConfirmDialog(null, getNewWeight,
//                                "New Weight", JOptionPane.OK_CANCEL_OPTION);
//                        if(!inputWeight.getText().isEmpty() && resW==JOptionPane.OK_OPTION) {
//                            weight = Double.parseDouble(inputWeight.getText());
//                        }else{
//                            JOptionPane.showMessageDialog(null,"Didnt give any value keeping old weight");
//                        }
//                    }
//                    ga.getGraph().removeEdge(src,dest);
//                    int newSrc= Integer.parseInt(inputSrcNew.getText());
//                    int newDest= Integer.parseInt(inputDestNew.getText());
//                    ga.getGraph().connect(newSrc,newDest,weight);
//                }else{
//                    JOptionPane.showMessageDialog(null,"Wrong Input");
//                }
//            }
//            getTopLevelAncestor().repaint();
//        }else if(e.getSource()==editVertex){
//            JPanel editVertexLocation= new JPanel(new GridLayout(2,2));
//            JTextField inputX = createFilteredTextField(true);
//            JTextField inputY = createFilteredTextField(true);
//            inputX.setColumns(5);
//            inputY.setColumns(5);
//            editVertexLocation.add(new JLabel("set X: "));
//            editVertexLocation.add(inputX);
//            editVertexLocation.add(new JLabel("set Y: "));
//            editVertexLocation.add(inputY);
//
//            JPanel chooseKey= new JPanel(new GridLayout(1,2));
//            JTextField inputKey= createFilteredTextField(false);
//            inputKey.setColumns(5);
//            chooseKey.add(new JLabel("Key: "));
//            chooseKey.add(inputKey);
//
//            int res= JOptionPane.showConfirmDialog(null, chooseKey,
//                    "Key", JOptionPane.OK_CANCEL_OPTION);
//            if (res==JOptionPane.OK_OPTION){
//                int key=Integer.parseInt(inputKey.getText());
//                if(ga.getGraph().getNode(key)!=null){
//                    NodeData temp=ga.getGraph().getNode(key);
//                    int resXY= JOptionPane.showConfirmDialog(null, editVertexLocation,
//                            "Choose Location", JOptionPane.OK_CANCEL_OPTION);
//                    if(resXY==JOptionPane.OK_OPTION){
//                        double x=Double.parseDouble(inputX.getText());
//                        double y=Double.parseDouble(inputY.getText());
//                        GeoLocationImpl xyNew= new GeoLocationImpl(x,y,0);
//                        temp.setLocation(xyNew);
//                    }
//                }else{
//                    JOptionPane.showMessageDialog(null,"Wrong Input");
//                }
//            }
//            getTopLevelAncestor().repaint();
        }
    }
}
