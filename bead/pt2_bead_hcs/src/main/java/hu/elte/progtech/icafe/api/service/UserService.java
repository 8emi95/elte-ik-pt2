/**
 * Created by Csaba_Hete on 2017.03.26..
 */
package hu.elte.progtech.icafe.api.service;

import hu.elte.progtech.icafe.api.entity.*;
import hu.elte.progtech.icafe.backend.dao.DataSource;
import hu.elte.progtech.icafe.backend.dao.UserDao;
import hu.elte.progtech.icafe.backend.db.DbHelper;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public class UserService implements DaoService<User> {

    private UserDao userDao;

    public UserService() {
        userDao = DataSource.getInstance().getUserDao();
    }

    @Override
    public int getEntityCount() throws Exception {
        return userDao.getEntityCount();
    }

    @Override
    public List<User> getEntities() throws Exception {
        return userDao.getEntities();
    }

    @Override
    public User getEntityById(long id) throws Exception {
        User user = userDao.getEntityById(id);
        List<Address> addresses = new AddressService().getEntitiesByForeignKey("USER_ID", id);
        addresses.stream().findFirst().ifPresent(user::setAddress);
        user.setActiveSession(getActiveSession(user));
        user.setPendingPayment(getPendingPayment(user));
        return user;
    }

    @Override
    public User getEntityByRowIndex(int rowIndex) throws Exception {
        User user = userDao.getEntityByRowIndex(rowIndex);
        List<Address> addresses = new AddressService().getEntitiesByForeignKey("USER_ID", user.getId());
        addresses.stream().findFirst().ifPresent(user::setAddress);
        return user;
    }

    @Override
    public void addEntity(User entity) throws Exception {
        final Connection con = DbHelper.getInstance().getConnection();
        checkUsernameAvaibility(entity);
        try {
            con.setAutoCommit(false);
            long userId = userDao.addEntity(entity);
            Address address = entity.getAddress();
            address.setUserId(userId);
            new AddressService().addEntity(address);
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            throw e;
        } finally {
            con.setAutoCommit(true);
        }
    }

    @Override
    public void deleteEntity(long index) throws Exception {
        userDao.deleteEntity(index);
    }

    @Override
    public void updateEntity(User entity, long index) throws Exception {
        final Connection con = DbHelper.getInstance().getConnection();
        checkUsernameAvaibility(entity);
        try {
            con.setAutoCommit(false);
            userDao.updateEntity(entity, index);
            Address address = entity.getAddress();
            new AddressService().updateEntity(address, address.getId());
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            throw e;
        } finally {
            con.setAutoCommit(true);
        }
    }

    @Override
    public List<User> getEntitiesByForeignKey(final String column, final long id) throws Exception {
        return userDao.getEntitiesByForeignKey(column, id);
    }

    private void checkUsernameAvaibility(User entity) throws Exception {
        List<User> entities = userDao.getEntities();
        if (entities.stream().anyMatch(o -> o.getUsername().equals(entity.getUsername()) && !o.getId().equals(entity.getId()))) {
            throw new Exception("Már létezik ügyfél ilyen felhasználónévvel!");
        }
    }

    public void loginUser(User user) throws Exception {
        if (user.isPresent()) {
            throw new Exception("A felhasználó már be van lépve!");
        }
        ComputerService computerService = new ComputerService();
        Computer computer = computerService.getFirstFree();
        if (null == computer) {
            throw new Exception("Nincs szabad számítógép!");
        }
        final Connection con = DbHelper.getInstance().getConnection();
        try {
            con.setAutoCommit(false);
            computerService.setBusy(computer);
            Session session = new Session();
            session.setUserId(user.getId());
            session.setComputerId(computer.getId());
            new SessionService().addEntity(session);
            user.setPresent(true);
            userDao.updateEntity(user, user.getId());
            con.commit();
            session.setComputer(computer);
            user.setActiveSession(session);
        } catch (SQLException e) {
            con.rollback();
            throw e;
        } finally {
            con.setAutoCommit(true);
        }
    }

    public void closeActiveSession(User user) throws Exception {
        if (!user.isPresent()) {
            throw new Exception("A felhasználó nincs belépve!");
        }
        Session session = user.getActiveSession();
        Computer computer = session.getComputer();
        final Connection con = DbHelper.getInstance().getConnection();
        try {
            con.setAutoCommit(false);
            new ComputerService().setFree(computer);
            new SessionService().updateEntity(session, session.getId());
            user.setPoints(user.getPoints() + getLoyaltyPoints(session));
            userDao.updateEntity(user, user.getId());
            PaymentService paymentService = new PaymentService();
            Payment payment = paymentService.createPayment(user, session);
            paymentService.addEntity(payment);
            con.commit();
            user.setPendingPayment(payment);
        } catch (SQLException e) {
            con.rollback();
            throw e;
        } finally {
            con.setAutoCommit(true);
        }
    }

    public void logoutUser(User user) throws Exception {
        user.setPresent(false);
        userDao.updateEntity(user, user.getId());
    }

    public Integer getLoyaltyPoints(Session session) {
        int points = 0;
        LocalDateTime startTime = session.getStartTime().toLocalDateTime();
        for (int i = 0; i < SessionService.getDiffInCeiledHours(session.getEndTime(), session.getStartTime()); ++i) {
            if (startTime.toLocalTime().equals(LocalTime.of(16, 0, 0)) || startTime.plusHours(1).toLocalTime().equals(LocalTime.of(21, 0, 0)) ||
                    startTime.toLocalTime().isBefore(LocalTime.of(16, 0, 0)) && startTime.plusHours(1).toLocalTime().isAfter(LocalTime.of(16, 0, 0)) ||
                    startTime.toLocalTime().isAfter(LocalTime.of(16, 0, 0)) && startTime.plusHours(1).toLocalTime().isBefore(LocalTime.of(21, 0, 0)) &&  !startTime.plusHours(1).toLocalTime().equals(LocalTime.of(0, 0, 0)) ||
                    startTime.toLocalTime().isBefore(LocalTime.of(21, 0, 0)) && startTime.plusHours(1).toLocalTime().isAfter(LocalTime.of(21, 0, 0))) {
                points += 1;
            } else {
                points += 2;
            }
            startTime = startTime.plusHours(1);
        }
        return points;
    }

    private Session getActiveSession(User user) throws Exception {
        if (null != user.getId()) {
            SessionService sessionService = new SessionService();
            List<Session> sessions = sessionService.getEntitiesByForeignKey("USER_ID", user.getId());
            Optional<Session> active = sessions.stream().filter(o -> null == o.getEndTime()).findFirst();
            if (active.isPresent()) {
                return sessionService.getEntityById(active.get().getId());
            }
        }
        return null;
    }

    private Payment getPendingPayment(User user) throws Exception {
        return new PaymentService().getPendingPayment(user);
    }
}
