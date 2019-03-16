package hu.elte.progtech.icafe.api.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.sql.Timestamp;


public class SessionServiceTest {
    private Timestamp startTime;

    @Before
    public void setUp(){
        startTime = new Timestamp(new Date().getTime());
    }

    @Test
    public void testGetDiffInCeiledHours_startEqualsEnd() throws Exception {
        Assert.assertTrue(SessionService.getDiffInCeiledHours(startTime, startTime) == 0);
    }

    @Test
    public void testGetDiffInCeiledHours_startIsBeforeEnd() throws Exception {
        Timestamp endTime = new Timestamp(startTime.getTime() + 1 );
        Assert.assertTrue(SessionService.getDiffInCeiledHours(startTime, endTime) == 1);
    }
    @Test
    public void testGetDiffInCeiledHours_startIsAfterEnd() throws Exception {
        Timestamp endTime = new Timestamp(startTime.getTime() + 1 );
        Assert.assertTrue(SessionService.getDiffInCeiledHours(endTime, startTime) == 1);
    }

    @Test
    public void testGetDiffInCeiledHours_testCalculation() throws Exception {
        Timestamp endTime = new Timestamp(startTime.getTime() + 60 * 60 * 1000 );
        Assert.assertTrue(SessionService.getDiffInCeiledHours(startTime, endTime) == 1);
        endTime = new Timestamp(startTime.getTime() + 60 * 60 * 1000 +1 );
        Assert.assertTrue(SessionService.getDiffInCeiledHours(startTime, endTime) == 2);
    }

}