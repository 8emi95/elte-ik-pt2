/**
 * Created by Csaba_Hete on 2017.03.18..
 */

package hu.elte.progtech.icafe.backend.db.migration;

import hu.elte.progtech.icafe.backend.db.DbHelper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.function.Consumer;

class Migration {
    private static Migration instance = null;

    static Migration getInstance() {
        if(null == instance){
            instance = new Migration();
        }
        return instance;
    }

    void run(String tableName, Consumer<Blueprint> l) throws SQLException {
        Connection conn = DbHelper.getInstance().getConnection();
        Blueprint table = new Blueprint(conn, tableName);
        l.accept(table);
        table.create();
    }


}
