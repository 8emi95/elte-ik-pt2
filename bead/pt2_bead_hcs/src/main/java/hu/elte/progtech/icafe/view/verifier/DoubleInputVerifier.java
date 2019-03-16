/**
 * Created by Csaba_Hete on 2017.03.22..
 */
package hu.elte.progtech.icafe.view.verifier;

import javax.swing.*;

public class DoubleInputVerifier extends HighlightingInputVerifier {

    private double min;
    private double max;

    public DoubleInputVerifier(double min, double max) {
        if (min > max) {
            throw new IllegalArgumentException();
        }
        this.min = min;
        this.max = max;
    }

    public DoubleInputVerifier(double min) {
        this(min, Double.MAX_VALUE);
    }

    @Override
    public boolean verify(JComponent input) {
        String text = ((JTextField) input).getText();
        try {
            double value = Double.parseDouble(text);
            return value >= min && value <= max;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
