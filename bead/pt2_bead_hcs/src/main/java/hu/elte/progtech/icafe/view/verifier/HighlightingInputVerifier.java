/**
 * Created by Csaba_Hete on 2017.04.12..
 */
package hu.elte.progtech.icafe.view.verifier;

import javax.swing.*;
import java.awt.*;

abstract class HighlightingInputVerifier extends InputVerifier {

    @Override
    public boolean shouldYieldFocus(JComponent input) {
        if (!verify(input)) {
            input.setBackground(Color.PINK);
        } else {
            input.setBackground(Color.WHITE);
        }
        return true;
    }
}
