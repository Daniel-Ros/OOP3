package GUI;

import api.DirectedWeightedGraphAlgorithms;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;

public class SrcDestWindow extends JPanel {
    JLabel srcLabel;
    JLabel destLabel;
    JLabel restriction;
    JTextField srcInput=createFilteredTextField();
    JTextField destInput=createFilteredTextField();

    public SrcDestWindow(){
        super.setLayout(new GridLayout(3,2));
        srcLabel =new JLabel("Source: ");
        destLabel= new JLabel("Destination: ");
        restriction = new JLabel("Only Natural numbers");
        restriction.setForeground(Color.red);
        srcInput.setColumns(5);
        destInput.setColumns(5);
        super.add(srcLabel);
        super.add(srcInput);
        super.add(destLabel);
        super.add(destInput);
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
