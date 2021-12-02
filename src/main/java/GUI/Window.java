package GUI;

import api.DirectedWeightedGraphAlgorithms;
import implentations.DirectedWeightedGraphAlgorithmsImpl;

import javax.swing.JFrame;
import java.awt.*;


public class Window {

    DirectedWeightedGraphAlgorithms ga;
    JFrame window;
    GrapPanel graphPanel;
    SidePanel sidePanel;
    MenuBar menuBar;
    public Window(DirectedWeightedGraphAlgorithms graphAlgorithms){
        ga = graphAlgorithms;

        window = new JFrame();
        graphPanel = new GrapPanel(ga,((DirectedWeightedGraphAlgorithmsImpl)ga).getMin(),((DirectedWeightedGraphAlgorithmsImpl)ga).getMax());
        sidePanel = new SidePanel(graphAlgorithms);
        menuBar = new MenuBar(graphAlgorithms);

        window.setSize(800,800);
        window.setTitle("Daniel Roseberg and Daniel Zinn");
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        graphPanel.setNodeSelectionListener(sidePanel);

        sidePanel.setMaximumSize(new Dimension(50,800));

        window.add(sidePanel, BorderLayout.EAST);
        window.add(graphPanel,BorderLayout.CENTER);
        window.setJMenuBar(menuBar);


    }

}
