
package contents.entering;

import contents.information.InformationText;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author Lúcia
 */
public class DataEnteringForSearching extends JButton{
    private JLabel titleLabel=new JLabel("Adja meg a film címét:");
    private JTextField titleField=new JTextField();
    private JLabel dirLabel=new JLabel("Adja meg a rendezőt: ");
    private JTextField directorField=new JTextField();
    
    private JLabel mainCharLabel=new JLabel("Adja meg a főszereplőket (vesszővel elválasztva):");
    private JTextField mainCharField=new JTextField();
    
    
    private JLabel yearLabel=new JLabel("Adja meg az évet: (0 és az idei év között)");
    private JTextField yearField=new JTextField();
    
    private JLabel lengthLabel=new JLabel("Adja meg a film hosszát: ");
    private JTextField lengthField=new JTextField(); 
    
    private JLabel typeLabel=new JLabel("Adja meg a típust: ");
     
    private ButtonGroup groupType = new ButtonGroup(); 
    private JRadioButton DVD_RadioButton=new JRadioButton("DVD");
    
    private JRadioButton VHS_RadioButton=new JRadioButton("VHS");
    

    private JLabel isOriginalLabel=new JLabel("Eredeti a film?  ");
    private ButtonGroup groupOriginal = new ButtonGroup();
       
    private JRadioButton originalRadioButton=new JRadioButton("Igen");
    private JRadioButton notOriginalRadioButton=new JRadioButton("Nem");

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

    public JLabel getMainCharLabel() {
        return mainCharLabel;
    }

    public JTextField getMainCharField() {
        return mainCharField;
    }

    public JLabel getYearLabel() {
        return yearLabel;
    }

    public JTextField getYearField() {
        return yearField;
    }

    public JLabel getLengthLabel() {
        return lengthLabel;
    }

    public JTextField getLengthField() {
        return lengthField;
    }

    public JLabel getTypeLabel() {
        return typeLabel;
    }

    public ButtonGroup getGroupType() {
        return groupType;
    }

    public JRadioButton getDVD_RadioButton() {
        return DVD_RadioButton;
    }

    public JRadioButton getVHS_RadioButton() {
        return VHS_RadioButton;
    }

    public JLabel getIsOriginalLabel() {
        return isOriginalLabel;
    }

    public JRadioButton getOriginalRadioButton() {
        return originalRadioButton;
    }



    public JRadioButton getNotOriginalRadioButton() {
        return notOriginalRadioButton;
    }
      
    public DataEnteringForSearching(JFrame frame, String information){
    

        frame.getContentPane().removeAll();
        frame.setResizable(false);

      frame.getContentPane().setLayout(new FlowLayout());

      //________________________________________________________________________
      
      Dimension d=new Dimension(200,20);
      titleField.setPreferredSize(d);
      titleField.setMinimumSize(d);
      titleField.setMaximumSize(d);

      
      //________________________________________________________________________
      
      directorField.setPreferredSize(d);
      directorField.setMinimumSize(d);
      directorField.setMaximumSize(d);
       
 
      //________________________________________________________________________
      
      mainCharField.setPreferredSize(d);
      mainCharField.setMinimumSize(d);
      mainCharField.setMaximumSize(d);
 
      //________________________________________________________________________
      
      Dimension d3=new Dimension(40,20);
      yearField.setPreferredSize(d3);
      yearField.setMinimumSize(d3);
      yearField.setMaximumSize(d3);

      //________________________________________________________________________
     
     lengthField.setPreferredSize(d3);
     lengthField.setMinimumSize(d3);
     lengthField.setMaximumSize(d3);
   
  
      //________________________________________________________________________
     
     
     
     DVD_RadioButton.setSelected(true);
     
 
     groupType.add(DVD_RadioButton);
     groupType.add(VHS_RadioButton);
      
   //________________________________________________________________________ 
 
      originalRadioButton.setSelected(true); 
      groupOriginal.add(originalRadioButton);
      groupOriginal.add(notOriginalRadioButton);

      //________________________________________________________________________
      
      frame.getContentPane().add(this,BoxLayout.X_AXIS);
      this.setVisible(false);
      
       
       frame.getContentPane().add(isOriginalLabel,BoxLayout.X_AXIS);
       frame.getContentPane().add(notOriginalRadioButton,BoxLayout.Y_AXIS);
       frame.getContentPane().add(originalRadioButton,BoxLayout.Y_AXIS);
       
   
       frame.getContentPane().add(typeLabel,BoxLayout.X_AXIS);
       frame.getContentPane().add(VHS_RadioButton,BoxLayout.Y_AXIS);
       frame.getContentPane().add(DVD_RadioButton,BoxLayout.Y_AXIS);
       
       frame.getContentPane().add(lengthLabel,BoxLayout.X_AXIS);
       frame.getContentPane().add(lengthField,BoxLayout.Y_AXIS);
       
       frame.getContentPane().add(yearLabel,BoxLayout.X_AXIS);
       frame.getContentPane().add(yearField,BoxLayout.Y_AXIS);
       
       frame.getContentPane().add(mainCharLabel,BoxLayout.X_AXIS);
       frame.getContentPane().add(mainCharField,BoxLayout.Y_AXIS);
       
       frame.getContentPane().add(dirLabel,BoxLayout.X_AXIS);
       frame.getContentPane().add(directorField,BoxLayout.Y_AXIS);
       
       frame.getContentPane().add(titleField,BoxLayout.X_AXIS);
       frame.getContentPane().add(titleLabel,BoxLayout.X_AXIS);
       
       
       InformationText informatinText=new InformationText(frame,information);

    }
    
    
}
