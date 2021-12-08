package GUI;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;

public class KeyLocationWindow extends JPanel {
    JLabel xLabel;
    JLabel yLabel;
    JLabel keyLabel;
    JTextField xInput;
    JTextField yInput;
    JTextField keyInput;
    JLabel restriction;

    public  KeyLocationWindow(){
        super.setLayout(new GridLayout(4,2));
        xLabel= new JLabel("X: ");
        yLabel= new JLabel("Y: ");
        keyLabel= new JLabel("Key(Natural numbers): ");
        restriction= new JLabel("Real Numbers");
        restriction.setForeground(Color.red);
        xInput=createFilteredTextField(true);
        yInput=createFilteredTextField(true);
        keyInput=createFilteredTextField(false);
        keyInput.setColumns(5);
        xInput.setColumns(5);
        yInput.setColumns(5);
        super.add(keyLabel);
        super.add(keyInput);
        super.add(xLabel);
        super.add(xInput);
        super.add(yLabel);
        super.add(yInput);
        super.add(restriction);
    }
    private JTextField createFilteredTextField(boolean type){
        JTextField field =new JTextField();
        AbstractDocument doc =(AbstractDocument) field.getDocument();
        final int maxCharacters =1000;
        doc.setDocumentFilter(new DocumentFilter(){
            public void replace(FilterBypass fb, int offset, int len, String str, AttributeSet a) throws BadLocationException {
                String regexType;
                if(type) {
                    regexType = "^[1]?[0-9]{1,9999999}([.][0-9]{0,7})?$";
                }else{
                    regexType = "^[1]?[0-9]{1,9999999}?$";
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
                if(type) {
                    regexType = "^[1]?[0-9]{1,9999999}([.][0-9]{0,7})?$";
                }else{
                    regexType = "^[1]?[0-9]{1,9999999}?$";
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
