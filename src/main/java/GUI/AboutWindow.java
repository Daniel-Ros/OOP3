package GUI;

import javax.swing.*;
import java.awt.*;


public class AboutWindow extends JPanel{
    JLabel about;
    public AboutWindow(){
        GridLayout layout = new GridLayout(1,1);
        super.setLayout(layout);
        about = new JLabel("<html>\n" +
                "<b>About</b>\n" +
                "<br><b>Menu Bar:</b></br>\n" +
                "<b>File: </b>\n" +
                "<br><li type=disc>Load: Import supported graph JSON file from Directory.</br>\n" +
                "<br><li type=disc> Save: Export JSON file.</br>\n" +
                "<li type=disc>Exit: close program.\n" +
                "<br></br>\n" +
                "    <br><b>Algo: </b></br>\n" +
                "<li type=disc> tsp:\n" +
                "<br><li type=disc>Shortest Path Distance: calculates the shortest distance between two Vertex </br>\n" +
                "<li type=disc>Shortest Path: Mark the shortest path from Source to Destination\n" +
                "<br><li type=disc>Connect: creates new weighted edge from Source to Destination </br>\n" +
                "<li type=disc> Is Connected: check if the graph is connected\n" +
                "<br><li type=disc>Center: The center point of the graph</br>\n" +
                "<br><br>\n"+
                "    <br><b>Graph: </b></br>\n" +
                "<li type=square>Vertex: Edit/ Delete Vertex\n" +
                "<li type=square>Edge: Add/ Delete/ Edit Edge\n" +
                "<br><br>\n" +
                "<b>Side Panel (Same functions as Algo)</b>\n" +
                "<br><br>\n" +
                "    <b>Graph Panel:</b>\n" +
                "<li type=disc> Mouse wheel: Zoom In/Out.\n" +
                "    <br><li type=disc>Double Click on Vertex open edit vertex window<br>\n" +
                "<li type=disc>Double Click on Graph Panel creates new Vertex\n" +
                "    <br><li type=disc>Click hold moves graph panel<br>\n" +
                "<br><br>\n" +
                "  Made By: Daniel Rosenberg and Daniel Zinn\n" +
                "<br><A HREF=\"https://github.com/Daniel-Ros/OOP3\">GitHub Link</a>.</br>\n" +
                "</html>");
        super.add(about);
    }
}
