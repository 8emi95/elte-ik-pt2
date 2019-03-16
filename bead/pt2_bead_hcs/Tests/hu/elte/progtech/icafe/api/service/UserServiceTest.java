package hu.elte.progtech.icafe.api.service;

import hu.elte.progtech.icafe.api.entity.Session;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.Date;

public class UserServiceTest {
    private UserService userService;
    private Session session;

    @Before
    public void setUp() {
        userService = new UserService();
        session = new Session();
    }

    @Test
    public void getLoyaltyPoints() throws Exception {
        session.setStartTime(new Timestamp(new Date(2017,05,14,14,50).getTime()));
        session.setEndTime(new Timestamp(new Date(2017,05,14,16,50).getTime()));
        Assert.assertTrue(userService.getLoyaltyPoints(session) == 3);
    }

    @Test
    public void getLoyaltyPoints_aFullDay() throws Exception {
        session.setStartTime(new Timestamp(new Date(2017,05,14,16,00).getTime()));
        session.setEndTime(new Timestamp(new Date(2017,05,15,16,00).getTime()));
        Assert.assertTrue(userService.getLoyaltyPoints(session) == 43);
    }

    @Test
    public void getLoyaltyPoints_alwaysLowerIfStartWasNotInAnExactHour() throws Exception {
        session.setStartTime(new Timestamp(new Date(2017,05,14,14,59).getTime()));
        session.setEndTime(new Timestamp(new Date(2017,05,15,14,59).getTime()));
        Assert.assertTrue(userService.getLoyaltyPoints(session) == 41);
    }

}