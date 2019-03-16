package hu.valdar.progtech.frontend.model.table;

import hu.valdar.progtech.api.entity.ArtistEntity;
import hu.valdar.progtech.backend.db.DataSource;

public class ArtistTableModel extends EntityTableModel<ArtistEntity>{

    public ArtistTableModel() {
        super(ArtistEntity.FIELD_NAMES, DataSource.getInstance().getArtistDao());
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return Integer.class;
            case 2:
                return String.class;
            default:
                return null;
        }
    }

    @Override
    protected Object getAttributeOfEntity(ArtistEntity artist, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return artist.getName();
            case 1:
                return artist.getAge();
            case 2:
                return artist.getInstrument();
            default:
                return null;
        }
    }

    @Override
    protected void setEntityAttributes(int columnIndex, ArtistEntity artist, Object aValue) {
        switch (columnIndex) {
            case 0:
                artist.setName((String) aValue);
                break;
            case 1:
                artist.setAge((Integer) aValue);
                break;
            case 2:
                artist.setInstrument((String) aValue);
                break;
        }
    }

    @Override
    public void addNewEntity() {
        ArtistEntity artistEntity = new ArtistEntity();
        artistEntity.setName("New artist");
        artistEntity.setAge(999);
        artistEntity.setInstrument("-");
        addNewEntity(artistEntity);
    }
}
