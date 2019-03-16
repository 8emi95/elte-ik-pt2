/**
 * Created by Csaba_Hete on 2017.04.12..
 */
package hu.elte.progtech.icafe.view.dialog;

import hu.elte.progtech.icafe.backend.configuration.AppConfiguration;
import hu.elte.progtech.icafe.backend.controller.Currency;
import hu.elte.progtech.icafe.view.panel.DoubleSidedPanel;
import hu.elte.progtech.icafe.view.verifier.DoubleInputVerifier;
import hu.elte.progtech.icafe.view.verifier.IntegerInputVerifier;

import javax.swing.*;
import java.util.Properties;

public abstract class EditSystemSettingsDialog extends ICafeEntityEditDialog<Properties> {

    private JComboBox<Currency> currencyField;
    private JTextField discountPerPointsField;
    private JTextField vatRateField;
    private JTextField maxDiscountField;
    private JTextField netPriceField;

    public EditSystemSettingsDialog(JFrame frame, String title, boolean isModal) {
        super(frame, title, isModal, AppConfiguration.getConfiguration());
    }

    @Override
    void setEditableValues() {
        Currency selectedCurrency = (Currency) currencyField.getSelectedItem();
        editable.setProperty("currency", selectedCurrency.name());
        editable.setProperty("discountPerPoints", discountPerPointsField.getText());
        editable.setProperty("vatRate", vatRateField.getText());
        editable.setProperty("maxDiscount", maxDiscountField.getText());
        editable.setProperty("netPrice", netPriceField.getText());
    }

    @Override
    void prepareFields() {
        currencyField = new JComboBox<>(Currency.values());
        discountPerPointsField = new JTextField();
        vatRateField = new JTextField();
        maxDiscountField = new JTextField();
        netPriceField = new JTextField();

        discountPerPointsField.setInputVerifier(new IntegerInputVerifier());
        vatRateField.setInputVerifier(new IntegerInputVerifier(0, 100));
        maxDiscountField.setInputVerifier(new IntegerInputVerifier(0, 100));
        netPriceField.setInputVerifier(new DoubleInputVerifier(0));
    }

    @Override
    void initializeFields() {
        currencyField.setSelectedItem(Currency.valueOf(editable.getProperty("currency")));
        discountPerPointsField.setText(editable.getProperty("discountPerPoints"));
        vatRateField.setText(editable.getProperty("vatRate"));
        maxDiscountField.setText(editable.getProperty("maxDiscount"));
        netPriceField.setText(editable.getProperty("netPrice"));
    }

    @Override
    DoubleSidedPanel createInputsPanel() {
        DoubleSidedPanel inputs = new DoubleSidedPanel();
        inputs.addInput(new JLabel("Pénznem:"), currencyField);
        inputs.addInput(new JLabel("1% kedvezmény / pont:"), discountPerPointsField);
        inputs.addInput(new JLabel("ÁFAkulcs:"), vatRateField);
        inputs.addInput(new JLabel("Maximális kedvezmény %:"), maxDiscountField);
        inputs.addInput(new JLabel("Nettó ár / óra:"), netPriceField);
        inputs.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return inputs;
    }

    @Override
    boolean validateInputs() {
        return discountPerPointsField.getInputVerifier().verify(discountPerPointsField) &&
                vatRateField.getInputVerifier().verify(vatRateField) &&
                maxDiscountField.getInputVerifier().verify(maxDiscountField);
    }
}