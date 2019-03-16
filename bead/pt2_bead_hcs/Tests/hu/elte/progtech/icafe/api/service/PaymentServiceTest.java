package hu.elte.progtech.icafe.api.service;

import hu.elte.progtech.icafe.api.entity.Payment;
import hu.elte.progtech.icafe.backend.configuration.AppConfiguration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PaymentServiceTest {
    private PaymentService paymentService;
    final int maxDiscount = Integer.parseInt(AppConfiguration.getConfiguration().getProperty("maxDiscount"));
    final int discountPerPoints = Integer.parseInt(AppConfiguration.getConfiguration().getProperty("discountPerPoints"));

    @Before
    public void setUp() {
        paymentService = new PaymentService();

    }

    @Test
    public void testGetDiscount() {
        Assert.assertTrue(paymentService.getDiscount(0) == 0);
        Assert.assertTrue(paymentService.getDiscount(discountPerPoints) == 1);
        Assert.assertTrue(paymentService.getDiscount(maxDiscount * discountPerPoints) == maxDiscount);
        Assert.assertTrue(paymentService.getDiscount(100 * discountPerPoints) == maxDiscount);
    }

    @Test
    public void testGetGrossAmount_zeroVatRate() {
        Payment payment = new Payment();
        payment.setQuantity(1L);
        payment.setNetPrice(1D);
        payment.setVatRate(0);
        payment.setDiscount(0);
        Assert.assertTrue(paymentService.getGrossAmount(payment) == 1);
    }

    @Test
    public void testGetGrossAmount_fullVatRate() {
        Payment payment = new Payment();
        payment.setQuantity(1L);
        payment.setNetPrice(1D);
        payment.setVatRate(100);
        payment.setDiscount(0);
        Assert.assertTrue(paymentService.getGrossAmount(payment) == 2);
    }

    @Test
    public void testGetGrossAmount_zeroDiscount() {
        Payment payment = new Payment();
        payment.setQuantity(1L);
        payment.setNetPrice(100D);
        payment.setVatRate(27);
        payment.setDiscount(0);
        Assert.assertTrue(paymentService.getGrossAmount(payment) == 127);
    }

    @Test
    public void testGetGrossAmount_fullDiscount() {
        Payment payment = new Payment();
        payment.setQuantity(1L);
        payment.setNetPrice(1D);
        payment.setVatRate(1);
        payment.setDiscount(100);
        Assert.assertTrue(paymentService.getGrossAmount(payment) == 0);
    }
}