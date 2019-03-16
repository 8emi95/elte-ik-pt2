/**
 * Created by Csaba_Hete on 2017.03.22..
 */
package hu.elte.progtech.icafe.view.verifier;

import javax.swing.*;
import java.awt.*;

public class StringInputVerifier extends HighlightingInputVerifier {

    private int inputLength;
    private boolean allowEmptyString;

    public StringInputVerifier(int inputLength, boolean allowEmptyString) {
        if (inputLength <= 0) {
            throw new IllegalArgumentException();
        }
        this.allowEmptyString = allowEmptyString;
        this.inputLength = inputLength;
    }

    public StringInputVerifier(int inputLength) {
        this(inputLength, true);
    }

    @Override
    public boolean shouldYieldFocus(JComponent input) {
        String text = ((JTextField) input).getText();
        String tooltip = "";
        if (!verify(input)) {
            if (!allowEmptyString && text.length() == 0) {
                tooltip = "A mező kitöltése kötelező!";
            } else if (text.length() >= inputLength) {
                tooltip = "Legfejjebb " + inputLength + " karaktert adjon meg!";
            }
            input.setBackground(Color.PINK);
            input.setToolTipText(tooltip);
        } else {
            input.setToolTipText(null);
            input.setBackground(Color.WHITE);
        }
        return true;
    }

    @Override
    public boolean verify(JComponent input) {
        JTextField textField = (JTextField) input;
        String text = textField.getText().trim();
        textField.setText(text);
        return ((allowEmptyString || text.length() != 0) && text.length() <= inputLength);
    }
}
