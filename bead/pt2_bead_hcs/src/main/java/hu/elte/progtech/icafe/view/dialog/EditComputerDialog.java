/**
 * Created by Csaba_Hete on 2017.04.12..
 */
package hu.elte.progtech.icafe.view.dialog;

import hu.elte.progtech.icafe.api.entity.Computer;
import hu.elte.progtech.icafe.view.panel.DoubleSidedPanel;
import hu.elte.progtech.icafe.view.verifier.StringInputVerifier;

import javax.swing.*;

public abstract class EditComputerDialog extends ICafeEntityEditDialog<Computer> {

    private JTextField name;
    private JTextField cpu;
    private JTextField motherboard;
    private JTextField memory;
    private JTextField vga;
    private JTextField massStorage;
    private JTextField os;

    public EditComputerDialog(JFrame frame, String title, boolean isModal, final Computer computer) {
        super(frame, title, isModal, computer);
    }

    @Override
    void setEditableValues() {
        editable.setName(name.getText());
        editable.setCpu(cpu.getText());
        editable.setMotherboard(motherboard.getText());
        editable.setMemory(memory.getText());
        editable.setVga(vga.getText());
        editable.setMassStorage(massStorage.getText());
        editable.setOs(os.getText());
    }

    @Override
    void prepareFields() {
        name = new JTextField(10);
        cpu = new JTextField(50);
        motherboard = new JTextField(50);
        memory = new JTextField(50);
        vga = new JTextField(50);
        massStorage = new JTextField(50);
        os = new JTextField(50);

        name.setInputVerifier(new StringInputVerifier(10, false));
        cpu.setInputVerifier(new StringInputVerifier(50));
        motherboard.setInputVerifier(new StringInputVerifier(50));
        memory.setInputVerifier(new StringInputVerifier(50));
        vga.setInputVerifier(new StringInputVerifier(50));
        massStorage.setInputVerifier(new StringInputVerifier(50));
        os.setInputVerifier(new StringInputVerifier(50));
    }

    @Override
    void initializeFields() {
        name.setText(editable.getName());
        cpu.setText(editable.getCpu());
        motherboard.setText(editable.getMotherboard());
        memory.setText(editable.getMemory());
        vga.setText(editable.getVga());
        massStorage.setText(editable.getMassStorage());
        os.setText(editable.getOs());
    }

    @Override
    DoubleSidedPanel createInputsPanel() {
        DoubleSidedPanel inputs = new DoubleSidedPanel();
        inputs.addInput(new JLabel("Név"), name);
        inputs.addInput(new JLabel("CPU"), cpu);
        inputs.addInput(new JLabel("Alaplap"), motherboard);
        inputs.addInput(new JLabel("RAM"), memory);
        inputs.addInput(new JLabel("VGA"), vga);
        inputs.addInput(new JLabel("HDD"), massStorage);
        inputs.addInput(new JLabel("OS"), os);
        inputs.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return inputs;
    }

    @Override
    boolean validateInputs() {
        return name.getInputVerifier().verify(name) &&
                cpu.getInputVerifier().verify(cpu) &&
                motherboard.getInputVerifier().verify(motherboard) &&
                memory.getInputVerifier().verify(memory) &&
                vga.getInputVerifier().verify(vga) &&
                massStorage.getInputVerifier().verify(massStorage) &&
                os.getInputVerifier().verify(os);
    }
}