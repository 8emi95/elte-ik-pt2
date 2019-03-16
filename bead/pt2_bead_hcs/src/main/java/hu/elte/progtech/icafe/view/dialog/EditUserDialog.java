/**
 * Created by Csaba_Hete on 2017.04.12..
 */
package hu.elte.progtech.icafe.view.dialog;

import hu.elte.progtech.icafe.api.entity.Address;
import hu.elte.progtech.icafe.api.entity.User;
import hu.elte.progtech.icafe.view.panel.DoubleSidedPanel;
import hu.elte.progtech.icafe.view.verifier.StringInputVerifier;

import javax.swing.*;

public abstract class EditUserDialog extends ICafeEntityEditDialog<User> {

    private JTextField name;
    private JTextField idNumber;
    private JTextField username;
    private JPasswordField password;
    private JPasswordField passwordConfirm;

    private JTextField country;
    private JTextField city;
    private JTextField zip;
    private JTextField streetName;
    private JTextField streetSuffix;
    private JTextField houseNumber;

    public EditUserDialog(JFrame frame, String title, boolean isModal, final User user) {
        super(frame, title, isModal, user);
    }

    @Override
    void setEditableValues() {
        setUserAttributes();
        setAddressAttributes();
    }

    private void setAddressAttributes() {
        Address address = editable.getAddress();
        address.setCountry(country.getText());
        address.setCity(city.getText());
        address.setZip(zip.getText());
        address.setStreetName(streetName.getText());
        address.setStreetSuffix(streetSuffix.getText());
        address.setHouseNumber(houseNumber.getText());
    }

    private void setUserAttributes() {
        editable.setName(name.getText());
        editable.setIdNumber(idNumber.getText());
        editable.setUsername(username.getText());
        editable.setPassword(password.getText());
    }

    @Override
    void prepareFields() {
        createUserTextFields();
        createAddressTextFields();
    }

    private void createUserTextFields() {
        name = new JTextField(25);
        idNumber = new JTextField(25);
        username = new JTextField(25);
        password = new JPasswordField(25);
        passwordConfirm = new JPasswordField(25);

        name.setInputVerifier(new StringInputVerifier(50, false));
        idNumber.setInputVerifier(new StringInputVerifier(20, false));
        username.setInputVerifier(new StringInputVerifier(20, false));
        password.setInputVerifier(new StringInputVerifier(64, false));
        passwordConfirm.setInputVerifier(new StringInputVerifier(64, false));
    }

    private void createAddressTextFields() {
        country = new JTextField(25);
        city = new JTextField(25);
        zip = new JTextField(25);
        streetName = new JTextField(25);
        streetSuffix = new JTextField(25);
        houseNumber = new JTextField(25);

        country.setInputVerifier(new StringInputVerifier(30, false));
        city.setInputVerifier(new StringInputVerifier(30, false));
        zip.setInputVerifier(new StringInputVerifier(20, false));
        streetName.setInputVerifier(new StringInputVerifier(30, false));
        streetSuffix.setInputVerifier(new StringInputVerifier(20, false));
        houseNumber.setInputVerifier(new StringInputVerifier(20, false));
    }

    @Override
    void initializeFields() {
        if (null == editable.getId()) {
            country.setText("Magyarország");
        } else {
            setUserJTextFields();
            setAddressJTextFields();
        }
    }

    private void setUserJTextFields() {
        name.setText(editable.getName());
        idNumber.setText(editable.getIdNumber());
        username.setText(editable.getUsername());
        password.setText(editable.getPassword());
        passwordConfirm.setText(editable.getPassword());
    }

    private void setAddressJTextFields() {
        Address address = editable.getAddress();
        country.setText(address.getCountry());
        city.setText(address.getCity());
        zip.setText(address.getZip());
        streetName.setText(address.getStreetName());
        streetSuffix.setText(address.getStreetSuffix());
        houseNumber.setText(address.getHouseNumber());
    }

    @Override
    DoubleSidedPanel createInputsPanel() {
        DoubleSidedPanel inputs = new DoubleSidedPanel();
        inputs.addInput(new JLabel("Név:"), name);
        inputs.addInput(new JLabel("Szem. ig. szám:"), idNumber);
        inputs.addInput(new JLabel("Felhasználónév:"), username);
        inputs.addInput(new JLabel("Jelszó:"), password);
        inputs.addInput(new JLabel("Jelszó megerősítés:"), passwordConfirm);
        inputs.addInput(new JLabel("Ország:"), country);
        inputs.addInput(new JLabel("Település:"), city);
        inputs.addInput(new JLabel("Irányítószám:"), zip);
        inputs.addInput(new JLabel("Közterület neve:"), streetName);
        inputs.addInput(new JLabel("Közterület jellege:"), streetSuffix);
        inputs.addInput(new JLabel("Házszám:"), houseNumber);
        inputs.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return inputs;
    }

    @Override
    boolean validateInputs() {
        return name.getInputVerifier().verify(name) &&
                idNumber.getInputVerifier().verify(idNumber) &&
                username.getInputVerifier().verify(username) &&
                password.getInputVerifier().verify(password) &&
                country.getInputVerifier().verify(country) &&
                city.getInputVerifier().verify(city) &&
                zip.getInputVerifier().verify(zip) &&
                streetName.getInputVerifier().verify(streetName) &&
                streetSuffix.getInputVerifier().verify(streetSuffix) &&
                houseNumber.getInputVerifier().verify(houseNumber);
    }

    @Override
    boolean inputsAreValid() {
        if (validateInputs()) {
            if (password.getText().equals(passwordConfirm.getText())) {
                return true;
            } else {
                displayErrorDialog("Hiba", "A megadott jelszavak nem egyeznek!.");
            }
        } else {
            displayErrorDialog("Hiba", "Hiányzó adat, vagy hibás érték került megadásra!\nJavítsa az adatokat, majd próbálja újra.");
        }
        return false;
    }
}