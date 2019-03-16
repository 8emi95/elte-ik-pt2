/**
 * Created by Csaba_Hete on 2017.03.22..
 */
package hu.elte.progtech.icafe.view.verifier;

import javax.swing.*;

public class IntegerInputVerifier extends HighlightingInputVerifier {

    private int min;
    private int max;

    public IntegerInputVerifier(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException();
        }
        this.min = min;
        this.max = max;
    }

    public IntegerInputVerifier(int min) {
        this(min, Integer.MAX_VALUE);
    }

    public IntegerInputVerifier() {
        this(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    @Override
    public boolean verify(JComponent input) {
        String text = ((JTextField) input).getText();
        try {
            int value = Integer.parseInt(text);
            return value >= min && value <= max;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
