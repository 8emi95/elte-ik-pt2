
package contents.information;

import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JTextArea;


public class InformationText {
    
    public InformationText(JFrame frame, String text){
        
    JTextArea textArea = new JTextArea(
                text
                , 
                6, 
                20);
        textArea.setFont(new Font("Serif", Font.HANGING_BASELINE, 16));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setOpaque(false);
        textArea.setEditable(false);
        frame.getContentPane().add(textArea,BoxLayout.X_AXIS);
    }
    

}
