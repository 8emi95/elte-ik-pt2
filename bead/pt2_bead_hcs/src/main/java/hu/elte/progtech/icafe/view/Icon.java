/**
 * Created by Csaba_Hete on 2017.03.21..
 */
package hu.elte.progtech.icafe.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Icon extends ImageIcon {

    private static final double RATIO = 0.7;

    public Icon(String filename, int fieldWidth, int fieldHeight) {
        Image image = null;
        String workDir = System.getProperty("user.dir");
        File img = new File(workDir + "\\resources\\" + filename);

        try {
            image = ImageIO.read(img);
            image = image.getScaledInstance((int) (fieldWidth * RATIO), (int) (fieldHeight * RATIO),
                    Image.SCALE_SMOOTH);
            setImage(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
