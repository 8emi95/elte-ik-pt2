/**
 * Created by Csaba_Hete on 2017.04.12..
 */
package hu.elte.progtech.icafe.view.dialog;

import hu.elte.progtech.icafe.api.entity.Payment;
import hu.elte.progtech.icafe.api.entity.Session;
import hu.elte.progtech.icafe.api.entity.User;
import hu.elte.progtech.icafe.api.service.PaymentService;
import hu.elte.progtech.icafe.api.service.SessionService;
import hu.elte.progtech.icafe.view.panel.DoubleSidedPanel;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public abstract class FulfillPaymentDialog extends UserAdministrativeDialog {

    public FulfillPaymentDialog(JFrame frame, String title, boolean isModal, final User user) {
        super(frame, title, isModal, user);
    }

    @Override
    protected JPanel createActionButtonPanel() {
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new BorderLayout());
        JButton logoutButton = new JButton("Befizetés megerősítése");
        logoutButton.addActionListener(e -> onActionConfirm());
        btnPanel.add(logoutButton, BorderLayout.SOUTH);

        DoubleSidedPanel paymentPanel = new DoubleSidedPanel();
        btnPanel.add(paymentPanel, BorderLayout.NORTH);
        createPaymentDetailsPanel(paymentPanel);

        btnPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return btnPanel;
    }

    private void createPaymentDetailsPanel(DoubleSidedPanel sessionPanel) {
        Payment pendingPayment = user.getPendingPayment();
        sessionPanel.setBorder(BorderFactory.createTitledBorder("Befizetés adatai"));
        JTextField netPrice = new JTextField(25);
        netPrice.setEditable(false);
        netPrice.setText(pendingPayment.getNetPrice().toString());
        JTextField quantity = new JTextField(25);
        quantity.setEditable(false);
        quantity.setText(pendingPayment.getQuantity().toString());
        JTextField unit = new JTextField(25);
        unit.setEditable(false);
        unit.setText(pendingPayment.getUnit());
        JTextField currency = new JTextField(25);
        currency.setEditable(false);
        currency.setText(pendingPayment.getCurrency());
        JTextField vatRate = new JTextField(25);
        vatRate.setEditable(false);
        vatRate.setText(pendingPayment.getVatRate().toString() + "%");
        JTextField discount = new JTextField(25);
        discount.setEditable(false);
        discount.setText(pendingPayment.getDiscount().toString() + "%");
        JTextField grossAmount = new JTextField(25);
        grossAmount.setEditable(false);
        grossAmount.setText(pendingPayment.getGrossAmount().toString());

        sessionPanel.addInput(new JLabel("Nettó ár"), netPrice);
        sessionPanel.addInput(new JLabel("Mennyiség"), quantity);
        sessionPanel.addInput(new JLabel("Mennyiségi egység"), unit);
        sessionPanel.addInput(new JLabel("Deviza"), currency);
        sessionPanel.addInput(new JLabel("ÁFA"), vatRate);
        sessionPanel.addInput(new JLabel("Kedvezmény"), discount);
        sessionPanel.addInput(new JLabel("Bruttó összeg"), grossAmount);
    }
}