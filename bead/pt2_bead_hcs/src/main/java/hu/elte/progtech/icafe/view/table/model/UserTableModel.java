package hu.elte.progtech.icafe.view.table.model;

import hu.elte.progtech.icafe.api.entity.User;
import hu.elte.progtech.icafe.api.service.UserService;
import hu.elte.progtech.icafe.view.MainFrame;

import java.sql.SQLException;
import java.sql.Timestamp;

public class UserTableModel extends EntityTableModel<User> {

    public UserTableModel() {
        super(User.FIELD_NAMES, new UserService());
    }

    @Override
    public User getEntityAtRow(int rowIndex) {
        User user = entities.get(rowIndex);
        try{
            return service.getEntityById(user.getId());
        } catch (Exception e){
            MainFrame.showError("Hiba", "Az ügyfél adatainak lekérése során hiba történt: \n" + e.getMessage());
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
                return Integer.class;
            case 4:
                return Boolean.class;
            case 5:
                return Timestamp.class;
            case 6:
                return Timestamp.class;
            default:
                return null;
        }
    }

    @Override
    protected Object getAttributeOfEntity(User user, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return user.getName();
            case 1:
                return user.getIdNumber();
            case 2:
                return user.getUsername();
            case 3:
                return user.getPoints();
            case 4:
                return user.isPresent();
            case 5:
                return user.getCreatedAt();
            case 6:
                return user.getUpdatedAt();
            default:
                return null;
        }
    }

    @Override
    protected void displayError(SQLException e) {
        MainFrame.showError("HIBA", "Hiba a felhasználók listázása közben: \n" + e.getMessage());
    }
}
