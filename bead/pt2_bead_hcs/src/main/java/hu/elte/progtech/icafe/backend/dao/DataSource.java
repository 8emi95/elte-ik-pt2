/**
 * Created by Csaba_Hete on 2017.05.10..
 */
package hu.elte.progtech.icafe.backend.dao;

import lombok.Getter;

public class DataSource {
    @Getter private final UserDao userDao;
    @Getter private final AddressDao addressDao;
    @Getter private final ComputerDao computerDao;
    @Getter private final PaymentDao paymentDao;
    @Getter private final SessionDao sessionDao;


    private DataSource(){
        userDao = new UserDao();
        addressDao = new AddressDao();
        computerDao = new ComputerDao();
        paymentDao = new PaymentDao();
        sessionDao = new SessionDao();
    }

    public static DataSource getInstance(){
        return DataSourceHolder.INSTANCE;
    }

    private static class DataSourceHolder{
        private static final DataSource INSTANCE = new DataSource();
    }
}
