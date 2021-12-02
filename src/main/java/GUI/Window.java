package GUI;

import api.DirectedWeightedGraphAlgorithms;
import implentations.DirectedWeightedGraphAlgorithmsImpl;

import javax.swing.JFrame;
import java.awt.*;


public class Window {

    DirectedWeightedGraphAlgorithms ga;
    JFrame window;
    GrapPanel grapPanel;
    SidePanel sidePanel;
    MenuBar menuBar;
    public Window(DirectedWeightedGraphAlgorithms graphAlgorithms){
        ga = graphAlgorithms;

        window = new JFrame();
        grapPanel = new GrapPanel(ga,((DirectedWeightedGraphAlgorithmsImpl)ga).getMin(),((DirectedWeightedGraphAlgorithmsImpl)ga).getMax());
        sidePanel = new SidePanel(graphAlgorithms);
        menuBar = new MenuBar(graphAlgorithms);

        window.setSize(800,800);
        window.setTitle("Daniel Roseberg and Daniel Zinn");
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.add(sidePanel, BorderLayout.EAST);
        window.add(grapPanel,BorderLayout.CENTER);
        window.setJMenuBar(menuBar);
    }

}
