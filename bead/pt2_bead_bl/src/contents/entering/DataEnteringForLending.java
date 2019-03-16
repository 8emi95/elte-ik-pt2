
package contents.entering;

import contents.information.InformationText;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Lúcia
 */
public class DataEnteringForLending extends JButton{

    
    
    private JLabel titleLabel=new JLabel("Adja meg a film címét:");
    private JTextField titleField=new JTextField();
    private JLabel dirLabel=new JLabel("Adja meg a rendezőt: ");
    private JTextField directorField=new JTextField();
    
    
    
    private JLabel lendByLabel=new JLabel("Adja meg a nevét: ");
    private JTextField lendByField=new JTextField();


    public JLabel getTitleLabel() {
        return titleLabel;
    }

    public JTextField getTitleField() {
        return titleField;
    }

    public JLabel getDirLabel() {
        return dirLabel;
    }

    public JTextField getDirectorField() {
        return directorField;
    }

    public JLabel getLendByLabel() {
        return lendByLabel;
    }

    public JTextField getLendByField() {
        return lendByField;
    }
    

    public DataEnteringForLending(JFrame frame, String information){
 
    frame.getContentPane().removeAll();
    frame.setResizable(false);

    frame.getContentPane().setLayout(new FlowLayout());

      
      Dimension d=new Dimension(200,20);
      titleField.setPreferredSize(d);
      titleField.setMinimumSize(d);
      titleField.setMaximumSize(d);

      
      directorField.setPreferredSize(d);
      directorField.setMinimumSize(d);
      directorField.setMaximumSize(d);
       

      lendByField.setPreferredSize(d);
      lendByField.setMinimumSize(d);
      lendByField.setMaximumSize(d);

      frame.getContentPane().add(this,BoxLayout.X_AXIS);
      this.setVisible(false);
      
      frame.getContentPane().add(lendByLabel,BoxLayout.X_AXIS);
      frame.getContentPane().add(lendByField,BoxLayout.Y_AXIS);

      frame.getContentPane().add(dirLabel,BoxLayout.X_AXIS);
      frame.getContentPane().add(directorField,BoxLayout.Y_AXIS);
       
      frame.getContentPane().add(titleField,BoxLayout.X_AXIS);
      frame.getContentPane().add(titleLabel,BoxLayout.X_AXIS);

      InformationText informatinText=new InformationText(frame,information);

    }
    
    
}
