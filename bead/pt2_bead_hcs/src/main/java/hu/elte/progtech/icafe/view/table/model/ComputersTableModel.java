package hu.elte.progtech.icafe.view.table.model;

import hu.elte.progtech.icafe.api.entity.Computer;
import hu.elte.progtech.icafe.api.service.ComputerService;
import hu.elte.progtech.icafe.view.MainFrame;

import java.sql.SQLException;
import java.sql.Timestamp;

public class ComputersTableModel extends EntityTableModel<Computer> {

    public ComputersTableModel() {
        super(Computer.FIELD_NAMES, new ComputerService());
    }

    @Override
    public Computer getEntityAtRow(int rowIndex) {
        Computer computer = entities.get(rowIndex);
        try{
            return service.getEntityById(computer.getId());
        } catch (Exception e){
            MainFrame.showError("Hiba", "A számítógép adatainak lekérése során hiba történt: \n" + e.getMessage());
        }
        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            case 4:
                return String.class;
            case 5:
                return String.class;
            case 6:
                return String.class;
            case 7:
                return Boolean.class;
            case 8:
                return Timestamp.class;
            case 9:
                return Timestamp.class;
            default:
                return null;
        }
    }

    @Override
    protected Object getAttributeOfEntity(Computer computer, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return computer.getName();
            case 1:
                return computer.getCpu();
            case 2:
                return computer.getMotherboard();
            case 3:
                return computer.getMemory();
            case 4:
                return computer.getVga();
            case 5:
                return computer.getMassStorage();
            case 6:
                return computer.getOs();
            case 7:
                return computer.isBusy();
            case 8:
                return computer.getCreatedAt();
            case 9:
                return computer.getCreatedAt();
            default:
                return null;
        }
    }

    @Override
    protected void displayError(SQLException e) {
        MainFrame.showError("HIBA", "Hiba a számítógépek listázása közben: \n" + e.getMessage());
    }
}
