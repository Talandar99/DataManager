package src;

import java.util.ArrayList;
import javax.swing.JTextField;

public class MainTableField extends JTextField{

    static ArrayList<JTextField> textFields = new ArrayList<JTextField>();
    
    public MainTableField() {
		textFields.add(this);}
}
