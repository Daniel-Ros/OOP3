package GUI;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;

public class EditEdgeWindow extends JPanel {
    JLabel oldSrcLabel;
    JLabel oldDestLabel;
    JLabel newSrcLabel;
    JLabel newDestLabel;
    JLabel restriction;
    JTextField oldSrcInput =createFilteredTextField();
    JTextField oldDestInput =createFilteredTextField();
    JTextField newSrcInput =createFilteredTextField();
    JTextField newDestInput =createFilteredTextField();
    JCheckBox keepWeight;

    public EditEdgeWindow(){
        super.setLayout(new GridLayout(7,2));
        oldSrcLabel =new JLabel("Old Source: ");
        oldDestLabel = new JLabel("Old Destination: ");
        newSrcLabel =new JLabel("New Source: ");
        newDestLabel = new JLabel("New Destination: ");
        restriction = new JLabel("Only Natural numbers");
        keepWeight = new JCheckBox("KeepWeight ");
        restriction.setForeground(Color.red);
        oldSrcInput.setColumns(5);
        oldDestInput.setColumns(5);
        newSrcInput.setColumns(5);
        newDestInput.setColumns(5);
        super.add(oldSrcLabel);
        super.add(oldSrcInput);
        super.add(oldDestLabel);
        super.add(oldDestInput);
        super.add(newSrcLabel);
        super.add(newSrcInput);
        super.add(newDestLabel);
        super.add(newDestInput);
        super.add(keepWeight);
        super.setForeground(Color.red);
        super.add(restriction);

    }

    private JTextField createFilteredTextField(){
        JTextField field =new JTextField();
        AbstractDocument doc =(AbstractDocument) field.getDocument();
        final int maxCharacters =1000;
        doc.setDocumentFilter(new DocumentFilter(){
            public void replace(FilterBypass fb, int offset, int len, String str, AttributeSet a) throws BadLocationException {
                String regexType;
                regexType="^[1]?[0-9]{1,9999999}$";
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
                regexType="^[1]?[0-9]{1,9999999}$";
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
