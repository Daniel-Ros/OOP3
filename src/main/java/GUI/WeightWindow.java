package GUI;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;

public class WeightWindow extends JPanel{
    JTextField weightInput;
    JLabel weightLabel;
    JLabel restriction;

    public WeightWindow(){
        super.setLayout(new GridLayout(2,2));
        weightLabel= new JLabel("Weight: ");
        restriction= new JLabel("Real Numbers");
        restriction.setForeground(Color.red);
        weightInput=createFilteredTextField();
        weightInput.setColumns(5);
        super.add(weightLabel);
        super.add(weightInput);
        super.add(restriction);
    }


    private JTextField createFilteredTextField(){
        JTextField field =new JTextField();
        AbstractDocument doc =(AbstractDocument) field.getDocument();
        final int maxCharacters =1000;
        doc.setDocumentFilter(new DocumentFilter(){
            public void replace(FilterBypass fb, int offset, int len, String str, AttributeSet a) throws BadLocationException {
                String regexType;
                regexType="^[1]?[0-9]{1,9999999}([.][0-9]{0,7})?$";
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
                regexType="^[1]?[0-9]{1,9999999}([.][0-9]{0,7})?$";
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
